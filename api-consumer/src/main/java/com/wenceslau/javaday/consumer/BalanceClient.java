package com.wenceslau.javaday.consumer;

import com.wenceslau.models.BalanceRequest;
import com.wenceslau.models.BalanceResponse;
import com.wenceslau.models.CredentialRequest;
import com.wenceslau.models.CredentialResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
public class BalanceClient {

    private final BalanceClientNoAuth clientNoAuth;
    private final BalanceClientBasicAuth clientBasicAuth;
    private final BalanceClientJwtAuth clientJwtAuth;

    public BalanceClient(BalanceClientNoAuth clientNoAuth, BalanceClientBasicAuth clientBasicAuth, BalanceClientJwtAuth clientJwtAuth) {
        this.clientNoAuth = clientNoAuth;
        this.clientBasicAuth = clientBasicAuth;
        this.clientJwtAuth = clientJwtAuth;
    }

    public BalanceResponse getBalancePath(String date, String account, String currency, String language) {

        return clientNoAuth.getBalancePath(date, account, currency, language);
    }

    public BalanceResponse getBalanceBody(BalanceRequest request, String language) {

        return clientNoAuth.getBalanceBody(request, language);
    }

    public BalanceResponse getBalanceParams(BalanceRequest request, String language) {

        return clientNoAuth.getBalanceParams(request, language);
    }



    public BalanceResponse getBalanceBasicAuthPath(String date, String account, String currency, String language) {

        String authorization = "Basic " + java.util.Base64.getEncoder().encodeToString("user:123".getBytes());
        return clientBasicAuth.getBalancePath(date, account, currency, language, authorization);
    }

    public BalanceResponse getBalanceBasicAuthBody(BalanceRequest request, String language) {

        String authorization = "Basic " + java.util.Base64.getEncoder().encodeToString("user:123".getBytes());
        return clientBasicAuth.getBalanceBody(request, language, authorization);
    }

    public BalanceResponse getBalanceBasicAuthParams(BalanceRequest request, String language) {

        String authorization = "Basic " + java.util.Base64.getEncoder().encodeToString("user:123".getBytes());
        return clientBasicAuth.getBalanceParams(request, language, authorization);
    }



    public BalanceResponse getBalanceJwtAuthBody(BalanceRequest request, String language) {

        CredentialRequest credentialRequest = new CredentialRequest("user", "123");
        CredentialResponse credential = clientJwtAuth.login(credentialRequest);

        String authorization = "Bearer " + credential.token();
        return clientJwtAuth.getBalanceBody(request, language, authorization);
    }

    public BalanceResponse getBalanceJwtAuthParams(BalanceRequest request, String language) {

        CredentialRequest credentialRequest = new CredentialRequest("user", "123");
        CredentialResponse credential = clientJwtAuth.login(credentialRequest);

        String authorization = "Bearer " + credential.token();
        return clientJwtAuth.getBalanceParams(request, language, authorization);
    }

    public BalanceResponse getBalanceJwtAuthPath(String date, String account, String currency, String language) {

        CredentialRequest credentialRequest = new CredentialRequest("user", "123");
        CredentialResponse credential = clientJwtAuth.login(credentialRequest);

        String authorization = "Bearer " + credential.token();
        return clientJwtAuth.getBalancePath(date, account, currency, language, authorization);
    }

}
