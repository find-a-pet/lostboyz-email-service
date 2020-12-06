package com.mtotowamkwe.lostboyzemailservice.exception;

import com.mtotowamkwe.lostboyzemailservice.model.User;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;

public class EmailNotSentException extends RuntimeException {

    public EmailNotSentException(User user) {
        super(Constants.EMAIL_NOT_SENT_ERROR + user.getEmail() + ".");
    }
}
