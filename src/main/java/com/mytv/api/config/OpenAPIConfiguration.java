package com.mytv.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfiguration {

   @Bean
   public OpenAPI defineOpenApi() {
       //Server server = new Server();
       //server.setUrl("http://localhost:8080");
       //server.setDescription("Development");

       Contact myContact = new Contact();
       myContact.setName("XKSAPP");
       myContact.setEmail("contact@xksapp.com");

       Info information = new Info()
               .title("API MYTELEVISION SPRING-BOOT V 3.1.8")
               .version("1.0")
               .description("Cette API axpose l'ensemble des endpoints necessaire pour la consomation et fonctionnement en backend de Mytelevision ")
               .contact(myContact);
       return new OpenAPI().info(information);//.servers(List.of(server));
   }
}