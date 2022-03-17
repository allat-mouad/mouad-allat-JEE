package dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("dao") //au demarage spring  va cree un objet DAOIMPL nomme dao
public class DaoImpl implements IDao {
    @Override
    public double getValue() {
        System.out.println("version 1:");
        return 2000;
    }
}
