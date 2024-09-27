package com.elktest.demo.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;

@Configuration
public class LogConfig {

    public void configure() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        LogstashTcpSocketAppender logstashAppender = new LogstashTcpSocketAppender();
        logstashAppender.setName("LOGSTASH");
        logstashAppender.addDestination("192.168.0.158:5044");

        LogstashEncoder encoder = new LogstashEncoder();
        encoder.setIncludeCallerData(true);
        encoder.setCustomFields("{\"app\":\"spring-boot-app\"}");

        logstashAppender.setEncoder(encoder);
        logstashAppender.start();

        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory
                .getLogger(Logger.ROOT_LOGGER_NAME);
        root.addAppender(logstashAppender);

        StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
    }
}