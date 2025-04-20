package org.example.springtaskjpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class SpringTaskJpaApplicationTests {


    @Test
    void main_shouldStartApplication() {
        assertDoesNotThrow(() -> SpringTaskJpaApplication.main(new String[]{}));
    }

}
