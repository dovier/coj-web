package cu.uci.coj.config;

import cu.uci.coj.model.dto.VerdictDTO;
import cu.uci.coj.utils.UEngineMessageListener;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource({"classpath:cu/uci/coj/config/amqp.config.properties"})
public class AMQPConfiguration {

    @Resource
    protected Environment env;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory bean = new CachingConnectionFactory(env.getProperty("rabbitmq.hostname"), Integer.parseInt(env.getProperty("rabbitmq.port")));
        bean.setUsername(env.getProperty("rabbitmq.username"));
        bean.setPassword(env.getProperty("rabbitmq.password"));
        return bean;
    }

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory());
    }
    
    @Bean
    public RabbitTemplate submitTemplate() {
        RabbitTemplate bean = new RabbitTemplate(connectionFactory());
        bean.setMessageConverter(jsonMessageConverter());
		// para enviar, el binding del exchange se configura en el rabbitmq
        // server a la cola correspondiente (que aqui no interesa)
        bean.setExchange(env.getProperty("submit.exchange"));
        return bean;
    }
    
    @Bean
    public RabbitTemplate plagiarismDetectorTemplate() {
        RabbitTemplate bean = new RabbitTemplate(connectionFactory());
        bean.setMessageConverter(jsonMessageConverter());
        bean.setExchange(env.getProperty("plagiarism.detection.exchange"));
        return bean;
    }

    @Autowired
    private UEngineMessageListener messageListener;

    @Bean
    public DefaultClassMapper typeMapper() {
        DefaultClassMapper typeMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("UEngineVerdict", VerdictDTO.class);
        typeMapper.setIdClassMapping(idClassMapping);
        return typeMapper;
    }

    @Bean
    public JsonMessageConverter jsonMessageConverter() {
        JsonMessageConverter bean = new JsonMessageConverter();
        bean.setClassMapper(typeMapper());
        return bean;
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer bean = new SimpleMessageListenerContainer(connectionFactory());
        bean.setMessageListener(messageListener);
        bean.setQueueNames(env.getProperty("submit.response.queue"));
        bean.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return bean;
    }

}
