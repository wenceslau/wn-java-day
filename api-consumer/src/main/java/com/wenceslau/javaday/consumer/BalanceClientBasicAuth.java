package com.wenceslau.javaday.consumer;

import com.wenceslau.models.BalanceRequest;
import com.wenceslau.models.BalanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@FeignClient(name = "balance-api-basic-auth", url = "http://localhost:8082", path = "balance")
public interface BalanceClientBasicAuth {

    @RequestMapping(method = GET, value = "/path/{date}/{account}/{currency}")
    BalanceResponse getBalancePath(@PathVariable String date,
                                   @PathVariable String account,
                                   @PathVariable String currency,
                                   @RequestHeader String language,
                                   @RequestHeader String authorization);

    @RequestMapping(method = POST, value = "/body", consumes = "application/json", produces = "application/json")
    BalanceResponse getBalanceBody(@RequestBody BalanceRequest request,
                                   @RequestHeader String language,
                                   @RequestHeader String authorization);

    @RequestMapping(method = GET, value = "/params")
    BalanceResponse getBalanceParams(@SpringQueryMap BalanceRequest request,
                                     @RequestHeader String language,
                                     @RequestHeader String authorization);


}
