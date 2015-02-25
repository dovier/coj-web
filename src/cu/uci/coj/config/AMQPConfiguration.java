/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cu.uci.coj.utils.UEngineMessageListener;

@Configuration
public class AMQPConfiguration {

	@Bean
	public CachingConnectionFactory connectionFactory() {
		// conexion a localhost:5672, default. TODO: hacer configurable todo
		// esto
		CachingConnectionFactory bean = new CachingConnectionFactory("localhost", 5672);
		bean.setUsername("guest");
		bean.setPassword("cojrabbit123*-+");
		return bean;
	}

	@Bean
	public RabbitAdmin rabbitAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate bean = new RabbitTemplate(connectionFactory());
		bean.setMessageConverter(jsonMessageConverter());
		// para enviar, el binding del exchange se configura en el rabbitmq
		// server a la cola correspondiente (que aqui no interesa)
		bean.setExchange("submit.exchange");
		return bean;
	}

	@Autowired
	private UEngineMessageListener messageListener;

	@Bean
	public JsonMessageConverter jsonMessageConverter() {
		JsonMessageConverter bean = new JsonMessageConverter();

		return bean;
	}

	@Bean
	public SimpleMessageListenerContainer listenerContainer() {
		SimpleMessageListenerContainer bean = new SimpleMessageListenerContainer(connectionFactory());
		bean.setMessageListener(messageListener);
		bean.setQueueNames("submit.response.queue");
		bean.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return bean;
	}

}
