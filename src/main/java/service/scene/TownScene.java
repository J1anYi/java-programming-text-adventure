package service.scene;

import controller.Game;
import model.Task;
import model.character.Protagonist;
import model.goods.Goods;
import service.event.TaskEvent;
import service.event.TradeEvent;

import java.util.List;
import java.util.Map;

public class TownScene extends Scene{

    private List<Task> taskboard;
    private List<Goods> marketplace;

    public TownScene() {
        super();
    }

    @Override
    public Scene act() {
        Scene nextScene = super.act();
        return nextScene;
    }

    @Override
    protected void listChoice() {
        System.out.println("Please choice the event number you want to do: ");
        System.out.println("1. Go to the next scene");
        System.out.println("2. go to task board");
        System.out.println("3. go to marketplace");
    }

    @Override
    protected Scene choice() {
        int choice = Game.scanner.nextInt();
        switch (choice) {
            case 1:
                Scene nextScene = choiceScene();
                return nextScene;
            case 2:
                choiceTask();
                break;
            case 3:
                choiceTrade();
                break;
            default:
                break;
        }

        return null;
    }

    private void choiceTrade() {
        System.out.println("Please choose the goods you want to buy: ");
        for (int i = 0; i < marketplace.size(); i++) {
            System.out.println(i + ". " + marketplace.get(i).getDescription());
        }

        int choice = Game.scanner.nextInt();
        actTrade(choice);
    }

    private void choiceTask() {
        System.out.println("Please choose the task you want to do: ");
        for (int i = 0; i < taskboard.size(); i++) {
            System.out.println(i + ". " + taskboard.get(i).getDescription());
        }

        int choice = Game.scanner.nextInt();
        actTask(choice);
    }

    public void actTask(int choice) {
        Protagonist protagonist = Game.getInstance().getCurrentProtagonist();
        Task task = taskboard.get(choice);

        // trigger the task event
        new TaskEvent(protagonist, task).trigger();
    }

    public void actTrade(int choice) {
        Protagonist protagonist = Game.getInstance().getCurrentProtagonist();
        Goods goods = marketplace.get(choice);

        // buy goods
    }

    public List<Task> getTaskboard() {
        return taskboard;
    }

    public void setTaskboard(List<Task> taskboard) {
        this.taskboard = taskboard;
    }

    public List<Goods> getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(List<Goods> marketplace) {
        this.marketplace = marketplace;
    }

    @Override
    public String toString() {
        return super.toString() + "TownScene{" +
                "taskboard=" + taskboard +
                ", marketplace=" + marketplace +
                '}';
    }
}
