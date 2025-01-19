package com.wenceslau.javaday.consumer;

import com.wenceslau.models.BalanceRequest;
import com.wenceslau.models.BalanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "balance-api", url = "http://localhost:8081")
public interface Client {

    @RequestMapping(method = GET, value = "/balance/{date}/{account}/{currency}")
    BalanceResponse getBalance(@PathVariable String date,
                               @PathVariable String account,
                               @PathVariable String currency,
                               @RequestHeader String language);

    @RequestMapping(method = POST, value = "/balance")
    BalanceResponse getBalance(@RequestBody BalanceRequest request,
                               @RequestHeader String language);

}
