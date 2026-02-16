package com.example.testit.adapter.user;

import com.example.testit.model.User;

import java.util.Optional;

public interface CurrentUserService {

    /**
     * Retourne l'id de l'utilsiateur connecte
     * @return
     */
    Optional<Long> getCurrentUserId();
}
