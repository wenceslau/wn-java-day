package com.wenceslau.javaday.provider.noauth;

import com.wenceslau.models.BalanceAPI;
import com.wenceslau.models.BalanceRequest;
import com.wenceslau.models.BalanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;

@RestController
public class BalanceController implements BalanceAPI {

    private static final Logger log = LoggerFactory.getLogger(BalanceController.class);

    @Override
    public ResponseEntity<?> getBalance(LocalDate date, String account, String currency, String language) {
        log.info("getBalance: date={}, account={}, currency={}, language={}", date, account, currency, language);

        var response = new BalanceResponse(
                account,
                currency,
                new Random().nextDouble(),
                date.atStartOfDay());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getBalance(BalanceRequest request, String language) {
        log.info("getBalance: request={}, language={}", request, language);

        var response = new BalanceResponse(
                request.account(),
                request.currency(),
                new Random().nextDouble(),
                request.date().atStartOfDay());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getBalanceParams(BalanceRequest request, String language) {
        log.info("getBalanceParams: request={}, language={}", request, language);

        var response = new BalanceResponse(
                request.account(),
                request.currency(),
                new Random().nextDouble(),
                request.date().atStartOfDay());

        return ResponseEntity.ok(response);
    }


}
