package com.mtotowamkwe.lostboyzemailservice.model;

import com.mtotowamkwe.lostboyzemailservice.config.Config;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.SerializationUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class EmailTest {

    @Autowired
    private SpringTemplateEngine template;

    private Map model;
    private Email email;
    private Context context;

    @Before
    public void setUp() throws Exception {
        model = Map.of(Constants.USER_NAME_KEY, Constants.TEST_USER_NAME,
                Constants.USER_CODE_KEY, Constants.TEST_USER_CODE);
        context = new Context();
        context.setVariables(model);
        email = new Email();
        email.setTo(Constants.TEST_USER_EMAIL);
        email.setFrom(Constants.EMAIL_FROM);
        email.setSubject(Constants.EMAIL_SUBJECT);
        email.setModel(model);
        email.setContent(template.process(Constants.TEST_EMAIL_TEMPLATE_NAME,context));
    }

    @Test
    public void testThatTemplateIsNotNull() {
        assertNotNull(template);
    }

    @Test
    public void testIfEmailIsSerializable() {
        final byte[] serializedEmail = SerializationUtils.serialize(email);
        final Email deserializedEmail = (Email) SerializationUtils.deserialize(serializedEmail);
        assertEquals(email, deserializedEmail);
    }

    @Test
    public void testGettersAndSettersUsingMeanBean() throws Exception {
        new BeanTester().testBean(Email.class);
    }

    @Test
    public void testEqualsAndHashCodeWithEqualsVerifier() throws Exception {
        EqualsVerifier.simple().forClass(Email.class).verify();
    }

}