package com.nexus.nexuscommonredis.service;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings({"rawtypes", "null"})
public class RedissonService {
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * Redis 操作客户端
     */
    private RedissonClient redissonClient;

    /**
     * 获取分布式锁
     * 
     * @param lockKey       锁的唯一标识，建议：模块名 + 服务名 + 唯一键
     * @param expire        过期时间，单位是毫秒
     * @return              分布式锁
     */
    public RLock aquire(String lockKey, long expire){
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(expire, TimeUnit.MILLISECONDS);
        return lock;
    }

    /**
     * 释放锁
     * 
     * @param lock          锁实例
     * @return              解锁是否成功（是否线程对应的释放锁）
     */
    public boolean releaseLock(RLock lock){
        if(lock.isHeldByCurrentThread()){
            lock.unlock();
            return true;
        }
        return false;
    }

    /**
     * 引入 Lua 脚本，删除指定值对应的 Redis 中的键值（compare and delete）
     *
     * @param key   缓存key
     * @param value value
     * @return 是否完成了比较并删除
     */
    public boolean cad(String key, String value) {
        if (key.contains(StringUtils.SPACE) || value.contains(StringUtils.SPACE)) {
            return false;
        }
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then returnredis.call('del', KEYS[1]) else return 0 end";
        // 通过lua脚本原子验证令牌和删除令牌
        Long result = (Long) redisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
                Collections.singletonList(key),
                value);
        return !Objects.equals(result, 0L);
    }
}
