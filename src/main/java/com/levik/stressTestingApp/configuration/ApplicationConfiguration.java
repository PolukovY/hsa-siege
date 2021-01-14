package com.levik.stressTestingApp.configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.levik.stressTestingApp.entity.UserEntity;
import com.levik.stressTestingApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Configuration
public class ApplicationConfiguration {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }


    @Bean
    public LoadingCache<Integer, Page<UserEntity>> loadingCache(UserRepository userRepository) {
        return CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .refreshAfterWrite(50, TimeUnit.SECONDS)
                .build(new CacheLoader<>() {
                    @Override
                    public Page<UserEntity> load(Integer page) {
                        PageRequest fistPage = PageRequest.of(0, 20, Sort.unsorted());
                        return userRepository.findAll(fistPage);
                    }
                });
    }
}
