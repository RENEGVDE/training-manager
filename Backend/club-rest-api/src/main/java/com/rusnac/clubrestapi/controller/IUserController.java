package com.rusnac.clubrestapi.controller;

import com.rusnac.clubrestapi.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserController {
    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<String> authenticate(@RequestBody User user);
}
