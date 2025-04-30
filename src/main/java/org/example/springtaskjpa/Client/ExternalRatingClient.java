package org.example.springtaskjpa.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "external-rating-client", url = "${external.rating.service.api}")
public interface ExternalRatingClient {

    @GetMapping("/service")
    String fetchRating();
}
