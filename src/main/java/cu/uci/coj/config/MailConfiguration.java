package cu.uci.coj.config;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {

	@Resource
	protected Environment env;

	@Bean
	public JavaMailSenderImpl mailSender() {
		JavaMailSenderImpl bean = new JavaMailSenderImpl();

		bean.setHost(env.getProperty("mail.host"));
		bean.setPort(Integer.valueOf(env.getProperty("mail.port")));
		bean.setUsername(env.getProperty("mail.username"));
		bean.setPassword(env.getProperty("mail.password"));
		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth.mechanisms", "LOGIN PLAIN");
		javaMailProperties.put("mail.smtp.starttls.required", "true");
		javaMailProperties.put("mail.smtp.ssl.trust", "*");
		bean.setJavaMailProperties(javaMailProperties);

		return bean;
	}
}
