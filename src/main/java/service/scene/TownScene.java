package service.scene;

import model.Task;
import model.goods.Goods;

import java.util.List;

public class TownScene extends Scene{

    private List<Task> taskboard;
    private List<Goods> marketplace;

    public TownScene() {
        super();
    }

    @Override
    public Scene act() {
        return null;
    }

    public void actTask(int choice) {

    }

    public void actTrade(int choice) {

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
