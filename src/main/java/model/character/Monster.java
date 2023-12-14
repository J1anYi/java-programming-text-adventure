package model.character;

import model.goods.Armor;
import model.goods.Goods;
import model.goods.Weapon;

import java.util.List;

public class Monster extends Character implements Cloneable{

    private int gold;
    private int exp;
    private List<Goods> dropItems;

    public Monster() {}
    public Monster(int health, Weapon weapon, Armor armor, String description, int gold, int exp, List<Goods> dropItems) {
        super(health, weapon, armor, description);
        this.gold = gold;
        this.exp = exp;
        this.dropItems = dropItems;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public List<Goods> getDropItems() {
        return dropItems;
    }

    public void setDropItems(List<Goods> dropItems) {
        this.dropItems = dropItems;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + "Monster{" +
                "gold=" + gold +
                ", exp=" + exp +
                ", dropItems=" + dropItems +
                '}';
    }
}
