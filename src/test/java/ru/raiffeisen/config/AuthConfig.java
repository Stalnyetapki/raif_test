package ru.raiffeisen.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:auth.properties"
})
public interface AuthConfig extends Config {

    @Key("login")
    String getLogin();

    @Key("password")
    String getPassword();

}
