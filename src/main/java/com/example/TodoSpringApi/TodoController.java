package com.example.TodoSpringApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

//    @Autowired
//    @Qualifier("anotherTodoService")
    private TodoService todoService;
    private TodoService todoService2;

    private static final String TODO_NOT_FOUND = "Todo not found";
    private static List<Todo> todoList;

    public TodoController(
            @Qualifier("anotherTodoService") TodoService todoService,
            @Qualifier("fakeTodoService") TodoService todoService2
    ) {
        this.todoService = todoService;
        this.todoService2 = todoService2;
        todoList = new ArrayList<>();
        todoList.add(new Todo(1, false, "Todo 1", 1));
        todoList.add(new Todo(2, true, "Todo 2", 2));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(@RequestParam(required = false) Boolean isCompleted) {
        System.out.println("Incoming query params" + isCompleted + " " + this.todoService2.doSomething());
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
    public ResponseEntity<?> getTodoById(@PathVariable Long todoId) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                return ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }


    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long todoId) {
        Todo todoToRemove = null;
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                todoToRemove = todo;
                break;
            }
        }

        if(todoToRemove != null) {
            todoList.remove(todoToRemove);
            String deleteSuccessMessage = "Todo deleted successfully";
            return ResponseEntity.status(HttpStatus.OK).body(deleteSuccessMessage);
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
        }
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<?> updateTodoById(@PathVariable Long todoId, @RequestParam(required = false) String title, @RequestParam(required = false) Boolean isCompleted, Integer userId) {
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                if(title != null) {
                    todo.setTitle(title);
                }
                if(isCompleted != null) {
                    todo.setCompleted(isCompleted);
                }
                if(userId != null) {
                    todo.setUserId(userId);
                }

                return ResponseEntity.ok(todo);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO_NOT_FOUND);
    }

    /*
    the classes that we create in spring project is handled using inversion of control by spring. User does not create any
    object of the class, spring handles that. The object that is created by spring is refered as beans.
    Spring framework has 2 IoC container -> BeanFactory and ApplcationContext.

    @Qualifier helps us to invoke the desired bean that we want to when there is a collision of beans of the same type
    @Autowired helps us to bypass constructor dependency injection, it automatically brings up the bean
     */

}
