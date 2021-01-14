package com.levik.stressTestingApp.controller;

import com.levik.stressTestingApp.entity.UserEntity;
import com.levik.stressTestingApp.service.UserService;
import com.levik.stressTestingApp.service.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserEntity> getAll(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @PostMapping
    public UserModel create(@RequestBody UserModel userModel) {
        return userService.create(userModel);
    }
}
