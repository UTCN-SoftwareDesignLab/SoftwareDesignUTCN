package architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
    packages = "org.example.spring1",
    importOptions = ImportOption.DoNotIncludeTests.class)
public class AccessFlowRulesTest {
  @ArchTest
  static final ArchRule servicesShouldNotAccessControllers =
      noClasses()
          .that()
          .areAnnotatedWith(Service.class)
          .should()
          .dependOnClassesThat()
          .areAnnotatedWith(RestController.class);

  @ArchTest
  static final ArchRule controllersShouldNotAccessRepositories =
      noClasses()
          .that()
          .areAnnotatedWith(RestController.class)
          .should()
          .dependOnClassesThat()
          .areAnnotatedWith(Repository.class)
          .orShould()
          .dependOnClassesThat()
          .areMetaAnnotatedWith(Repository.class);

  @ArchTest
  static final ArchRule entitiesShouldNotReferenceAnyBeans =
      noClasses()
          .that()
          .areNotAnnotatedWith(Entity.class)
//          .or()
//          .areAnnotatedWith(Document.class)
          .should()
          .dependOnClassesThat()
          .areMetaAnnotatedWith(Bean.class);
}
