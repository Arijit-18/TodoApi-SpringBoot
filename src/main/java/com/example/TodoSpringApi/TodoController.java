package com.example.TodoSpringApi;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


//by adding Restcontroller annotation we are indicating spring framework that this particular class has to be
//treated as a controller

//controller has 2 use cases, collecting the request and returning the response. they do not contain any business logic
//whatever we return from this controller, that returned value is directly returned in the response body of
//http response object

@RestController
public class TodoController {

    private static List<Todo> todos;

    public TodoController() {
        todos = new ArrayList<>();
        todos.add(new Todo(1, false, "Todo 1", 1));
        todos.add(new Todo(2, true, "Todo 2", 2));
    }

}
