package com.wenceslau.models;

import java.time.LocalDate;

public record BalanceRequest(
        LocalDate date,
        String account,
        String currency) {

    public BalanceRequest {
        if (account == null || account.isBlank()) {
            throw new IllegalArgumentException("Account cannot be null or empty");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
    }
}
