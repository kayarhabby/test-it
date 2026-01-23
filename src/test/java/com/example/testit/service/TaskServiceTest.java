package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.TaskRepository;
import com.example.testit.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TaskServiceTest {


    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;


//    GET /tasks : Lister toutes les tâches
//    GET /tasks/{id} : Obtenir une tâche par ID
//    GET /tasks/user/{userId} : Lister les tâches d'un utilisateur
//    POST /tasks : Créer une tâche (body: {title, description, userId})
//    PUT /tasks/{id} : Modifier une tâche
//    DELETE /tasks/{id} : Supprimer une tâche


    @Test
    public void itshouldReturnAllTaskInTheDatabase() {

        // Etand donné que
        User user = new User("rhabby");
        Task task = new Task("faire à manger", "préparer du poulet à midi", user);

        // on insere les données dans la base de données
        this.userRepository.save(user);
        User assignedUser = this.userRepository.findByUsername("rhabby");
        Long requesterId = assignedUser.getId();
        Long assignedUserId = assignedUser.getId();

        taskService.createTask(task.getTitle(),task.getDescription(),requesterId, assignedUserId );

        // Quand

        // on récupère l'ensemble des tâches enregistrés dans la base de données
        List<Task> taskList = taskRepository.findAll();

        // On certifie que la liste retournée possède bien la task précédemment enregistré
        Assertions.assertThat(taskList.get(0).getTitle()).isEqualTo(task.getTitle());
    }


}
