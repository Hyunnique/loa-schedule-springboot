package me.hyunnique.loaschedule.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/character/{query}")
    public ResponseEntity<String> searchCharacter(@PathVariable String query, @Value("${lostark.api-key}") String key) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(key);

        return restTemplate.exchange(RequestEntity.get("https://developer-lostark.game.onstove.com/characters/" + query + "/siblings").headers(headers).build(), String.class);
    }
}
