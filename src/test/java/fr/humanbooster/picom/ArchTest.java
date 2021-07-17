package fr.humanbooster.picom;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("fr.humanbooster.picom");

        noClasses()
            .that()
            .resideInAnyPackage("fr.humanbooster.picom.service..")
            .or()
            .resideInAnyPackage("fr.humanbooster.picom.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..fr.humanbooster.picom.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
