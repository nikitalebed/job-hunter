package com.jh.lebid.sheduler;

import com.jh.lebid.integration.slack.SlackUserMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SlackMessageSendSheduler {

    private final SlackUserMessageSender slackUserMessageSender;

    @Autowired
    public SlackMessageSendSheduler(SlackUserMessageSender slackUserMessageSender) {
        this.slackUserMessageSender = slackUserMessageSender;
    }

    @Scheduled(fixedDelay = 2000)
    public void sendSimpleMessageToSlckUser() {
        slackUserMessageSender.sendDirectMessageToAUser();
    }
}
