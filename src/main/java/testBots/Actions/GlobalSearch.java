package testBots.Actions;

import org.hibernate.Session;
import testBots.dao.entities.LectorsEntity;
import testBots.dao.service.LectorsService;
import testBots.dao.utils.HibernateSessionFactory;

import java.util.List;

public class GlobalSearch implements DisplayMode{

    private LectorsService lectorsService = new LectorsService();
    private Session session = HibernateSessionFactory.getSessionFactory().openSession();

    @Override
    public void displayInfo(String userQuestion) {

        lectorsService.setSession(session);

        List<LectorsEntity> lectors = lectorsService.getAllLectors();
        String[] queryMass = userQuestion.split("Global search by ");
        String template = queryMass[1];
        String forSearch = "";
        for (int i = 0; i < lectors.size(); i++){
            forSearch += lectors.get(i).getName() + " " + lectors.get(i).getSecondName();
            if (forSearch.indexOf(template) != -1){
                System.out.println(lectors.get(i).getName() + " " + lectors.get(i).getSecondName());
            }
            forSearch = "";
        }
    }

    @Override
    public void closeSession(){
        session.close();
        HibernateSessionFactory.closeSession();
    }
}
