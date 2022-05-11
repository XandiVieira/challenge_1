package com.cadastrocliente.utils;

import com.cadastrocliente.exception.CustomInvalidCpfException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateCPFTest {

    @Test
    void isValid() {
        boolean valid = ValidateCPF.isValid("00896126048");
        boolean valid2 = ValidateCPF.isValid("84877730010");
        boolean invalid = ValidateCPF.isValid("00000000000");
        boolean invalid2 = ValidateCPF.isValid("012164370099");
        boolean invalid3 = ValidateCPF.isValid("11111100000");

        assertTrue(valid);
        assertTrue(valid2);
        assertFalse(invalid);
        assertFalse(invalid2);
        assertFalse(invalid3);
    }

    @Test
    void formatCpf() {
        String cpf = "008.961.260-48";
        String formattedCpf = "00896126048";

        String returnedCpf = ValidateCPF.formatCpf(cpf);

        assertEquals(formattedCpf, returnedCpf);
    }
}