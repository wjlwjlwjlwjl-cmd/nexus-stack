package com.nexus.nexuscommoncore.utils;

import org.junit.jupiter.api.Test;

import com.nexus.nexuscommoncore.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtilTest {
    @Test
    void testObject2String() {
        User user = new User("zhangsan", 19, "test", "China", "school");
        log.debug(JsonUtil.object2String(user));
    }

    @Test
    void testObject2StringPretty() {
        User user = new User("zhangsan", 19, "test", "China", "school");
        log.info(JsonUtil.object2StringPretty(user));
    }

    @Test
    void testString2Object(){
        User user = JsonUtil.string2Object("{\"name\": \"zhangsan\", \"age\": 19, \"nickname\": \"test\", \"nation\": \"China\", \"school\": \"school\"}", User.class);
    }
}
