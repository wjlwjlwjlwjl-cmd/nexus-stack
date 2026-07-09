package com.nexus.nexuscommoncore.utils;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;

@Slf4j
public class JsonUtil {
    static private ObjectMapper OBJECT_MAPPER;

    static {
    OBJECT_MAPPER =JsonMapper.builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
    .configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
    .configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false)
    .configure(MapperFeature.USE_ANNOTATIONS, false)
    .addModule(new JavaTimeModule())
    .defaultDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")) //魔法值，todo
    .serializationInclusion(JsonInclude.Include.NON_NULL)
    .build();
    }

    /**
     * 
     * @param <T> 转换对象类型
     * @param obj 转换对象
     * @return    转换结果
     */
    public static<T> String object2String(T obj){
        if(obj == null || obj instanceof String){
            return (String)obj;
        }
        try{
            return OBJECT_MAPPER.writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            log.warn("obj transfer to json fail: {}", e.toString());
            return null;
        }
    }

    /**
     * 
     * @param <T> 转换对象类型
     * @param obj 转换对象
     * @return    返回结果（美化）
     */
    public static<T> String object2StringPretty(T obj){
        if(obj == null || obj instanceof String){
            return (String)obj;
        }
        try{
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            log.warn("obj transfer to json fail: {}", e.toString());
            return null;
        }
    }

    /**
     * 
     * @param <T> 转换结果类型
     * @param str 用来转换的 json 串
     * @param clazz 转换结果类型
     * @return 转换对象
     */
    public static<T> T string2Object(String str, Class clazz){
        if(str == null || str.isEmpty() || clazz == null){
            return null;
        }
        try{
            return clazz.equals(String.class) ? (T)str : (T)OBJECT_MAPPER.readValue(str, clazz);
        }
        catch(JsonProcessingException e){
            log.warn("json transfer to obj fail: {}", e.toString());
            return null;
        }
    }
}
