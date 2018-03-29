package com.jh.lebid.sheduler;

import com.jh.lebid.cache.VacancyCache;
import com.jh.lebid.dto.Vacancy;
import com.jh.lebid.integration.slack.SlackUserMessageSender;
import com.jh.lebid.parser.JsonVacancyParser;
import com.jh.lebid.rest.client.RestClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeSet;

@Component
public class SlackMessageSendSheduler {

    private final SlackUserMessageSender slackUserMessageSender;

    private final RestClient restClient;

    private final JsonVacancyParser jsonVacancyParser;
    private final VacancyCache vacancyCache;

    @Autowired
    public SlackMessageSendSheduler(SlackUserMessageSender slackUserMessageSender,
                                    RestClient restClient,
                                    JsonVacancyParser jsonVacancyParser,
                                    VacancyCache vacancyCache) {
        this.slackUserMessageSender = slackUserMessageSender;
        this.restClient = restClient;
        this.jsonVacancyParser = jsonVacancyParser;
        this.vacancyCache = vacancyCache;
    }

    @Scheduled(fixedDelay = 2000)
    public void sendSimpleMessageToSlackUser() {
        ResponseEntity<String> messageFromRabotaUa = restClient.retrieveJobs();
        JSONArray jsonArray = new JSONObject(messageFromRabotaUa.getBody()).getJSONArray("documents");
        TreeSet<Vacancy> vacancies = new TreeSet<>(jsonVacancyParser.parseJsonToList(jsonArray));
        List<Vacancy> newestVacancies = vacancyCache.getNewestVacanciesFromCache(vacancies);
        if (!newestVacancies.isEmpty()) {
            slackUserMessageSender.sendDirectMessageToAUser(newestVacancies.toString());
        }
    }
}
