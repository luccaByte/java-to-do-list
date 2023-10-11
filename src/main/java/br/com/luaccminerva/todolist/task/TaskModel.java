package br.com.luaccminerva.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;


    /*
     * ID
     * Usuário (ID_USUARIO)
     * Descrição
     * Título
     * Data de início
     * Data de término
     * Propriedade
     */


public class TaskModel {
    
     private UUID id;
     private String description;
     private String title;
     private LocalDateTime startAt;
     private LocalDateTime endAt;
     private String priority;

     private LocalDateTime createdAt;
     private UUID idUser;
}
