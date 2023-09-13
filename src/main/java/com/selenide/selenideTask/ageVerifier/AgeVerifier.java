package com.selenide.selenideTask.ageVerifier;

import java.time.LocalDate;
import java.time.Period;

public class AgeVerifier {
    public static boolean isUserOver18(LocalDate dateOfBirth) {
        try {
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(dateOfBirth, currentDate);
            return age.getYears() >= 18;
        } catch (NullPointerException e) {
            System.err.println("Error: Date of birth cannot be null.");
            return false;
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            return false;
        }
    }
}
