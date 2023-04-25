package ru.raiffeisen.tests;

import com.codeborne.selenide.CollectionCondition;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.*;
import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;

public class RaiffeisenMainPageTests extends TestBase{


    static Stream<Arguments> checkHeaderMenuItemsTest() {
        return Stream.of(
                Arguments.of("en", List.of("Consumer Loans", "Mortgage", "Bank cards", "Saving and investing", "Insurance", "Online-service")),
                Arguments.of("", List.of("Кредиты", "Ипотека", "Карты", "Вклады", "Инвестиции", "Страхование", "Сервисы", "Про Онлайн-банк"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Check header existing links {1} in locale {0}")
    @Severity(SeverityLevel.BLOCKER)
    void checkHeaderMenuItemsTest(String locale, List<String> menuItems) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open the main page in specific locale", () -> {
            open("/" + locale);
        });

        step("Check menu items in current locale", () -> {
            $$(".Headerstyles__Box-sc-1148tul-0 div[data-marker='HeaderItems.HeaderItems.Header.Item']").filter(visible)
                    .shouldHave(CollectionCondition.texts(menuItems));
        });
    }


    @ValueSource(strings = {"О банке", "Задать вопрос", "Качество обслуживания", "Реквизиты", "Противодействие коррупции", "Пресс-центр", "Карьера в банке", "Инвесторам", "Аналитика"})
    @ParameterizedTest(name = "Check that link {0} exists in footer")
    @Severity(SeverityLevel.CRITICAL)
    void checkFooterLinks(String link) {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open the main page", () -> {
            open("/");
        });
        step("Check link existing in footer", () -> {
            $$(".Linkstyles__Wrapper-sc-1c9c2bd-0").filterBy(visible).shouldHave(CollectionCondition.itemWithText(link));
        });
    }


    @Test
    @DisplayName("Check that user can go on currency rates page from main page")
    @Severity(SeverityLevel.NORMAL)
    void checkCurrencyRatesLink() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open the main page", () -> {
            open("/");
        });
        step("Find and go to Currency rates page by link on main page", () -> {
            $("[data-marker='CurrencyRate.Button_1']").click();
            $("h1[data-marker='Headline.Heading']").shouldHave(text("Курс валют в Райффайзен Банке"));
        });
    }

    @Test
    @DisplayName("Check that support chat open by click")
    @Severity(SeverityLevel.NORMAL)
    void checkSupportChatOpen() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open the main page", () -> {
            open("/");
        });
        step("Open support chat", () -> {
            $(".Headerstyles__Action-sc-1148tul-3 [data-marker='HeaderWrap.Button_1']").click();
            $("[data-testid='rchat-root-window']").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Check the 'Онлайн-банк' button work and ability to go on online bank page")
    @Severity(SeverityLevel.NORMAL)
    void checkOnlineBankingButton() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Open the main page", () -> {
            open("/");
        });
        step("Open online bank page in new tab", () -> {
            $(".Headerstyles__Action-sc-1148tul-3 [data-marker='HeaderWrap.Button_2']").click();
            Selenide.switchTo().window(1);
            webdriver().shouldHave(url("https://online.raiffeisen.ru/login/main"));
        });
    }

}


