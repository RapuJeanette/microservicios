package authservice.auth_service.config;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import authservice.auth_service.resolver.Mutation;

import java.io.IOException;

@Configuration
public class GraphQLSchemaConfig {

    @Autowired
    private Mutation mutationResolver;

    @Bean
    public GraphQL graphQLs() throws IOException {
        String schemaFile = "graphql/schema.graphql";
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(getClass().getResourceAsStream("/" + schemaFile));
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
        return GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
            .type("Mutation", builder -> builder
            .dataFetcher("realizarCompra", (env) -> mutationResolver.realizarCompra(env.getArgument("usuarioId"), env.getArgument("productos")))

            )
            .build();
    }
}


