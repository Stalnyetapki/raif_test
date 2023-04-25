package ru.raiffeisen.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:${env}.properties"
})
public interface WebConfig extends Config {

    @Key("base_url")
    @DefaultValue("https://www.raiffeisen.ru")
    String getBaseUrl();

    @Key("browser_size")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("browser_name")
    @DefaultValue("chrome")
    String getBrowserName();

    @Key("browser_version")
    @DefaultValue("100.0")
    String getBrowserVersion();

    @Key("is_remote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remote_url")
    String getRemoteURL();

}
