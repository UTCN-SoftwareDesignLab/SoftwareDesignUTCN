package architecture;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaField;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.stream.Stream;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(
    packages = "org.example.spring1",
    importOptions = ImportOption.DoNotIncludeTests.class)
public class ControllerRulesTest {

  @ArchTest
  static final ArchRule controllersShouldOnlyHaveServicesAsFields =
      classes()
          .that()
          .areAnnotatedWith(RestController.class)
          .should(haveServicesAsFields())
          .because("Controllers should only have services as fields.");

  @ArchTest
  static final ArchRule controllersShouldOnlyReturnDtos =
      methods()
          .that()
          .areDeclaredInClassesThat()
          .areAnnotatedWith(RestController.class)
          .and()
          .areMetaAnnotatedWith(RequestMapping.class)
          .and()
          .doNotHaveRawReturnType(Void.TYPE)
          .and()
          .doNotHaveRawReturnType(excludedExceptions())
          .should(returnDtoObjects())
          .because("Controllers should only return DTO objects.");

  @ArchTest
  static final ArchRule controllersShouldNeverReferenceEntitiesDirectly =
      noClasses()
          .that()
          .areAnnotatedWith(RestController.class)
          .should()
          .accessClassesThat(areEntities())
          .because("Entities should never be referenced from controllers directly.");

  @ArchTest
  static ArchRule allControllerMethodsShouldBePublic =
      methods()
          .that()
          .areDeclaredInClassesThat()
          .areAnnotatedWith(RestController.class)
          .should()
          .bePublic()
          .because("All controller methods should be public");

  @ArchTest
  static ArchRule allPublicControllerMethodsShouldBeRestEndpoints =
      methods()
          .that()
          .areDeclaredInClassesThat()
          .areAnnotatedWith(RestController.class)
          .should()
          .beAnnotatedWith(restApiAnnotation())
          .because("All public controller methods should be REST endpoints");


  private static ArchCondition<? super JavaClass> haveServicesAsFields() {
    return new ArchCondition<>("have services as fields") {
      @Override
      public void check(JavaClass item, ConditionEvents events) {
        for (JavaField field : item.getFields()) {
          if (!field.getType().toErasure().isAnnotatedWith(Service.class)) {
            events.add(
                new SimpleConditionEvent(
                    item,
                    false,
                    "%s from %s is non-service as field"
                        .formatted(field.getName(), item.getSimpleName())));
          }
        }
      }
    };
  }

  private static ArchCondition<JavaMethod> returnDtoObjects() {
    return new ArchCondition<>("return DTO object") {
      @Override
      public void check(JavaMethod item, ConditionEvents events) {
        Type genericReturnType = item.reflect().getGenericReturnType();
        if (!genericReturnType.getTypeName().endsWith("Dto")) {
          events.add(
              new SimpleConditionEvent(
                  item,
                  false,
                  "%s from %s not returning dto object (was %s)"
                      .formatted(
                          item.getName(),
                          item.getOwner().getSimpleName(),
                          item.getReturnType().getName())));
        }
      }
    };
  }

  private static DescribedPredicate<JavaClass> excludedExceptions() {
    return new DescribedPredicate<>("are not (boxed) primitives and arrays") {
      @Override
      public boolean test(JavaClass input) {
        return input.isPrimitive()
            || input.getPackageName().contains("java.lang")
            || input.isArray()
            || input.getPackageName().contains("java.util")
            || input.getPackageName().contains("java.util.stream")
            || input.getName().contains("org.springframework.data.domain.Page")
            || input.getName().contains("org.springframework.http.ResponseEntity");
      }
    };
  }

  private static DescribedPredicate<? super JavaClass> areEntities() {
    return new DescribedPredicate<>("entity") {
      @Override
      public boolean test(JavaClass input) {
        return input.isAnnotatedWith(Entity.class);
      }
    };
  }

  private static DescribedPredicate<? super JavaAnnotation<?>> restApiAnnotation() {
    return new DescribedPredicate<>("rest api annotation") {
      @Override
      public boolean test(JavaAnnotation<?> input) {
        return Stream.of(
                GetMapping.class,
                PostMapping.class,
                PutMapping.class,
                PatchMapping.class,
                DeleteMapping.class)
            .map(Class::getSimpleName)
            .anyMatch(input.getRawType().getSimpleName()::equals);
      }
    };
  }
}
