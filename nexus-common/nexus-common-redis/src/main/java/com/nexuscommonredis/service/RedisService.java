package com.nexuscommonredis.service;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nexus.nexuscommoncore.utils.JsonUtil;

@Component
@SuppressWarnings({"unchecked", "rawtypes", "null"})
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    //*************** */
    // 键值对操作
    //*************** */

    /**
     * 设置键值对过期时间（默认时间颗粒度秒）
     * 
     * @param key       键
     * @param timeout   值
     * @return          是否成功设置键值对
     */
    public boolean expire(final String key, final long timeout){
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置键值对过期时间（指定时间颗粒度）
     * 
     * @param key       键
     * @param timeout   值
     * @param unit      时间颗粒度
     * @return          是否成功设置键值对
     */
    public boolean expire(final String key, final long timeout, TimeUnit unit){
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获取过期时间
     * 
     * @param key   键值对主键
     * @return      过期时间
     */
    public long getExpire(final String key){
        return redisTemplate.getExpire(key);
    }

    /**
     * 检查是否存在某个键值对
     * 
     * @param key   键值对主键
     * @return      是否存在
     */
    public boolean hasKey(final String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据匹配规则查询所有 key
     * 
     * @param patteString   匹配规则
     * @return              匹配到的键值对主键
     */
    public Collection<String> keys(final String patteString){
        return redisTemplate.keys(patteString);
    }

    /**
     * 重命名键值对
     * 
     * @param oldName   旧名
     * @param newName   新名
     */
    public void renameKey(final String oldName, final String newName){
        redisTemplate.rename(oldName, newName);
    }    

    /**
     * 删除单个键值对
     * 
     * @param key 键值对主键
     * @return    删除结果
     */
    public boolean deleteObject(final String key){
        return redisTemplate.delete(key);
    }

    /**
     * 删除多个键值对
     * 
     * @param keys
     * @return
     */
    public boolean deleteObject(final Collection<String> keys){
        return redisTemplate.delete(keys) > 0;
    }

    //*************** */    
    // String 操作
    //*************** */    

    /**
     * 将 obj 序列化为 Json 存储进 redis
     * 
     * @param <T>   缓存对象类型
     * @param key   缓存对象对应主键
     * @param obj   缓存对象
     */
    public <T> void setCacheObject(final String key, T obj){
        redisTemplate.opsForValue().set(key, obj);
    }

    /**
     * 将 obj 序列化为 Json 存储进 Redis，同时指定过期时间与时间颗粒度
     * 
     * @param <T>       缓存对象类型
     * @param key       缓存对象对应主键
     * @param obj       缓存对象
     * @param timeout   过期时间
     * @param unit      时间颗粒度
     */
    public <T> void setCacheObject(final String key, T obj, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().set(key, obj, timeout, unit);
    }

    /**
     * 如果对应 key 不存在，则将 obj 序列化为 Json 存储进 Redis，同时指定过期时间与时间颗粒度
     * 
     * @param <T>
     * @param key
     * @param obj
     * @param timeout
     * @param unit
     */
    public <T> void setCacheObjectIfAbsent(final String key, T obj, long timeout, TimeUnit unit){
        redisTemplate.opsForValue().setIfAbsent(key, obj, timeout, unit);
    }

    /**
     * 获取 key 对应的缓存对象
     * 
     * @param <T>   缓存对象类型
     * @param key   键值对键
     * @param clazz 缓存对象类对象
     * @return      获得的缓存对象
     */
    public <T> T getCacheObject(final String key, Class<T> clazz){
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        T t = (T)operation.get(key);
        if(t == null){  //解析失败
            return null;
        }
        return JsonUtil.string2Object(JsonUtil.object2String(t), clazz);
    }

    /**
     * 获取 key 对应的缓存对象（支持复杂类型）
     * 
     * @param <T>
     * @param key
     * @param reference
     * @return
     */
    public <T> T getCacheObject(final String key, TypeReference<T> reference){
        Object obj = redisTemplate.opsForValue().get(key);
        T t = (T)obj;
        if(obj == null) return null;
        return JsonUtil.string2Object(JsonUtil.object2String(t), reference);
    }

    //************** */
    // list 操作
    //************** */
    
    public <T> long setCacheList(final String key, final List<T> dataList){

    }
}
