package com.mtotowamkwe.lostboyzemailservice.service;

import com.mtotowamkwe.lostboyzemailservice.config.Config;
import com.mtotowamkwe.lostboyzemailservice.model.Email;
import com.mtotowamkwe.lostboyzemailservice.model.User;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class EmailServiceTest {

    private User user;

    private Context context = new Context();

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Before
    public void setUp() throws Exception {
        user = new User();
        user.setCode(Constants.TEST_USER_CODE);
        user.setName(Constants.TEST_USER_NAME);
        user.setEmail(Constants.TEST_USER_EMAIL);
    }

    @Test
    public void testThatUserIsNotNull() {
        assertNotNull(user);
    }

    @Test
    public void testUserContainsEmail() {
        assertEquals(user.getEmail(), Constants.TEST_USER_EMAIL);
    }

    @Test
    public void testUserContainsCode() {
        assertEquals(user.getCode(), Constants.TEST_USER_CODE);
    }

    @Test
    public void testUserContainsName() {
        assertEquals(user.getName(), Constants.TEST_USER_NAME);
    }

    @Test
    public void testIsInstanceOfUser() {
        assertThat(user, instanceOf(User.class));
    }

    @Test
    public void testThatEmailSenderIsNotNull() {
        assertNotNull(emailSender);
    }

    @Test
    public void testIsInstanceOfJavaMailSender() {
        assertThat(emailSender, instanceOf(JavaMailSender.class));
    }

    @Test
    public void testThatTemplateEngineIsNotNull() {
        assertNotNull(templateEngine);
    }

    @Test
    public void testIsInstanceOfSpringTemplateEngine() {
        assertThat(templateEngine, instanceOf(SpringTemplateEngine.class));
    }

    @Test
    public void testThatContextIsNotNull() {
        assertNotNull(context);
    }

    @Test
    public void testIsInstanceOfContext() {
        assertThat(context, instanceOf(Context.class));
    }

    @Test
    public void sendSimpleMessage() {
        User aUser = new EmailService(emailSender, templateEngine).sendSimpleMessage(user);
        assertNotNull(aUser);
        assertThat(aUser, instanceOf(User.class));
        assertThat(aUser).usingRecursiveComparison().isEqualTo(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        User emptyUser = new User();
        EmailService emailService = new EmailService(emailSender, templateEngine);
        emailService.sendSimpleMessage(emptyUser);
    }

    @Test
    public void emailFactory() {
        Email email = new EmailService(emailSender, templateEngine).emailFactory(user);
        assertNotNull(email);
        assertThat(email, instanceOf(Email.class));
    }
}