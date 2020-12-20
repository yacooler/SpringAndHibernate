import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vyazankin.database.Test;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ru.vyazankin.SpringConfig.class);

        Test test = context.getBean("test", Test.class);
        test.select();

        context.close();
    }


}
