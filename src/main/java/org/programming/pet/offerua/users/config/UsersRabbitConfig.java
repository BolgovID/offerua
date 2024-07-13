package org.programming.pet.offerua.users.config;

import org.programming.pet.offerua.common.rabbit.RabbitConst;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class UsersRabbitConfig {
    @Bean
    public Binding verificationBinding() {
        return BindingBuilder.bind(verificationQueue())
                .to(mailerExchange())
                .with(RabbitConst.VERIFICATION_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding resetPasswordBinding() {
        return BindingBuilder.bind(resetPasswordQueue())
                .to(mailerExchange())
                .with(RabbitConst.RESET_PASSWORD_ROUTING_KEY)
                .noargs();
    }

    @Bean
    Exchange mailerExchange() {
        return ExchangeBuilder.directExchange(RabbitConst.MAILER_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Queue verificationQueue() {
        return QueueBuilder.durable(RabbitConst.VERIFICATION_QUEUE)
                .build();
    }

    @Bean
    public Queue resetPasswordQueue() {
        return QueueBuilder.durable(RabbitConst.RESET_PASSWORD_QUEUE)
                .build();
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}