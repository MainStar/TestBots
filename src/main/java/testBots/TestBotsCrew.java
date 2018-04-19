package testBots;

import testBots.Actions.DisplayMode;
import testBots.Actions.FindeHeadDepartment;
import testBots.Actions.GlobalSearch;
import testBots.Actions.ShowStatistic;

import java.util.Scanner;

public class TestBotsCrew {

    private Scanner scanner = new Scanner(System.in);
    private String userQuestion;
    private String[] questionMass;

    private static DisplayMode show;

    public void test(){

        System.out.println("Enter a command: \n");
        userQuestion = scanner.nextLine();
        questionMass = userQuestion.split(" ");

        show = actions(questionMass[0]);
        if (show != null) {
            show.displayInfo(userQuestion);
            show.closeSession();
        }else {
            System.out.println("You have entered a wrong command!");
        }
    }

    private DisplayMode actions(String action){
        switch (action){
            case "Who" : return new FindeHeadDepartment();
            case "Show" : return new ShowStatistic();
            case "Global" : return new GlobalSearch();
        }
        return null;
    }
}
