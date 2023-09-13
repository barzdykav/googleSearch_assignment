package com.selenide.selenideTask.googleSearch.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GoogleSearchResultPage {
    private final ElementsCollection results = $$("#search .g");
    private final SelenideElement googleLogo = $("img[alt='Google']");

    public GoogleSearchPage clickOnGoogleLogo() {
        googleLogo.click();
        return new GoogleSearchPage();
    }

    public GoogleSearchResultPage checkResultsSizeIsAtLeast(int expectedSize) {
        results.shouldHave(sizeGreaterThan(expectedSize));
        return this;
    }

    public GoogleSearchResultPage checkResultHasTest(int index, String expectedText) {
        results.get(index).shouldHave(text(expectedText));
        return this;
    }

    public ElementsCollection getResults() {
        return results;
    }
}
