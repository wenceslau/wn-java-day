package com.wenceslau.javaday.consumer;

import org.springframework.stereotype.Service;

@Service
public class ClientUse {

    private final Client client;

    public ClientUse(Client client) {
        this.client = client;
    }

    public void use() {
        var date = "2025-01-01";
        var account = "123456789";
        var currency = "USD";
        var language = "en";

        var response = client.getBalance(date, account, currency, language);

        System.out.println("Balance: " + response.balance());
        System.out.println("Balance Date: " + response.balanceDate());
    }
}
