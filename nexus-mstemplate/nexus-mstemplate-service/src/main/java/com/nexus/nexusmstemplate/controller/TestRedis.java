package com.nexus.nexusmstemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexuscommonredis.service.RedisService;

@RestController
@RequestMapping("/test/redis")
public class TestRedis {
    @Autowired
    RedisService redisService;

    @RequestMapping("/kv/expire1")
    public void expire1(){
        redisService.expire("key1", 5);
    }
}
