package com.example.testit.service;

import com.example.testit.adapter.mail.MailService;
import com.example.testit.model.Task;
import com.example.testit.repository.TaskRepository;
import com.example.testit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskServiceUnitaireTest {

    @Test
    void testCreateTask() {
        TaskRepository taskRepo = Mockito.mock(TaskRepository.class);
        UserRepository userRepo = Mockito.mock(UserRepository.class);
        MailService mailService = Mockito.mock(MailService.class);

        TaskService service = new TaskService(taskRepo, userRepo, mailService);

        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("tache 1", "desc", null));
        tasks.add(new Task("tache 2", "desc", null));

        Mockito.when(taskRepo.findAll()).thenReturn(tasks);

        List<Task> resultat = service.findAll();

        assertEquals(2, resultat.size());
    }
}
