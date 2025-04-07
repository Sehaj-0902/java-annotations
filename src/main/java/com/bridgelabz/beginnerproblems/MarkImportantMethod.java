package com.bridgelabz.beginnerproblems;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

class ImportantClass {
    @ImportantMethod(level = "LOW")
    public void displayMessage() {
        System.out.println("Hello, World!");
    }

    @ImportantMethod()
    public int add(int a, int b) {
        return a + b;
    }
}

public class MarkImportantMethod {
    public static void main(String[] args) throws Exception {
        ImportantClass importantClass = new ImportantClass();
        Class<?> cls = importantClass.getClass();

        System.out.println("Important Methods:");

        Method method1 = cls.getDeclaredMethod("displayMessage");
        if (method1.isAnnotationPresent(ImportantMethod.class)) {
            ImportantMethod annotation = method1.getAnnotation(ImportantMethod.class);
            System.out.println("Method: " + method1.getName() + ", Importance Level: " + annotation.level());
            importantClass.displayMessage();
        }

        Method method2 = cls.getDeclaredMethod("add", int.class, int.class);
        if (method2.isAnnotationPresent(ImportantMethod.class)) {
            ImportantMethod annotation = method2.getAnnotation(ImportantMethod.class);
            System.out.println("Method: " + method2.getName() + ", Importance Level: " + annotation.level());
            int result = importantClass.add(4, 9);
            System.out.println("Addition result: " + result);
        }
    }
}

/*
Output:
    Important Methods:
    Method: displayMessage, Importance Level: LOW
    Hello, World!
    Method: add, Importance Level: HIGH
    Addition result: 13
 */