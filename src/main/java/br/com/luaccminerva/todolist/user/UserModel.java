package br.com.luaccminerva.todolist.user;

import lombok.Data;

/* 
    Essa annotation Data adiciona automaticamente os getters e setters da classe.
    
    @Getter: apenas os getters
    @Setter: apenas os setters
    @Data: getters e setters
*/

@Data
public class UserModel {
    private String username;
    private String name;
    private String password;
}

