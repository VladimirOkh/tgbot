package ru.skillfactorydemo.tgbot.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "cbr.url")
public class CustomProperties {

    /**
     * The url to connect to.
     */
    String url = "http://www.cbr.ru/dailyinfowebserv/dailyinfo.asmx?wsdl";

    /**
     * The time to wait for the connection.
     */
    private int timeoutInMilliSeconds = 1000;

    // Getters and Setters

}