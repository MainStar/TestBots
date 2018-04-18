package testBots.Actions;

import org.hibernate.Session;
import testBots.dao.entities.DepartmentEntity;
import testBots.dao.entities.LectorsEntity;
import testBots.dao.service.LectorsService;
import testBots.dao.utils.HibernateSessionFactory;

import java.util.List;
import java.util.Set;

public class FindeHeadDepartment implements DisplayMode {

    private LectorsService lectorsService = new LectorsService();
    private Session session = HibernateSessionFactory.getSessionFactory().openSession();
    private String[] nameMass;
    private String departmentName;
    private List<LectorsEntity> lectors;

    @Override
    public void displayInfo(String userQuestion) {

        lectorsService.setSession(session);

        nameMass = userQuestion.split("department ");
        departmentName = nameMass[1];
        lectors = lectorsService.getAllLectors();
        for (int i = 0; i < lectors.size(); i++){
            Set<DepartmentEntity> departments = lectors.get(i).getDepartments();
            for (DepartmentEntity el : departments){
                if (el.getDepartment().equals(departmentName)) {
                    if (el.getHead_of_department() != null) {
                        System.out.println("Head of " + departmentName + " department " + el.getName() + " " + el.getSecondName());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void closeSession(){
        session.close();
        HibernateSessionFactory.closeSession();
    }
}
