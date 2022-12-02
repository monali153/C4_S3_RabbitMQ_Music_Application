package com.example.challenge.User_Authentication.rabbitmq_reciever;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    @Bean
    public Jackson2JsonMessageConverter getProducerJacksonConvertor(){

        return new Jackson2JsonMessageConverter();
    }
}
