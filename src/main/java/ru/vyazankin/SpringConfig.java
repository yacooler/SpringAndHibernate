package ru.vyazankin;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
@ComponentScan("ru.vyazankin")
public class SpringConfig {

    @Bean
    @Scope("singleton")
    public SessionFactory sessionFactoryBean(){
        System.out.println("sessionFactoryBean");
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
}



