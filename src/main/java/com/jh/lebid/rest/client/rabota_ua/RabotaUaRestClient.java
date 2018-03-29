package com.jh.lebid.rest.client.rabota_ua;

import com.jh.lebid.rest.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RabotaUaRestClient implements RestClient {

    @Value("${rabota_ua.base.api}")
    private String rabotaUaBaseApi;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> retrieveJobs() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = getSearchQueryMultiMap();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        return restTemplate.postForEntity(rabotaUaBaseApi + "/vacancy/search", request, String.class);
    }

    private MultiValueMap<String, String> getSearchQueryMultiMap() {
        MultiValueMap<String, String> resultQuery = new LinkedMultiValueMap<>();
        resultQuery.add("keyWords", "java");
        resultQuery.add("cityName", "Харьков");
        resultQuery.add("cityId", "21");
        return resultQuery;
    }

}
