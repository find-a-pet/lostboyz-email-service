package com.mtotowamkwe.lostboyzemailservice.controller;

import com.mtotowamkwe.lostboyzemailservice.config.Config;
import com.mtotowamkwe.lostboyzemailservice.model.User;
import com.mtotowamkwe.lostboyzemailservice.model.restful.UserModelAssembler;
import com.mtotowamkwe.lostboyzemailservice.service.EmailService;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class})
public class EmailControllerTest {

    private User user;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserModelAssembler modelAssembler;

    @Before
    public void setUp() {
        user = new User();
        user.setCode(Constants.TEST_USER_CODE);
        user.setEmail(Constants.TEST_USER_EMAIL);
        user.setName(Constants.TEST_USER_NAME);
    }

    @Test
    public void sendEmail() {
        assertNotNull(emailService);
        assertNotNull(modelAssembler);

        EntityModel<User> userEntityModel = modelAssembler.toModel(emailService.sendSimpleMessage(user));

        assertNotNull(userEntityModel);
        assertTrue(userEntityModel instanceof EntityModel);
        assertNotNull(userEntityModel.getLinks());
        assertThat(user).usingRecursiveComparison().isEqualTo(userEntityModel.getContent());

        ResponseEntity<?> resp = ResponseEntity.created(
                userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);

        assertNotNull(resp);
        assertTrue(resp instanceof ResponseEntity);
        assertEquals(resp.getStatusCode(), HttpStatus.CREATED);
        assertTrue(resp.getStatusCodeValue() == 201);
    }

}