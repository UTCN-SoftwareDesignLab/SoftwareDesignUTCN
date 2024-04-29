package architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.GeneralCodingRules.*;

@AnalyzeClasses(
    packages = "org.example.spring1",
    importOptions = ImportOption.DoNotIncludeTests.class)
public class CodingRulesTest {

  @ArchTest
  static final ArchRule controllerAndServiceFieldsShouldNotBePublic =
      fields()
          .that()
          .areDeclaredInClassesThat()
          .areAnnotatedWith(RestController.class)
          .or()
          .areDeclaredInClassesThat()
          .areAnnotatedWith(Service.class)
          .should()
          .notBePublic()
          .because("Public fields are not allowed in Controller or Service classes");

  @ArchTest
  static final ArchRule classesShouldNotAccessStandardStreamsFromLibrary =
      NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS;

  @ArchTest
  static final ArchRule classesShouldNotThrowGenericExceptions =
      NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

  @ArchTest
  static final ArchRule classesShouldNotUseJavaUtilLogging =
      NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

  @ArchTest
  static final ArchRule classesShouldNotUseJodatime = NO_CLASSES_SHOULD_USE_JODATIME;

  @ArchTest
  static final ArchRule classesShouldNotUseFieldInjection = NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

  @ArchTest
  static final ArchRule configFilesShouldResideInConfigPackage =
      noClasses()
          .that()
          .areAnnotatedWith(Configuration.class)
          .should()
          .resideOutsideOfPackage("org.example.spring1.config..");

  @ArchTest
  static final ArchRule jobsShouldResideInJobsPackage =
      noMethods()
          .that()
          .areAnnotatedWith(Scheduled.class)
          .should()
          .beDeclaredInClassesThat()
          .resideOutsideOfPackage("org.example.spring1.jobs..");

}
