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

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRabbit
public class UsersRabbitConfig {

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Exchange mailerExchange() {
        return ExchangeBuilder.directExchange(RabbitConst.MAILER_EXCHANGE)
                .durable(true)
                .build();
    }

    @Bean
    public Exchange mailerDlx() {
        return ExchangeBuilder.directExchange(RabbitConst.MAILER_DL_EXCHANGE)
                .durable(true)
                .build();
    }

    //Verification configuration with dead letter queue
    @Bean
    public Binding verificationBinding() {
        return BindingBuilder.bind(verificationQueue())
                .to(mailerExchange())
                .with(RabbitConst.VERIFICATION_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding verificationDlqBinding() {
        return BindingBuilder.bind(verificationDlq())
                .to(mailerDlx())
                .with(RabbitConst.VERIFICATION_DL_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Queue verificationDlq() {
        return QueueBuilder.durable(RabbitConst.VERIFICATION_DLQ)
                .build();
    }

    @Bean
    public Queue verificationQueue() {
        return QueueBuilder.durable(RabbitConst.VERIFICATION_QUEUE)
                .withArguments(deadLetterVerificationArguments())
                .build();
    }

    //Reset Password Binding with dead letter queue

    @Bean
    public Binding resetPasswordBinding() {
        return BindingBuilder.bind(resetPasswordQueue())
                .to(mailerExchange())
                .with(RabbitConst.RESET_PASSWORD_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Binding resetPasswordDlqBinding() {
        return BindingBuilder.bind(resetPasswordDlq())
                .to(mailerDlx())
                .with(RabbitConst.RESET_PASSWORD_DL_ROUTING_KEY)
                .noargs();
    }

    @Bean
    public Queue resetPasswordQueue() {
        return QueueBuilder.durable(RabbitConst.RESET_PASSWORD_QUEUE)
                .withArguments(deadLetterResetPasswordArguments())
                .build();
    }

    @Bean
    public Queue resetPasswordDlq() {
        return QueueBuilder.durable(RabbitConst.RESET_PASSWORD_DLQ)
                .build();
    }

    private Map<String, Object> deadLetterVerificationArguments() {
        var arguments = new HashMap<String, Object>();
        arguments.put(RabbitConst.DLX_ARG, RabbitConst.MAILER_DL_EXCHANGE);
        arguments.put(RabbitConst.DL_ROUTING_ARG, RabbitConst.VERIFICATION_DL_ROUTING_KEY);
        return arguments;
    }

    private Map<String, Object> deadLetterResetPasswordArguments() {
        var arguments = new HashMap<String, Object>();
        arguments.put(RabbitConst.DLX_ARG, RabbitConst.MAILER_DL_EXCHANGE);
        arguments.put(RabbitConst.DL_ROUTING_ARG, RabbitConst.RESET_PASSWORD_DL_ROUTING_KEY);
        return arguments;
    }
}