package com.jh.lebid.sheduler;

import com.jh.lebid.integration.slack.SlackUserMessageSender;
import com.jh.lebid.rest.client.RestClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SlackMessageSendSheduler {

    private final SlackUserMessageSender slackUserMessageSender;

    private final RestClient restClient;

    @Autowired
    public SlackMessageSendSheduler(SlackUserMessageSender slackUserMessageSender,
                                    RestClient restClient) {
        this.slackUserMessageSender = slackUserMessageSender;
        this.restClient = restClient;
    }

    @Scheduled(fixedDelay = 20000)
    public void sendSimpleMessageToSlckUser() {
        ResponseEntity<String> messageFromRabotaUa = restClient.retrieveJobs();
        JSONArray jsonArray = new JSONObject(messageFromRabotaUa.getBody()).getJSONArray("documents");
        String firstJob = String.valueOf(jsonArray.get(1));
        slackUserMessageSender.sendDirectMessageToAUser(firstJob);
    }
}
