package org.example.springtaskjpa.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "external-rating-client", url = "${external.rating.service.api}")
public interface ExternalRatingClient {

    @GetMapping("/service")
    String fetchRating();
}
