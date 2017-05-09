package ru.stqa.pft.mantiss.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantiss.model.Issues;
import ru.stqa.pft.mantiss.model.IssuesData;
import ru.stqa.pft.mantiss.model.UserData;
import ru.stqa.pft.mantiss.model.Users;

import java.util.List;

public class DbHelper {

   private final SessionFactory sessionFactory;

   public DbHelper() {
      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
   }

   public Users users() {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<UserData> result = session.createQuery( "from UserData" ).list();
      session.getTransaction().commit();
      session.close();

      return new Users(result);
   }

   public Issues issues() { //TODO
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      List<IssuesData> result = session.createQuery( "from IssuesData" ).list();
      session.getTransaction().commit();
      session.close();

      return new Issues(result);
   }
}
