package com.cadastrocliente.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidateDateOfBirthTest {

    @Test
    void isValid() {
        boolean valid = ValidateDateOfBirth.isValid("30/06/1997");
        boolean invalid = ValidateDateOfBirth.isValid("32/06/1995");

        assertTrue(valid);
        assertFalse(invalid);
    }
}