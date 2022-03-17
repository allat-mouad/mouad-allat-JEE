package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class PresentationV2 {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(new File("config.txt"));
        String daoClassName=scanner.nextLine();
        Class cDao=Class.forName(daoClassName);
        IDao dao= (IDao) cDao.newInstance();

        String MetierClassName=scanner.nextLine();
        Class cMetier=Class.forName(MetierClassName);
        IMetier metier= (IMetier) cMetier.newInstance();

        Method m=cMetier.getMethod("setDao",IDao.class);
        m.invoke(metier,dao);

        System.out.println(metier.calculer());

    }
}
