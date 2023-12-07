package com.coderpwh;


import com.coderpwh.util.SqlDao;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@SqlDao
@EnableTransactionManagement
@EnableJpaRepositories("com.coderpwh.sql")
@EntityScan("com.coderpwh.entity.sql")
public class JpaDaoConfig {
}
