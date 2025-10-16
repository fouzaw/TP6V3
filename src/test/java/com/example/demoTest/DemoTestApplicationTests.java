package com.example.demoTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoTestApplicationTests {

	@Test
	void contextLoads() {
        System.out.println("✅ Profil 'test' activé – si vous voyez ce message, la config H2 est utilisée.");
	}

	

}
