package presentation;

import dao.DaoImpl;
import metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
        //instanciation static
        DaoImpl dao=new DaoImpl();
        MetierImpl metier=new MetierImpl();
        //injection de depandances
        metier.setDao(dao);
        System.out.println(metier.calculer());
    }
}
