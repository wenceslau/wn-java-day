package com.wenceslau.javaday.provider.jwtauth;

import com.wenceslau.javaday.provider.jwtauth.security.JwtTokenService;
import com.wenceslau.models.BalanceAPI;
import com.wenceslau.models.BalanceRequest;
import com.wenceslau.models.BalanceResponse;
import com.wenceslau.models.CredentialRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

@RestController
public class BalanceController implements BalanceAPI {

    private static final Logger log = LoggerFactory.getLogger(BalanceController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public BalanceController(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CredentialRequest request) {
        try {
            // Autentica o usuário
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );

            // Gera o token JWT
            var token = jwtTokenService.generateToken(authentication.getName());

            // Retorna o token no corpo da resposta
            return ResponseEntity
                    .status(200)
                    .body(Map.of("token", token));

        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(401)
                    .body(Map.of("error", "Usuário ou senha inválidos"));
        }
    }

}
