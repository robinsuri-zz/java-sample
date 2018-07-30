package sample;

import org.apache.catalina.Valve;
import org.apache.catalina.valves.AccessLogValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@SpringBootApplication
@EnableScheduling
public class Application implements EmbeddedServletContainerCustomizer {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (container instanceof TomcatEmbeddedServletContainerFactory)
        {
            logger.debug("It is TomcatEmbeddedServletContainerFactory");
            TomcatEmbeddedServletContainerFactory factory = (TomcatEmbeddedServletContainerFactory) container;

            Collection<Valve> engineValves = factory.getEngineValves();
            for (Valve engineValue: engineValves) {
                if (engineValue instanceof AccessLogValve) {
                    AccessLogValve accessLogValve = (AccessLogValve) engineValue;
                    accessLogValve.setConditionUnless("ignore");
                }
            }
        }
        else
        {
            logger.error("WARNING! this customizer does not support your configured container");
        }
    }
}