package authservice.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.GraphQL;

@RestController
@RequestMapping("/graphql")
public class GraphQLController {
    
    @Autowired
    private GraphQL graphQL;

    @PostMapping
    public ResponseEntity<Object> graphql(@RequestBody String query) {
        ExecutionResult executionResult = graphQL.execute(query);
        return ResponseEntity.ok(executionResult);
    }

    @GetMapping
    public String helloWorld() {
        return "¡Hola mundo desde GraphQL!";
    }
}
