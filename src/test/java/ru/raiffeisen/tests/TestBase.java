package ru.raiffeisen.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.raiffeisen.config.AuthConfig;
import ru.raiffeisen.config.WebConfig;
import ru.raiffeisen.helpers.Attach;

import java.util.Map;

public class TestBase {

    private static final WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
    private static final AuthConfig authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

    @BeforeAll
    static void setUp() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowserName();
        Configuration.browserSize = webConfig.getBrowserSize();
        Configuration.browserVersion = webConfig.getBrowserVersion();

        if (webConfig.isRemote()) {
            Configuration.remote = "https://" + authConfig.getLogin() + ":"
                    + authConfig.getPassword() + "@"
                    + webConfig.getRemoteURL();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @AfterEach
    void addAttachments() {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();

    }
}
