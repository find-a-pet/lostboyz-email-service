package com.mtotowamkwe.lostboyzemailservice.controller;

import com.mtotowamkwe.lostboyzemailservice.model.User;
import com.mtotowamkwe.lostboyzemailservice.model.restful.UserModelAssembler;
import com.mtotowamkwe.lostboyzemailservice.service.EmailService;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class EmailController {

    private final EmailService emailService;

    private final UserModelAssembler assembler;

    @Autowired
    public EmailController(EmailService emailService, UserModelAssembler assembler) {
        this.emailService = emailService;
        this.assembler = assembler;
    }

    @RequestMapping(value = Constants.API_EMAIL_USER_ENDPOINT, method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@Valid @NonNull @RequestBody User user) {
        EntityModel<User> entityModel = assembler.toModel(emailService.sendSimpleMessage(user));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @RequestMapping(value = Constants.API_TEST_EMAIL_USER_ENDPOINT, method = RequestMethod.POST)
    public ResponseEntity<?> validateEmailSent(@Valid @NonNull @RequestBody User user) {
        return sendEmail(user);
    }

}
