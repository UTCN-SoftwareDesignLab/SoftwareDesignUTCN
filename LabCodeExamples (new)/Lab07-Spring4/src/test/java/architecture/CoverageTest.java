package architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMember;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tngtech.archunit.core.domain.JavaModifier.PRIVATE;
import static com.tngtech.archunit.core.domain.JavaModifier.STATIC;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "org.example.spring1")
public class CoverageTest {

  public static final double MIN_METHOD_COVERAGE_CONTROLLER = 1.0;
  public static final double MIN_METHOD_COVERAGE_SERVICE = .9;
  public static final double MIN_METHOD_COVERAGE_REPOSITORY = 1.0;
  private static final String[] excludedClasses = {
      "ExcludedExampleRepository",
      "ExcludedExampleService",
      "JwtUtilsService"
  };

  @ArchTest
  static final ArchRule controllersNeedToHaveAssociatedTestClassesAndProperCoverage =
      classes()
          .that()
          .areAnnotatedWith(RestController.class)
          .should(haveTheirEquivalentTestClass())
          .andShould(havePercentMethodCoverage(MIN_METHOD_COVERAGE_CONTROLLER));


  @ArchTest
  static final ArchRule servicesNeedToHaveAssociatedTestClassesAndProperCoverage =
      classes()
          .that()
          .areAnnotatedWith(Service.class)
          .should(haveTheirEquivalentTestClass())
          .andShould(havePercentMethodCoverage(MIN_METHOD_COVERAGE_SERVICE));

  @ArchTest
  static final ArchRule repositoriesNeedToHaveAssociatedTestClassesAndProperCoverage =
      classes()
          .that()
          .areMetaAnnotatedWith(Repository.class)
//          .or()
//          .areAssignableTo(ExampleSqlRepoSuperinterface.class)
//          .or()
//          .areAssignableTo(ExampleMongoRepoSuperinterface.class)
          .should(haveTheirEquivalentTestClass())
          .andShould(havePercentMethodCoverage(MIN_METHOD_COVERAGE_REPOSITORY));


  private static ArchCondition<? super JavaClass> haveTheirEquivalentTestClass() {
    return new ArchCondition<>("have services as fields") {
      @Override
      public void check(JavaClass item, ConditionEvents events) {
        final String className = item.getSimpleName();
        if (Arrays.asList(excludedClasses).contains(className) || item.getMethods().isEmpty()) {
          return;
        }

        final Optional<JavaClass> testClass = getEquivalentTestClass(item);

        if (testClass.isEmpty()) {
          events.add(
              new SimpleConditionEvent(
                  item, false, "%s does not have a test class".formatted(className)));
        }
      }
    };
  }

  private static ArchCondition<? super JavaClass> havePercentMethodCoverage(double coverage) {
    return new ArchCondition<>("have most their methods covered") {
      @Override
      public void check(JavaClass clasz, ConditionEvents events) {
        final String className = clasz.getSimpleName();
        if (Arrays.asList(excludedClasses).contains(className)) {
          return;
        }

        final List<JavaClass> equivalentTestClasses = getEquivalentTestClasses(clasz, className);

        if (!equivalentTestClasses.isEmpty()) {
          final List<String> classMethods =
              getPublicNonStaticMethodsOfClass(clasz).stream().map(JavaMember::getName).toList();
          final Set<String> testMethods =
              equivalentTestClasses.stream()
                  .map(CoverageTest::getPublicNonStaticMethodsOfClass)
                  .flatMap(List::stream)
                  .map(JavaMember::getName)
                  .collect(Collectors.toSet());

          final List<String> missingMethods =
              classMethods.stream()
                  .filter(method -> !testMethods.contains(method))
                  .collect(Collectors.toList());

          final int claszMethodsSize = classMethods.size();
          final double percentMissing = (double) missingMethods.size() / claszMethodsSize;
          if (1 - percentMissing < coverage) {
            events.add(
                new SimpleConditionEvent(
                    clasz,
                    false,
                    "%s has just %.2f%% (%d/%d) of its methods covered [missing: %s]"
                        .formatted(
                            className,
                            (1 - percentMissing) * 100,
                            (claszMethodsSize - missingMethods.size()),
                            claszMethodsSize,
                            String.join(", ", missingMethods))));
          }
        }
      }
    };
  }

  private static List<JavaMethod> getPublicNonStaticMethodsOfClass(JavaClass testClass) {
    return testClass.getMethods().stream()
        .filter(m -> !m.getModifiers().contains(PRIVATE) && !m.getModifiers().contains(STATIC))
        .collect(Collectors.toList());
  }

  private static Optional<JavaClass> getEquivalentTestClass(JavaClass item) {
    final String testClassName = item.getSimpleName() + "Test";
    final String integrationTestClassName = item.getSimpleName() + "IntegrationTest";
    return item.getPackage().getClasses().stream()
        .filter(
            c ->
                c.getSimpleName().equals(testClassName)
                    || c.getSimpleName().equals(integrationTestClassName))
        .findFirst();
  }

  private static List<JavaClass> getEquivalentTestClasses(
      JavaClass item, String className) {
    final String testClassName = className + "Test";
    final String integrationTestClassName = className + "IntegrationTest";
    return item.getPackage().getClasses().stream()
        .filter(
            c ->
                c.getSimpleName().equals(testClassName)
                    || c.getSimpleName().equals(integrationTestClassName))
        .collect(Collectors.toList());
  }
}
