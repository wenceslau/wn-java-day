package com.wenceslau.javaday.provider.noauth.controllers;

import com.wenceslau.models.BalanceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RequestMapping("balance")
public interface BalanceAPI {

    @GetMapping("/path/{date}/{account}/{currency}")
    ResponseEntity<?> getBalance(@PathVariable LocalDate date,
                                 @PathVariable String account,
                                 @PathVariable String currency,
                                 @RequestHeader String language);

    @GetMapping("/params")
    ResponseEntity<?> getBalanceParams(BalanceRequest request,
                                       @RequestHeader String language);

    @PostMapping("/body")
    ResponseEntity<?> getBalance(@RequestBody BalanceRequest request,
                                 @RequestHeader String language);



}
