package com.selenide.selenideTask.googleSearch.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class GoogleSearchPage {
    private final SelenideElement searchInputField = $(byName("q"));
    private final GoogleSearchResultPage results = new GoogleSearchResultPage();

    public GoogleSearchPage open() {
        Selenide.open("/");
        return this;
    }

    public GoogleSearchPage hoverGoogleSearchField() {
        searchInputField.hover();
        return this;
    }

    public GoogleSearchResultPage searchFor(String text) {
        searchInputField.val(text).pressEnter();
        return new GoogleSearchResultPage();
    }

    public GoogleSearchPage checkTooltipIsDisplayed(String tooltipText) {
        searchInputField.shouldHave(Condition.attribute("title", tooltipText));
        return this;
    }

    public GoogleSearchPage checkSearchResultAreNoLongerVisible() {
        results.getResults().shouldBe(CollectionCondition.empty);
        return this;
    }
}
