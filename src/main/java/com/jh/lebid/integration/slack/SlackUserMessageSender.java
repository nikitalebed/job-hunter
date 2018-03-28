package com.jh.lebid.integration.slack;

import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.SlackUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SlackUserMessageSender {

    @Value("${user.who.will.receive}")
    private String userWhoWillRecieve;

    private Integer count = 0;

    private final SlackSession session;

    @Autowired
    public SlackUserMessageSender(SlackSession session) {
        this.session = session;
    }

    @Autowired
    public void sendDirectMessageToAUser() {
        SlackUser user = session.findUserByUserName(userWhoWillRecieve);
        session.sendMessageToUser(user, "" + count++, null);
    }

}
