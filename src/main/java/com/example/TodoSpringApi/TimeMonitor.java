package com.example.TodoSpringApi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//this represents where this annotation is applicable
@Target(ElementType.METHOD)

//represents when annotation is going to work(eg on runtime)
@Retention(RetentionPolicy.RUNTIME)

public @interface TimeMonitor {
}
