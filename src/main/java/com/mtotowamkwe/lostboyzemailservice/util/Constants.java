package com.mtotowamkwe.lostboyzemailservice.util;

public class Constants {

    public static String TEMPLATES = "classpath:/templates/";
    public static String SUFFIX = ".html";

    // Thymeleaf templates
    public static String EMAIL_TEMPLATE_ENGLISH = "sign-up-code-email-english";

    // Exception messages
    public static String EMAIL_NOT_SENT_ERROR = "An email could not be sent to ";

    // Test data
    public static String TEST_USER_EMAIL = "user01@james.local";
    public static String TEST_USER_NAME = "James";
    public static String TEST_USER_CODE = "696969";
    public static String TEST_EMAIL_TEMPLATE_NAME = "test-english-email-template";

    // Actual data
    public static String EMAIL_FROM = "no-reply@lostboyz.com";
    public static String USER_NAME_KEY = "name";
    public static String USER_CODE_KEY = "code";
    public static final String EMAIL_SUBJECT = "Sign up verification code.";

    // Endpoints
    public static final String API_EMAIL_USER_ENDPOINT = "/api/v1/email";
    public static final String API_TEST_EMAIL_USER_ENDPOINT = "/api/v1/email/test";

}
