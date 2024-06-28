package com.csys.template;

import com.csys.template.config.ApplicationProperties;
import com.csys.template.domain.Utilisateur;
import com.csys.template.service.UtilisateurService;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.modelmapper.ModelMapper;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
//@EnableCircuitBreaker
@RefreshScope
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true) 
@EnableJpaRepositories({"com.csys.template"})
public class TemplateApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(TemplateApplication.class);


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        SpringApplication app = new SpringApplication(TemplateApplication.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t"
                + "Application's name '{}' is running! Access URLs:\n\t"
                + "Local: \t\t{}://localhost:{}\n\t"
                + "External: \t{}://{}:{}\n\t"
                + "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }

    @Override
    public void run(String... args) throws Exception {
        // Add your code that needs to be executed on application startup here
    }
}
