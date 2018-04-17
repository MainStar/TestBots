package testBots.Actions;

public abstract class Comands implements DisplayMode {

    public Comands get(String action){
        switch (action){
            case "Who" : return new FindeHeadDepartment();
            case "Show" : return new ShowStatistic();
            case "Global" : return new GlobalSearch();
        }
        return null;
    }

}
