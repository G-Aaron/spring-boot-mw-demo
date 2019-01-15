package org.grp.test.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p>Title: ${file_name}</p>
 * <p>Description: </p>
 *
 * @version 1.0
 * @author: gaorenpeng
 * @date: 2018-08-14 14:54
 **/
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"org.grp.test.mysql"})
@ComponentScan(basePackages = {"org.grp.test.mysql"})
@EntityScan(basePackages ={"org.grp.test.mysql"})
public class MysqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
    }
}
