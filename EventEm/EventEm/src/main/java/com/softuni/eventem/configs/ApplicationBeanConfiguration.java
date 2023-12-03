package com.softuni.eventem.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {
@Bean
public ModelMapper getModelMapper(){return new ModelMapper();}

}
