package com.mtotowamkwe.lostboyzemailservice.exception;

import com.mtotowamkwe.lostboyzemailservice.model.Email;
import com.mtotowamkwe.lostboyzemailservice.util.Constants;

public class EmailNotSentException extends RuntimeException {

    public EmailNotSentException(Email email) {
        super(Constants.EMAIL_NOT_SENT_ERROR + email.getTo() + ".");
    }
}
