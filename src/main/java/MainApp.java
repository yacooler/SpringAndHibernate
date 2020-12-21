import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.vyazankin.database.Test;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ru.vyazankin.SpringConfig.class);
        Test test = context.getBean("test", Test.class);
        test.test();

        SessionFactory sessionFactory = context.getBean("sessionFactoryBean", SessionFactory.class);
        sessionFactory.getCurrentSession().close();
        sessionFactory.close();
        context.close();
    }
}
