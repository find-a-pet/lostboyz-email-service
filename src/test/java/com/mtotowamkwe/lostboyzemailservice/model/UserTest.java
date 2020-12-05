package com.mtotowamkwe.lostboyzemailservice.model;

import com.mtotowamkwe.lostboyzemailservice.config.Config;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.BeanTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.SerializationUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class UserTest {

    private User user;

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
    public void testIfUserIsSerializable() {
        final byte[] serializedUser = SerializationUtils.serialize(user);
        final User deserializedUser = (User) SerializationUtils.deserialize(serializedUser);
        assertEquals(user, deserializedUser);
    }

    @Test
    public void testGettersAndSettersUsingMeanBean() throws Exception {
        new BeanTester().testBean(User.class);
    }

    @Test
    public void testEqualsAndHashCodeWithEqualsVerifier() throws Exception {
        EqualsVerifier.simple().forClass(User.class).verify();
    }

}