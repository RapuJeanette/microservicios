package authservice.auth_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import graphql.GraphQL;

@Configuration
public class GraphQLConfig {
    
    @Autowired
    private GraphQL graphQL; // Aquí deberías tener tu esquema GraphQL

}
