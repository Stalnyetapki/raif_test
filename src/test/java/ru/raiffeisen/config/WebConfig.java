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
    String getBrowserSize();

    @Key("browser_name")
    String getBrowserName();

    @Key("browser_version")
    String getBrowserVersion();

    @Key("is_remote")
    @DefaultValue("false")
    boolean isRemote();

    @Key("remote_url")
    String getRemoteURL();

}
