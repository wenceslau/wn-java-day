package com.wenceslau.javaday.consumer;

import com.wenceslau.models.BalanceRequest;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@EnableFeignClients
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public ApplicationRunner run(BalanceClient balanceClient) {
        return args -> {

            var date = LocalDate.of(2021, 1, 1);
            var strDate = date.toString();
            var account = "1234567890";
            var currency = "USD";
            var language = "en";

            var response = balanceClient.getBalancePath(strDate, account, currency, language);
            System.out.println(response);

            var request = new BalanceRequest(date, account, currency);
            response = balanceClient.getBalanceBody(request, language);
            System.out.println(response);

            response = balanceClient.getBalanceSpringParams(request, language);
            System.out.println(response);

            response = balanceClient.getBalanceParams(date, account, currency, language);
            System.out.println(response);

        };
    }
}
