package com.example.challenge.UserTrackService.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {

    //creating exchange queue, Jackson, binding, rabbit template

    private String exchange_name = "user_exchange";
    private String register_queue = "user_queue";

    @Bean
    public DirectExchange getDirectExchange(){
        return new DirectExchange(exchange_name);
    }

    @Bean
    public Queue getQueueExchange(){
        return new Queue(register_queue);
    }

    @Bean
    public Jackson2JsonMessageConverter getProducerJacksonConvertor(){

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding getBinding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("user_routing");
    }

    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getProducerJacksonConvertor());
        return rabbitTemplate;
    }
}
