package com.example.TodoSpringApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//by adding Restcontroller annotation we are indicating spring framework that this particular class has to be
//treated as a controller

//controller has 2 use cases, collecting the request and returning the response. they do not contain any business logic
//whatever we return from this controller, that returned value is directly returned in the response body of
//http response object

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {

    private static List<Todo> todoList;

    public TodoController() {
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos() {
        return ResponseEntity.ok(todoList);
    }

    @PostMapping
    /**
     * we can use this annotation to set the status code @ResponseStatus(HttpStatus.CREATED)
     * ResponseEntity is a class that helps to manage the whole response object manually
     */
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo) {
        todoList.add(newTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long todoId) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{todoId}")
    public ResponseEntity<Todo> deleteTodoById(@PathVariable Long todoId) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                todoList.remove(todo);
                return ResponseEntity.ok(todo);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long todoId) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                todo.setId((int) (todoId * 10));
                todo.setTitle("Changed to new data");
                return ResponseEntity.ok(todo);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
