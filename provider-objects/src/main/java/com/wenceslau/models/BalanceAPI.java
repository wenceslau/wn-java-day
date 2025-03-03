package com.wenceslau.models;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
