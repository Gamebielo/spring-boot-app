package br.gov.sp.fatec.springbootapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin // Permite o acesso externo a todos os métodos da classe (CORS error)
public class HomeController {
    
    @GetMapping // Indica que irá responder a uma requisição do tipo GET
    public String welcome(){
        return "Hello World!";
    }
}