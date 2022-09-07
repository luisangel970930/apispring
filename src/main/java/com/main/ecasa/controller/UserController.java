package com.main.ecasa.controller;

import com.main.ecasa.model.Appuser;
import com.main.ecasa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ecasa")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping( path = "/users")
    public ResponseEntity<List<Appuser>>getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());

    }
}
