import java.lang.annotation.*;
import java.lang.reflect.Method;

// Defining repeatable annotation
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(BugReports.class)
@interface BugReport {
    String description();
}

// Defining container annotation
@Retention(RetentionPolicy.RUNTIME)
@interface BugReports {
    BugReport[] value();
}

class Bugs {
    @BugReport(description = "Found a functional bug")
    @BugReport(description = "Found a performance bug")
    public void display() {
        System.out.println("Finding bugs...");
    }
}

public class RepeatableAnnotation {
    public static void main(String[] args) throws Exception {
        Bugs bugs = new Bugs();
        Class<?> bugsClass = bugs.getClass();
        Method displayMethod = bugsClass.getMethod("display");

        bugs.display();

        // Accessing repeatable annotations
        BugReport[] reports = displayMethod.getAnnotationsByType(BugReport.class);
        if (reports.length > 0) {
            System.out.println("Bug Reports:");
            for (BugReport bugReport : reports) {
                System.out.println("Description: " + bugReport.description());
            }
        }
    }
}

/*
Output:
    Finding bugs...
    Bug Reports:
    Description: Found a functional bug
    Description: Found a performance bug
 */