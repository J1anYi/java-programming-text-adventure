package model;

import model.goods.Goods;

/**
 * - description: Strina
 * need: Goods
 * reward. Coods
 */
public class Task {

    private String description;
    private Goods need;
    private Goods reward;

    public Task() {}

    public Task(String description, Goods need, Goods reward) {
        this.description = description;
        this.need = need;
        this.reward = reward;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Goods getNeed() {
        return need;
    }

    public void setNeed(Goods need) {
        this.need = need;
    }

    public Goods getReward() {
        return reward;
    }

    public void setReward(Goods reward) {
        this.reward = reward;
    }
}
