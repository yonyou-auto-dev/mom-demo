package org.ben.mom.producer.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 扫描 mapper
 * 
 * @author BENJAMIN
 *
 */
@Configuration
@MapperScan("org.ben.mom.producer.mapper")
public class MybatisMapperConfig {

}
