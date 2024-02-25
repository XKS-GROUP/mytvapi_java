package com.mytv.api.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@SecuritySchemes({
    @SecurityScheme(
        name = "bearerAuth", // Identifiant de référence utilisé dans @SecurityRequirement
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
    )
})
public class OpenAPIConfiguration {

   @Bean
   public OpenAPI defineOpenApi() {
       //Server server = new Server();  Ici sera defini le server de l 'api 
       //server.setUrl("http://localhost:8080"); Ici sera defini l'adresse de base depuis laquelle l api sera disponible 
       //server.setDescription("Development"); Ici sera defini le mode de l'API (production ou developpement 

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
   
   
   @Bean
   public GroupedOpenApi allApi() {
       return GroupedOpenApi.builder()
               .group("Toutes les routes API")
               .pathsToMatch("/**")
               .build();
   }
   
   @Bean
   public GroupedOpenApi authApi() {
       return GroupedOpenApi.builder()
               .group("Auth")
               .pathsToMatch("/api/v1/auth/**")
               .build();
   }

   
   @Bean
   public GroupedOpenApi adminApi() {
       return GroupedOpenApi.builder()
               .group("Admin API")
               .pathsToMatch("/api/v1/admin/**")
               .build();
   }
   
   @Bean
   public GroupedOpenApi frontApi() {
       return GroupedOpenApi.builder()
               .group("Front API")
               .pathsToMatch("/api/v1/front/**")
               .build();
   }
}