package com.auth.jwt.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//? questo controller servirà per ritornare semplici messaggi
@RestController
public class MessagesController {

    @GetMapping("/messages")
    public ResponseEntity<List<String>> messages() {
        //? Ritorno un array di stringhe
        return ResponseEntity.ok(Arrays.asList("first",  "second"));
    } //per testare disattivare dal POM Jpa poichè non abbiamo bisogno di un database per ora

}
