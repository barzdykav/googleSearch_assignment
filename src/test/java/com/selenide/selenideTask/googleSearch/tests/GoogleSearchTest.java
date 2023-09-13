package com.selenide.selenideTask.googleSearch.tests;

import com.codeborne.selenide.*;
import com.selenide.selenideTask.googleSearch.pages.GoogleSearchPage;
import org.testng.annotations.*;

import static com.codeborne.selenide.AssertionMode.SOFT;
import static com.codeborne.selenide.Selenide.closeWindow;

import com.codeborne.selenide.testng.SoftAsserts;

@Listeners({SoftAsserts.class})
public class GoogleSearchTest {
    private static final String FULL_NAME_VALUE = "Elon Musk";

    private GoogleSearchPage google = new GoogleSearchPage();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        Configuration.assertionMode = SOFT;
        Configuration.browser = "safari";
        Configuration.baseUrl = "https://www.google.com";
        google.open();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeWindow();
    }

    @DataProvider(name = "validSearchData")
    public Object[][] validSearchData() {
        return new Object[][]{
                {"Bill"},
                {"Elon Musk"},
                {"Serg123"},
                {"Elon-Musk"},
        };
    }

    @DataProvider(name = "invalidSearchData")
    public Object[][] invalidSearchData() {
        return new Object[][]{
                {""},
                {"Xyzzzzzzzzzkj"}
        };
    }

    @Test(groups = {"ui"}, dataProvider = "validSearchData")
    public void testValidNameSearch(String searchData) {
        google
                .searchFor(searchData)
                .checkResultsSizeIsAtLeast(1)
                .checkResultHasTest(0, searchData.replaceAll("[^a-zA-Z0-9\\s]", " "));
    }

    @Test(groups = {"ui"}, dataProvider = "invalidSearchData")
    public void testInvalidNameSearch(String searchData) {
        google
                .searchFor(searchData)
                .checkResultsSizeIs(0);
    }

    @Test(groups = {"ui"})
    public void testHoverInputField() {
        google
                .hoverGoogleSearchField()
                .checkTooltipIsDisplayed("Search");
    }

    @Test(groups = {"ui"})
    public void testCheckLogoReturnsToSearchEnginePage() {
        google
                .searchFor(FULL_NAME_VALUE)
                .checkResultsSizeIsAtLeast(1)
                .clickOnGoogleLogo()
                .checkSearchResultAreNoLongerVisible();
    }
}
