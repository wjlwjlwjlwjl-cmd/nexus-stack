package com.nexus.nexusmstemplate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexuscommonredis.service.RedisService;
import com.nexus.nexusmstemplate.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test/redis")
public class TestRedis {
    @Autowired
    private RedisService redisService;

    @PostMapping("/kv/expire1")
    public void expire1(){
        redisService.expire("key1", 5);
    }

    @PostMapping("/kv/set")
    public void set(String key, String val){
        User user1 = new User(val, 10, val);
        User user2 = new User(val, 10, val);
        User user3 = new User(val, 10, val);
        List<User> list = new ArrayList<>();

        list.add(user1);
        list.add(user2);
        list.add(user3);

        redisService.setCacheObjectIfAbsent(key, list);
    }

    @GetMapping("/kv/get")
    public String get(String key){
        String val = redisService.getCacheObject(key, String.class);
        if(val == null){
            log.warn("val == null");
        }
        return val;
    }
}
