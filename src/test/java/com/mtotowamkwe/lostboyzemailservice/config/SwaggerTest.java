package com.mtotowamkwe.lostboyzemailservice.config;

import com.mtotowamkwe.lostboyzemailservice.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {com.mtotowamkwe.lostboyzemailservice.config.Swagger.class})
@ContextConfiguration(classes = {Config.class})
@TestPropertySource(locations = "classpath:application.yml")
public class SwaggerTest {

    private static final Logger LOG = LoggerFactory.getLogger(SwaggerTest.class);

    @Autowired
    private Environment environment;

    @Value("${description}")
    private String description;

    @Value("${title}")
    private String title;

    @Value("${version}")
    private String version;

    @Autowired
    private Docket docket;

    @Before
    public void setUp() {
        assertNotNull(environment);
    }

    @Test
    public void docket() {
        assertNotNull(docket);
        assertTrue(docket instanceof Docket);

        Docket aDocket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/api/v1/email.*")).build()
                .apiInfo(new ApiInfoBuilder().description(description).title(title).version(version).build());

        assertThat(docket).usingRecursiveComparison().isEqualTo(aDocket);
    }

    @Test
    public void description() {
        assertNotNull(description);
        assertTrue(description instanceof String);
        assertEquals(Constants.TEST_SPRING_APPLICATION_DESCRIPTION, description);
    }

    @Test
    public void title() {
        assertNotNull(title);
        assertTrue(title instanceof String);
        assertEquals(Constants.TEST_SPRING_APPLICATION_TITLE, title);
    }

    @Test
    public void version() {
        assertNotNull(version);
        assertTrue(version instanceof String);
        assertEquals(Constants.TEST_SPRING_APPLICATION_VERSION, version);
    }
}