package architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
    packages = "org.example.spring1",
    importOptions = ImportOption.DoNotIncludeTests.class)
public class NamingConventionRulesTest {

  @ArchTest
  static final ArchRule restControllersShouldHaveControllerInClassName =
      classes()
          .that()
          .areAnnotatedWith(RestController.class)
          .should()
          .haveSimpleNameEndingWith("Controller");

  @ArchTest
  static final ArchRule servicesShouldHaveServiceInClassName =
      classes().that().areAnnotatedWith(Service.class).should().haveSimpleNameEndingWith("Service");

  @ArchTest
  static final ArchRule repositoriesShouldHaveRepoInClassName =
      classes()
          .that()
          .areAnnotatedWith(Repository.class)
          .or()
          .areMetaAnnotatedWith(Repository.class)
          .should()
          .haveSimpleNameContaining("Repository");
}
