package testBots.Actions;

import org.hibernate.Session;
import testBots.dao.entities.DepartmentEntity;
import testBots.dao.entities.LectorsEntity;
import testBots.dao.service.DepartmentService;
import testBots.dao.service.LectorsService;
import testBots.dao.utils.HibernateSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ShowStatistic implements DisplayMode {

    private LectorsService lectorsService = new LectorsService();
    private DepartmentService departmentService = new DepartmentService();
    private Session session = HibernateSessionFactory.getSessionFactory().openSession();
    private List<LectorsEntity> lectors;
    private List<DepartmentEntity> departments;
    private String departmentName = "";
    private String answer = "The average salary of " + departmentName + " is ";

    private final String SHOW_THE_ADVANTAGE = "Show the advantage salary for department ";
    private final String SHOW_COUNT = "Show count of employee for ";

    @Override
    public void displayInfo(String userQuestion) {

        lectorsService.setSession(session);
        departmentService.setSession(session);
        lectors = lectorsService.getAllLectors();

        if (userQuestion.indexOf(SHOW_THE_ADVANTAGE) != -1){
            showSalary(userQuestion);
        }else if (userQuestion.indexOf(SHOW_COUNT) != -1){
            showCount(userQuestion);
        }else {
            show(userQuestion);
        }
    }

    private void show(String userQuestion){
        int assistansCount = 0;
        int associateProfessorCou = 0;
        int professorCount = 0;

        String[] a = userQuestion.split("Show ");
        String b = a[1];
        String[] c = b.split(" statistic");
        departmentName = c[0];

        System.out.println(departmentName);
        List<DepartmentEntity> departments = lectorsService.getDepartments();
        for (DepartmentEntity el : departments){
            if (el.getDepartment().equals(departmentName)){
                if (el.getLector().getDegree().equals("assistant")){
                    assistansCount += 1;
                }
                if (el.getLector().getDegree().equals("professor")){
                    professorCount += 1;
                }
                if (el.getLector().getDegree().equals("associate professor")){
                    associateProfessorCou += 1;
                }
            }
        }
        System.out.println("assistant: " + assistansCount);
        System.out.println("associate professor " + associateProfessorCou);
        System.out.println("professor " + professorCount);
    }

    private void showSalary(String userQuestion){
        List<Integer> maxVal = new ArrayList<>();
        String[] departmentMass = userQuestion.split(SHOW_THE_ADVANTAGE);
        departmentName = departmentMass[1];
        for (int i = 0; i < lectors.size(); i++){
            Set<DepartmentEntity> departments = lectors.get(i).getDepartments();
            for (DepartmentEntity el : departments){
                if (el.getDepartment().equals(departmentName)) {
                    maxVal.add(el.getSalary());
                }
            }
        }
        sortList(maxVal);
        if ((maxVal.size() % 2) == 0){
            System.out.println(answer + maxVal.get(maxVal.size()/2));
        }else {
            System.out.println(answer + maxVal.get((maxVal.size()/2)+1));
        }
    }

    private void showCount(String userQuestion){
        departments = lectorsService.getDepartments();
        int count = 0;
        String[] mass = userQuestion.split(SHOW_COUNT);
        departmentName = mass[1];
        for (DepartmentEntity el : departments){
            if (el.getDepartment().equals(departmentName)){
                count++;
            }
        }
        System.out.println("Count of employee: " + count);
    }

    private List<Integer> sortList(List<Integer> list){
        for (int i = 0; i < list.size(); i++){
            for (int x = list.size() - 1; x > i; x-- ){
                if (list.get(x-1) > list.get(x)){
                    int k = list.get(x - 1);
                    list.set(x - 1, list.get(x));
                    list.set(x, k);
                }
            }
        }
        return list;
    }

    @Override
    public void closeSession(){
        session.close();
        HibernateSessionFactory.closeSession();
    }
}
