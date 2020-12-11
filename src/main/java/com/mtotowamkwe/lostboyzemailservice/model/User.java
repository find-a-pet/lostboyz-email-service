package com.mtotowamkwe.lostboyzemailservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Random;

@Data
public class User implements Serializable {

    private static final long serialVersionUID = new Random().nextLong();

    private String email;
    private String code;

}
