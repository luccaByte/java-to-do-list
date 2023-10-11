package br.com.luaccminerva.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;

/*
    Modificadores:
    * public
    * private
    * protected
*/
@RestController
@RequestMapping ("/users")
public class UserController {
    /*
        * String: texto
        * Integer: int, numeros inteiros
        * Double: numeros 0,000
        * Float: numeros 0,00
        * Char: caracteres 
        * Date: data
        * Void: não tem retorno
    */

    @Autowired // gerenciamento todo ciclo de vida
    private iUserRepository userRepository; //chamada do repositório

    @PostMapping("/")
    public ResponseEntity create (@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            // mensagem de erro
            // status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe!");
        }

        var passwordHashred = Bcrypt.withDefaults().hashToString(12,userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashred);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
