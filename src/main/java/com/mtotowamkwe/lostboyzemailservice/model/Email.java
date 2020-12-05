package com.mtotowamkwe.lostboyzemailservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Random;

@Data
public class Email implements Serializable {

    private static final long serialVersionUID = new Random().nextLong();

    private String from;
    private String to;
    private String subject;
    private String content;
    private Map model;

}
