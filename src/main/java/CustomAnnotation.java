import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

enum Priority {Low, Medium, High}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
// Creating custom annotation with priority and assignedTo fields
@interface TaskInfo {
    Priority priority() default Priority.Medium;
    String assignedTo();
}

class TaskManager {
    // Applying custom annotation to the method
    @TaskInfo(priority = Priority.Low, assignedTo = "Sehaj")
    public void task1() {
        System.out.println("This task is assigned to Sehaj!");
    }

    // Applying custom annotation to the method
    @TaskInfo(assignedTo = "Sehaj")
    public void task2() {
        System.out.println("This task is also assigned to Sehaj!");
    }
}

public class CustomAnnotation {
    public static void main(String[] args) throws Exception {
        TaskManager taskManager = new TaskManager();
        Method method1 = taskManager.getClass().getMethod("task1");
        Method method2 = taskManager.getClass().getMethod("task2");

        if (method1.isAnnotationPresent(TaskInfo.class)) {
            TaskInfo annotation = method1.getAnnotation(TaskInfo.class);
            System.out.println("Assigned To: " + annotation.assignedTo());
            System.out.println("Priority: " + annotation.priority());
            taskManager.task1();
        }
        if (method2.isAnnotationPresent(TaskInfo.class)) {
            TaskInfo annotation = method2.getAnnotation(TaskInfo.class);
            System.out.println("\nAssigned To: " + annotation.assignedTo());
            System.out.println("Priority: " + annotation.priority());
            taskManager.task2();
        }
    }
}