package com.example.carros.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String get() {
        return "Get Spring Boot";
    }

    /*
    @GetMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
        return "Login: " + login + ", Senha: " + senha;
    }
    @PostMapping("/login/{login}/senha/{senha}")
    public String login(@PathVariable("login") String login, @PathVariable("senha") String senha) {
        return "Login: " + login + ", Senha: " + senha;
    }*/

    @PostMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
        return "Login: " + login + ", Senha: " + senha;
    }

    @GetMapping("/carros/{id}")
    public String getCarroById(@PathVariable("id") Long id) {
        return "Carro by id: " + id;
    }

    @GetMapping("/carros/tipo/{tipo}")
    public String getCarroByTipo(@PathVariable("tipo") String tipo) {
        return "Lista de carros: " + tipo;
    }
}