package com.wenceslau.javaday.consumer;

import com.wenceslau.models.BalanceRequest;
import com.wenceslau.models.BalanceResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class BalanceClient {

    private final BalanceClientNoAuth clientNoAuth;

    public BalanceClient(BalanceClientNoAuth clientNoAuth) {
        this.clientNoAuth = clientNoAuth;
    }

    public BalanceResponse getBalancePath(String date, String account, String currency, String language) {

        return clientNoAuth.getBalancePath(date, account, currency, language);
    }

    public BalanceResponse getBalanceBody(BalanceRequest request, String language) {

        return clientNoAuth.getBalanceBody(request, language);
    }

    public BalanceResponse getBalanceParams(LocalDate date, String account, String currency, String language) {

        Map<String, Object> params = Map.of(
                "date", date,
                "account", account,
                "currency", currency);

        return clientNoAuth.getBalanceParams(params, language);
    }

    public BalanceResponse getBalanceSpringParams(BalanceRequest request, String language) {

        return clientNoAuth.getBalanceSpringParams(request, language);
    }

}
