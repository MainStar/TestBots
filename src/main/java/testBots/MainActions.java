package testBots;

import testBots.Actions.DisplayMode;
import testBots.Actions.FindeHeadDepartment;
import testBots.Actions.GlobalSearch;
import testBots.Actions.ShowStatistic;

import java.util.Scanner;

public class MainActions {

    private static Scanner scanner = new Scanner(System.in);
    private static String userQuestion;
    private static String[] questionMass;

    private static DisplayMode show;

    private static final String WHO = "Who is a head of department Humanities & Social Sciences";
    private static final String globalSearch = "drov";

    public static void main(String[] args) {

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

    private static DisplayMode actions(String action){
        switch (action){
            case "Who" : return new FindeHeadDepartment();
            case "Show" : return new ShowStatistic();
            case "Global" : return new GlobalSearch();
        }
        return null;
    }
}
