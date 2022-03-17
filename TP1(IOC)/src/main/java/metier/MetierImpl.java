package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("metier") //au demarage , spring  va cree un objet METIERIMPL

public class MetierImpl implements IMetier {
    @Autowired   //injection de dependances :spring va cherche un objet dao puis il va l'injecter dans
                //la var dao
    @Qualifier("dao3")


    private IDao dao;
    @Override
    public double calculer() {
        return 2*dao.getValue();
    }


    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
