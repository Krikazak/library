package fr.peyrusaubes.library.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("fr.peyrusaubes.library.service")
@EnableTransactionManagement
public class ServiceConfig {

}
