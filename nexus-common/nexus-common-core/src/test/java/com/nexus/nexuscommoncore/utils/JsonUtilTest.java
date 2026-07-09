package com.nexus.nexuscommoncore.utils;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nexus.nexuscommoncore.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtilTest {
    @Test
    void testObject2String() {
        User user = new User("zhangsan", 19, "test", "China", "school");
        String ret = JsonUtil.object2String(user);
        int a = 10;
    }

    @Test
    void testObject2StringPretty() {
        User user = new User("zhangsan", 19, "test", "China", "school");
        String ret =JsonUtil.object2StringPretty(user);
        int a = 10;
    }

    @Test
    void testString2Object(){
        List<User> users = JsonUtil.string2Object("{\"name\":\"zhangsan\",\"age\":19,\"nickname\":\"test\",\"nation\":\"China\",\"school\":\"school\"}", List.class);
        String ret = JsonUtil.object2String(users);
        int a = 10;
    }

    @Test
    void testString2List(){
        List<User> users = JsonUtil.string2List("[{\"name\":\"zhangsan\",\"age\":19,\"nickname\":\"test\",\"nation\":\"China\",\"school\":\"school\"}]", User.class);
        String ret = JsonUtil.object2String(users);
        int a = 10;
    }

    @Test
    void testString2Map(){
        Map<String, User> map = JsonUtil.string2Map("{\"user1\": {\"name\":\"zhangsan\",\"age\":19,\"nickname\":\"test\",\"nation\":\"China\",\"school\":\"school\"}}", String.class, User.class);
        String ret = JsonUtil.object2String(map);
        int a = 10;
    }

    @Test
    void testString2Obj(){
        Map<String, List<User>> map = JsonUtil.string2Obj("{\"user1\": [{\"name\":\"zhangsan\",\"age\":19,\"nickname\":\"test\",\"nation\":\"China\",\"school\":\"school\"}]}", new TypeReference<Map<String, List<User>>>() {});
        String ret = JsonUtil.object2String(map);
        int a = 10;
    }
}
