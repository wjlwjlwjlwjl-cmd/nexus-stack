package com.adminservice;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@MapperScan("com.**.mapper")
@SpringBootApplication
@EnableScheduling
public class AdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
        log.info("基础管理服务启动成功");
    }

    /**
     * 分页拦截器
     * @return mapper分页查询
     */
    @Bean
    public PaginationInnerInterceptor paginationInterceptor(){
        return new PaginationInnerInterceptor();
    }
}
