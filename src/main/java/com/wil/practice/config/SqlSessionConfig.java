package com.wil.practice.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Configuration
public class SqlSessionConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        try(InputStream is = Resources.getResourceAsStream("mybatis.xml")) {
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            return sqlSessionFactory;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
