package br.com.luaccminerva.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeiraRota")
//http://localhost:8080/ -----
public class MinhaPrimeiraController {

    /*
        MÉTODOS DE ACESSO DO HTTP

        * GET: Buscar uma informação dentro da aplicação
        * POST: Adicionar um dado/info
        * PUT: Alterar uma dado/info
        * DELETE: Remover um dado/info
        * PATCH: Alterar somente uma parte do dado/info
    */

    // MÉTODO (FUNCIONALIDADE) DE UMA CLASSE

    @GetMapping ("/primeiroMetodo")
    public String primeiraMensagem () {
        return "Funcionou";
    }
}
