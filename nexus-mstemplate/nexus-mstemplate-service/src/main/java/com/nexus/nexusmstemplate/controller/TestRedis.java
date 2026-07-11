package com.nexus.nexusmstemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexuscommonredis.service.RedisService;

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
        redisService.setCacheObject(key, val);
    }
}
