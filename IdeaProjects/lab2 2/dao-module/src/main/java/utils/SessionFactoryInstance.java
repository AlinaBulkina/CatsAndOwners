package utils;


import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryInstance {
    @Getter
    private static final SessionFactory instance = new Configuration().configure().buildSessionFactory();
    public static void close() {
        instance.close();
    }
}
