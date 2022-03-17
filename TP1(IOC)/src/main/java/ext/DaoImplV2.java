package ext;

import dao.IDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("dao2")
public class DaoImplV2 implements IDao {

    @Override
    public double getValue() {
        System.out.println("version 2:");
        return 1000;
    }
}
