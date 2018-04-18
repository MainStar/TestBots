package testBots.Actions;

import org.hibernate.Session;
import testBots.dao.entities.DepartmentEntity;
import testBots.dao.entities.LectorsEntity;
import testBots.dao.service.LectorsService;
import testBots.dao.utils.HibernateSessionFactory;

import java.util.List;
import java.util.Set;

public class ShowStatistic implements DisplayMode {

    private LectorsService lectorsService = new LectorsService();
    private Session session = HibernateSessionFactory.getSessionFactory().openSession();
    private List<LectorsEntity> lectors;

    private final String SHOW_THE_ADVANTAGE = "Show the advantage salary for department";
    private final String SHOW_COUNT = "Show count of employee for";

    @Override
    public void displayInfo(String userQuestion) {

        lectorsService.setSession(session);

        if (userQuestion.indexOf(SHOW_THE_ADVANTAGE) != -1){
            showSalary(userQuestion);
        }else if (userQuestion.indexOf(SHOW_COUNT) != -1){
            showCount(userQuestion);
        }else {

        }
    }

    private void show(String userQuestion){

    }

    private void showSalary(String userQuestion){
        int min = 0;
        int max = 0;
        String[] departmentMass = userQuestion.split(SHOW_THE_ADVANTAGE);
        String departmentName = departmentMass[1];
        lectors = lectorsService.getAllLectors();
        for (int i = 0; i < lectors.size(); i++){
            Set<DepartmentEntity> departments = lectors.get(i).getDepartments();
            for (DepartmentEntity el : departments){
                if (el.getDepartment() == departmentName){
                    if (el.getSalary() > min){
                        max = el.getSalary();
                    }else {
                        min = el.getSalary();
                    }
                }else {
                    break;
                }
            }
        }
        System.out.println(min + " " + max);
    }

    private void showCount(String userQuestion){
        lectors = lectorsService.getAllLectors();
        System.out.println("Count of employee: " + lectors.size());
    }

    @Override
    public void closeSession(){
        session.close();
        HibernateSessionFactory.closeSession();
    }
}
