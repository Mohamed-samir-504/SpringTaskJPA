package org.example.springtaskjpa.Services;

import org.example.springtaskjpa.FeignClients.ExternalRatingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExternalRatingService {

    @Autowired
    private ExternalRatingClient externalRatingClient;

    public ExternalRatingService(ExternalRatingClient externalRatingClient) {

        this.externalRatingClient = externalRatingClient;
    }

    public String getService() {
        return externalRatingClient.fetchRating();
    }
}
