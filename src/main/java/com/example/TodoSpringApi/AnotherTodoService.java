package com.example.TodoSpringApi;

import org.springframework.stereotype.Service;

@Service("anotherTodoService")
public class AnotherTodoService implements TodoService{
    @Override
    public String doSomething() {
        return "Something 2";
    }
}
