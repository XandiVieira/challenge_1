package com.cadastrocliente.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Slf4j
public class ValidateDateOfBirth {

    public static boolean isValid(String dOB) {
        log.info("Validating date of birth: " + dOB);
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dOB);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}