package com.wenceslau.models;

import java.time.LocalDateTime;

public record BalanceResponse(
        String account,
        String currency,
        double balance,
        LocalDateTime balanceDate) {

}
