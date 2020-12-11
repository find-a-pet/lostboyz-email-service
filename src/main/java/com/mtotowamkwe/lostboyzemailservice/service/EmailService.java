package com.mtotowamkwe.lostboyzemailservice.service;

import com.mtotowamkwe.lostboyzemailservice.exception.EmailNotSentException;
import com.mtotowamkwe.lostboyzemailservice.model.Email;
import com.mtotowamkwe.lostboyzemailservice.model.User;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender emailSender;

    private final SpringTemplateEngine templateEngine;

    private Context context = new Context();

    public EmailService(@Qualifier("javaMailSender") JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    public User sendSimpleMessage(User user) {
        Email email = emailFactory(user);

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(email.getTo());
            helper.setFrom(email.getFrom());
            helper.setText(email.getContent(), true);
            helper.setSubject(email.getSubject());

            emailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error at sendSimpleMessage(Email email).", e);
            throw new EmailNotSentException(user);
        }

        return user;
    }

    Email emailFactory(User user) {
        Email email = new Email();

        email.setTo(user.getEmail());
        email.setFrom(Constants.EMAIL_FROM);
        email.setSubject(Constants.EMAIL_SUBJECT);
        email.setModel(new HashMap<String, String>(){{
                put(Constants.USER_CODE_KEY, user.getCode());
            }});

        context.setVariables(email.getModel());

        String htmlContent = templateEngine.process(Constants.EMAIL_TEMPLATE_ENGLISH, context);

        email.setContent(htmlContent);

        return email;
    }
}