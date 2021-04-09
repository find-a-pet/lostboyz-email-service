package com.mtotowamkwe.lostboyzemailservice.config;

import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
@Component
@ComponentScan({"com.mtotowamkwe.lostboyzemailservice.service", "com.mtotowamkwe.lostboyzemailservice.model.restful"})
public class Config {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }

    @Bean
    public SpringResourceTemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
        emailTemplateResolver.setPrefix(Constants.TEMPLATES);
        emailTemplateResolver.setSuffix(Constants.SUFFIX);
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return emailTemplateResolver;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(host);
        mailSender.setPort(587);
        mailSender.setPassword(password);
        mailSender.setUsername(username);
        mailSender.setProtocol("smtp");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

}