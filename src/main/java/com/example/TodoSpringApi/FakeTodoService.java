package com.example.TodoSpringApi;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/*
 any class that is annotated by @Component is good enough to be managed by Spring boot.
 */
@Service("fakeTodoService")
public class FakeTodoService implements TodoService{

    @TimeMonitor
    public String doSomething() { //join point
        for(int i=1;i<=10000000;i++) {}
        return "Something";
    }
}
