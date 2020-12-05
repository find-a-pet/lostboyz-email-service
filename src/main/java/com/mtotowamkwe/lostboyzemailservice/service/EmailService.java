package com.mtotowamkwe.lostboyzemailservice.service;

import com.mtotowamkwe.lostboyzemailservice.exception.EmailNotSentException;
import com.mtotowamkwe.lostboyzemailservice.model.Email;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    @Qualifier("javaMailSender")
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendSimpleMessage(Email email) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(email.getModel());

            String html = templateEngine.process(Constants.EMAIL_TEMPLATE_ENGLISH, context);

            helper.setTo(email.getTo());
            helper.setFrom(email.getFrom());
            helper.setText(html, true);
            helper.setSubject(email.getSubject());

            emailSender.send(message);
        } catch (MessagingException e) {
            log.error("Error at sendSimpleMessage(Email email).", e);
            throw new EmailNotSentException(email);
        }
    }

}
