package com.wenceslau.javaday.consumer;

import com.wenceslau.models.BalanceRequest;
import com.wenceslau.models.BalanceResponse;
import feign.QueryMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "balance-api-no-auth", url = "http://localhost:8081", path = "balance")
public interface BalanceClientNoAuth {

    @RequestMapping(method = GET, value = "/path/{date}/{account}/{currency}")
    BalanceResponse getBalancePath(@PathVariable String date,
                                   @PathVariable String account,
                                   @PathVariable String currency,
                                   @RequestHeader String language);

    @RequestMapping(method = POST, value = "/body", consumes = "application/json", produces = "application/json")
    BalanceResponse getBalanceBody(@RequestBody BalanceRequest request,
                                   @RequestHeader String language);

    @RequestMapping(method = GET, value = "/params")
    BalanceResponse getBalanceSpringParams(@SpringQueryMap BalanceRequest request,
                                           @RequestHeader String language);

    @RequestMapping(method = GET, value = "/params")
    BalanceResponse getBalanceParams(@QueryMap Object params,
                                     @RequestHeader String language);



}
