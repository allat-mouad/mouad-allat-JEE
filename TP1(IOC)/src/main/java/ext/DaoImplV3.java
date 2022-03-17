package ext;

import dao.IDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component("dao3")
public class DaoImplV3 implements IDao {

    @Override
    public double getValue() {
        System.out.println("version 3:");
        return 3000;
    }
}
