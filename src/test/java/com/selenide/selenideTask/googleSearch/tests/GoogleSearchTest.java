package com.selenide.selenideTask.googleSearch.tests;

import com.codeborne.selenide.*;
import com.selenide.selenideTask.googleSearch.pages.GoogleSearchPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static com.codeborne.selenide.AssertionMode.SOFT;
import com.codeborne.selenide.testng.SoftAsserts;

@Listeners({SoftAsserts.class})
public class GoogleSearchTest {

    private static final String NAME_VALUE = "Elon";
    private static final String FULL_NAME_VALUE = "Elon Musk";

    private GoogleSearchPage google = new GoogleSearchPage();

    @BeforeClass(alwaysRun=true)
    public void setUp() {
        Configuration.assertionMode = SOFT;
        Configuration.baseUrl = "https://www.google.com";
    }

    @Test(groups= {"ui"})
    public void searchByName() {
        google
                .open()
                .hoverGoogleSearchField()
                .checkTooltipIsDisplayed("Search")
                .searchFor(NAME_VALUE)
                .checkResultsSizeIsAtLeast(1)
                .checkResultHasTest(0, NAME_VALUE)
                .clickOnGoogleLogo()
                .checkSearchResultAreNoLongerVisible();

    }
}
