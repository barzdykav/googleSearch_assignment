package com.selenide.selenideTask.googleSearch.tests;

import com.selenide.selenideTask.ageVerifier.AgeVerifier;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class AgeVerifierTest {

    @Test(dataProvider = "ageTestData", groups = {"unit"})
    public void testIsUserOver18(LocalDate dateOfBirth, boolean expectedResult) {
        boolean result = AgeVerifier.isUserOver18(dateOfBirth);
        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider(name = "ageTestData")
    public Object[][] ageTestData() {
        return new Object[][]{
                // Test cases where the user is 18 years old or older
                {LocalDate.now().minusYears(18).minusYears(1), true}, // the user is 1 year older than 18
                {LocalDate.now().minusYears(18), true}, // the user is 18 years old
                {LocalDate.now().minusYears(18).minusDays(1), true}, // the user is 18 years old and 1 day

                // Test cases where the user is younger than 18
                {LocalDate.now().minusYears(17), false}, // the user is 17 years old
                {LocalDate.now().minusYears(18).plusDays(1), false}, // the user is 18 years old minus 1 day
                {LocalDate.now().minusYears(17).minusMonths(11), false}, // the user is less than 18 years old
                {LocalDate.now(), false}, // the user was born today

                //Test cases with invalid date of birth (null or future date)
                {null, false}, // null date of birth
                {LocalDate.now().plusYears(1), false} // future date of birth
        };
    }
}