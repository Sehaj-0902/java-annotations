package com.bridgelabz.beginnerproblems;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

class PendingTasks {
    @Todo(task = "Implement add item to cart functionality", assignedTo = "Sehaj", priority = "HIGH")
    public void addItemToCart() {
        System.out.println("Add Item to Cart functionality is pending!");
    }

    @Todo(task = "Implement view cart items functionality", assignedTo = "Sehaj")
    public void viewCartItems() {
        System.out.println("View Cart Items functionality is pending!");
    }
}

public class MarkPendingTask {
    public static void main(String[] args) throws Exception {
        PendingTasks tasks = new PendingTasks();
        Class<?> cls = tasks.getClass();

        System.out.println("Pending Tasks:");

        Method method1 = cls.getDeclaredMethod("addItemToCart");
        if (method1.isAnnotationPresent(Todo.class)) {
            Todo annotation = method1.getAnnotation(Todo.class);
            System.out.println("Method: " + method1.getName() + "\nTask: " + annotation.task() + "\nAssigned To: " + annotation.assignedTo() + "\nPriority: " + annotation.priority());
            tasks.addItemToCart();
        }

        Method method2 = cls.getDeclaredMethod("viewCartItems");
        if (method2.isAnnotationPresent(Todo.class)) {
            Todo annotation = method2.getAnnotation(Todo.class);
            System.out.println("Method: " + method2.getName() + "\nTask: " + annotation.task() + "\nAssigned To: " + annotation.assignedTo() + "\nPriority: " + annotation.priority());
            tasks.viewCartItems();
        }
    }
}

/*
Output:
    Pending Tasks:
    Method: addItemToCart
    Task: Implement add item to cart functionality
    Assigned To: Sehaj
    Priority: HIGH
    Add Item to Cart functionality is pending!
    Method: viewCartItems
    Task: Implement view cart items functionality
    Assigned To: Sehaj
    Priority: MEDIUM
    View Cart Items functionality is pending!
 */