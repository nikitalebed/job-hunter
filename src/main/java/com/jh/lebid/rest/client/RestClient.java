package com.jh.lebid.rest.client;

import org.springframework.http.ResponseEntity;

public interface RestClient {

    ResponseEntity<String> retrieveJobs();

}
