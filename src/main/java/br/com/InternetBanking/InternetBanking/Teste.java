package br.com.InternetBanking.InternetBanking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teste {
    @GetMapping("/")
    public  String obterBemVindo(){
        return "Bem vindo a Spring Boot";
    }
}
