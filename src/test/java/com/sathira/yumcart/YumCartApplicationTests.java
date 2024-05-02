package com.sathira.yumcart;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YumCartApplicationTests {

    @BeforeAll
    static void beforeAll() {
        System.setProperty("skipDataSeeding", "true");
    }

    @Test
        void contextLoads() {
    }

}
