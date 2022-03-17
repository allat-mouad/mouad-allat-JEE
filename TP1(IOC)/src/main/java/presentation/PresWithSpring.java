package presentation;

import metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PresWithSpring {
    public static void main(String[] args) {
        ApplicationContext ctx=
                new ClassPathXmlApplicationContext("config.xml");
        IMetier metier= (IMetier) ctx.getBean("metier");
        System.out.println(metier.calculer());

    }
}
