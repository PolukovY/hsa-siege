package com.levik.stressTestingApp.service;

import com.google.common.cache.LoadingCache;
import com.levik.stressTestingApp.entity.UserEntity;
import com.levik.stressTestingApp.repository.UserRepository;
import com.levik.stressTestingApp.service.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final LoadingCache<Integer, Page<UserEntity>> loadingCache;

    public UserModel create(UserModel userModel) {
        UserEntity userEntity = convertToEntity(userModel);
        UserEntity saveUserEntity = userRepository.save(userEntity);
        return convertToModel(saveUserEntity);
    }

    public Page<UserEntity> getAll(Pageable pageable) {
        Page<UserEntity> isFirstPage = loadingCache.getIfPresent(pageable.getPageNumber());

        if (isFirstPage != null) {
            return isFirstPage;
        }

        log.info("Fetch data from db and put it to cash");
        Page<UserEntity> all = userRepository.findAll(pageable);
        loadingCache.put(pageable.getPageNumber(), all);
        return all;
    }

    private UserEntity convertToEntity(UserModel source) {
        UserEntity target = new UserEntity();
        target.setName(source.getName());
        target.setEmail(source.getEmail());
        return target;
    }

    private UserModel convertToModel(UserEntity source) {
        return new UserModel(source.getId(), source.getName(), source.getEmail());
    }
}
