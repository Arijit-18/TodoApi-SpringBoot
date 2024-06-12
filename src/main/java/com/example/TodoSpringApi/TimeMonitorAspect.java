package com.example.TodoSpringApi;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


/*
   * Advice represents any action taken by an aspect(cross-cutting concern) at a particular join-point
   * An advice helps us to identify when the logic should be executed (whether before or after or around)
   * join point are places in the program where behaviour can be inserted
   * Point cut is an expression that selects one or more join points
 */




//this helps to implement the logic of the cross-cutting concern
@Aspect
@Component
public class TimeMonitorAspect {

    //@Around enables to run the following method when TimeMonitor annotation is attached somewhere
    @Around("@annotation(TimeMonitor)")
    public void logtime(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();

        //execute the join-point
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            System.out.println("Something went wrong");
        } finally {
            long end = System.currentTimeMillis();
            long totaLExecutionTime = end-start;

            System.out.println("Total time taken is " + totaLExecutionTime + " in ms.");
        }
    }

}
