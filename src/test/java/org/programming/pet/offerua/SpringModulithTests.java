package org.programming.pet.offerua;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class SpringModulithTests {

    ApplicationModules modules = ApplicationModules.of(OfferuaApplication.class);

    @Test
    void shouldBeCompliant() {
        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {
        new Documenter(modules)
                .writeModuleCanvases()
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }
}