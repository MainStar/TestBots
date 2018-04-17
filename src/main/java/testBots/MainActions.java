package testBots;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import testBots.dao.entities.DepartmentEntity;
import testBots.dao.entities.LectorsEntity;
import testBots.dao.utils.HibernateSessionFactory;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MainActions {

    private static Scanner scanner = new Scanner(System.in);
    private static String userQuestion;
    private static String[] questionMass;
    private static Session session;

    private static final String WHO = "Who is a head of department Humanities & Social Sciences";
    private static final String globalSearch = "drov";

    private static final String SHOW_THE_ADVANTAGE = "Show the advantage salary";
    private static final String SHOW_COUNT = "Show count of employee";

    public static void main(String[] args) {

        System.out.println("Enter a command: \n");
        userQuestion = scanner.nextLine();
        questionMass = userQuestion.split(" ");

        if (questionMass[0].equals("Who")){
            String[] nameMass = userQuestion.split("department ");
            String departmentName = nameMass[1];
            List<LectorsEntity> lectors = getAllLectors();
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
        if (questionMass[0].equals("Show")){

            if (userQuestion.indexOf(SHOW_THE_ADVANTAGE) != -1){

            }else if (userQuestion.indexOf(SHOW_COUNT) != -1){
                String[] departmentMass = userQuestion.split(SHOW_COUNT);
                String department = departmentMass[1];
                List<LectorsEntity> 
            }else {

            }

        }
        if (questionMass[0].equals("Global")){
            List<LectorsEntity> lectors = getAllLectors();
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

        session.close();
        HibernateSessionFactory.closeSession();

    }

    public static List<LectorsEntity> getAllLectors(){
        session = HibernateSessionFactory.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(LectorsEntity.class);
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }
}
