package com.example.testit;

import com.example.testit.adapter.user.CurrentUserServiceFake;
import com.example.testit.model.Status;
import com.example.testit.model.Task;
import com.example.testit.model.User;
import com.example.testit.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUserServiceFake currentUserServiceFake;

    private Long userId;

    @BeforeEach
    void setUp() {
        // Créer un utilisateur de test en DB
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String password = passwordEncoder.encode("user123");
        User user = new User("testuser");
        user.setPassword(passwordEncoder.encode("user123"));
        user.setRole("USER");
//        User user = new User("testuser", password,"USER");
        userRepository.save(user);
        userId = user.getId();
        // Set current user for auth
        currentUserServiceFake.setCurrent(userId);
    }

    @Test
    void getAllTasks_shouldReturnEmptyList_initially() throws Exception {
        mockMvc.perform(get("/tasks")
                        .with(user("testuser").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void createTask_shouldCreateTask() throws Exception {
        String taskJson = """
            {
                "title": "Test Task",
                "description": "Test Description"
            }
            """;

        mockMvc.perform(post("/tasks").with(user("testuser").roles("MANAGER"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.status").value("OUVERT"));
    }

    @Test
    void getTasksByUser_shouldReturnUserTasks() throws Exception {
        mockMvc.perform(get("/tasks/user/{userId}", userId)
                        .with(user("testuser").roles("USER"))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    // Pour startTask et finishTask, faudrait créer d'abord la tâche via API, mais pour simplifier.
}
