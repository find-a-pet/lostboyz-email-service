package com.mtotowamkwe.lostboyzemailservice.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class ConfigTest {

    @Autowired
    private SpringTemplateEngine engine;

    @Autowired
    private SpringResourceTemplateResolver resolver;

    @Autowired
    private JavaMailSender sender;

    @Test
    public void templateEngine() {
        assertNotNull(engine);
        assertThat(engine, instanceOf(SpringTemplateEngine.class));
        assertTrue(engine.getTemplateResolvers().contains(resolver));
    }

    @Test
    public void htmlTemplateResolver() {
        assertNotNull(resolver);
        assertThat(resolver, instanceOf(SpringResourceTemplateResolver.class));
    }

    @Test
    public void javaMailSender() {
        assertNotNull(sender);
        assertThat(sender, instanceOf(JavaMailSender.class));
    }
}