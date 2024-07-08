package org.programming.pet.offerua;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

class SpringModulithTests {

    ApplicationModules modules = ApplicationModules.of(OfferuaApplication.class);

    @Test
    void shouldBeCompliant() {
        modules.verify();
    }
}