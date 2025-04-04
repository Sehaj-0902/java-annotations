import java.util.ArrayList;
import java.util.List;

public class UncheckedWarnings {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("1");
        list.add("2");
        list.add("3");

        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}