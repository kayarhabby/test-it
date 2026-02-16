package com.example.testit;

import com.example.testit.model.AppUser;
import com.example.testit.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_shouldPersistUser() {
        AppUser user = new AppUser("testuser");
        AppUser saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getUsername()).isEqualTo("testuser");
    }

    @Test
    void findByUsername_shouldReturnUser_whenExists() {
        AppUser user = userRepository.save(new AppUser("findme"));

        AppUser found = userRepository.findByUsername("findme");

        assertThat(found).isNotNull();
        assertThat(found.getUsername()).isEqualTo("findme");
    }

    @Test
    void findByUsername_shouldReturnNull_whenNotExists() {
        AppUser found = userRepository.findByUsername("nonexistent");

        assertThat(found).isNull();
    }

    @Test
    void findById_shouldReturnUser_whenExists() {
        AppUser saved = userRepository.save(new AppUser("findbyid"));

        Optional<AppUser> found = userRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("findbyid");
    }

    @Test
    void existsById_shouldReturnTrue_whenExists() {
        AppUser saved = userRepository.save(new AppUser("exists"));

        boolean exists = userRepository.existsById(saved.getId());

        assertThat(exists).isTrue();
    }

    @Test
    void existsById_shouldReturnFalse_whenNotExists() {
        boolean exists = userRepository.existsById(999L);

        assertThat(exists).isFalse();
    }

    @Test
    void save_shouldPersistUserWithManager() {
        AppUser manager = userRepository.save(new AppUser("manager"));

        AppUser subordinate = new AppUser("sub");
        subordinate.setManager(manager);
        AppUser saved = userRepository.save(subordinate);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getManager().getUsername()).isEqualTo("manager");
    }
    @Test
    void save_shouldPersistUserWithoutManager() {
        AppUser manager = userRepository.save(new AppUser("manager"));


        assertThat(manager.getId()).isNotNull();
    }

    @Test
    void selfReference_shouldAllowUserAsOwnManager() {
        AppUser user = new AppUser("selfmanager");
        user.setManager(user);  // Auto-référence
        AppUser saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getManager().getUsername()).isEqualTo("selfmanager");
    }
}
