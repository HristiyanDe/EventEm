package com.softuni.eventem.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

import static com.softuni.eventem.constants.OpenAPIInfo.API_DESCRIPTION;
import static com.softuni.eventem.constants.OpenAPIInfo.API_TITLE;
import static com.softuni.eventem.constants.OpenAPIInfo.API_URL;
//TODO: Integrate swagger into security, proper method descriptions
@Configuration
@SecurityScheme(
  name = "Bearer Authentication",
  type = SecuritySchemeType.HTTP,
  bearerFormat = "JWT",
  scheme = "Bearer"
)
@OpenAPIDefinition(
  info =@Info(
    title = API_TITLE,
    contact = @Contact,
    license = @License(),
    description = API_DESCRIPTION
  ),
  servers = @Server(
    url = API_URL
  )
)
public class OpenAPIConfig {

}
