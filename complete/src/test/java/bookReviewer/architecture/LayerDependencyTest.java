package bookReviewer.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "bookReviewer")
public class LayerDependencyTest {

    @ArchTest
    static final ArchRule entities_should_only_depended_on_nothing =
            classes().that().resideInAPackage("..entity..")
                    .should().onlyDependOnClassesThat().resideInAnyPackage("java..",
                    "javax..",
                    "..entity..");

    @ArchTest
    static final ArchRule application_should_only_depended_on_entity =
            classes().that().resideInAPackage("..application..")
                    .should().onlyDependOnClassesThat().resideInAnyPackage(
                            "java..",
                    "javax..",
                    "..entity..",
                    "..application.."
            );

    @ArchTest
    static final ArchRule adapter_should_only_depended_on_entities_and_application =
            classes().that().resideInAPackage("..adapter..")
                    .should().onlyDependOnClassesThat().resideInAnyPackage("java..",
                    "javax..",
                    "..entity..",
                    "..application..",
                    "..adapter..",
                    "com.fasterxml..",
                    "org.hibernate.."
            );



    /*
    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()

            .layer("Entity").definedBy("bookReviewer.entity")
            .layer("Application").definedBy("bookReviewer.application..")
            .layer("Adapter").definedBy("bookReviewer.adapter..")
            .layer("Periphery").definedBy("bookReviewer.periphery..")
            .layer("Configuration").definedBy("bookReviewer.configuration..")

            .whereLayer("Configuration").mayNotBeAccessedByAnyLayer()
            .whereLayer("Periphery").mayOnlyBeAccessedByLayers("Configuration")
            .whereLayer("Adapter").mayOnlyBeAccessedByLayers("Configuration", "Periphery")
            .whereLayer("Application").mayOnlyBeAccessedByLayers("Configuration", "Periphery", "Adapter")
            .whereLayer("Entity").mayOnlyBeAccessedByLayers("Configuration", "Periphery", "Adapter", "Application");
*/
}











