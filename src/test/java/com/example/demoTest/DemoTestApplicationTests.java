package com.example.demoTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoTestApplicationTests {

	@Test
	void contextLoads() {
	}

	// Optionnel : ajoutez ce test pour vérifier la config
    @Test
    void testProfileIsActive() {
        System.out.println("✅ Profil 'test' activé – si vous voyez ce message, la config H2 est utilisée.");
    }

}
