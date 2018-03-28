package com.jh.lebid.integration.slack;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SlackSessionConfig {

    @Value("${slack.token}")
    private String slackToken;

    @Bean
    public SlackSession slackSession() throws IOException {
        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(slackToken);
        session.connect();
        return session;
    }

}
