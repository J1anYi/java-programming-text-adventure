package model.character;

import model.Magic;
import model.goods.Armor;
import model.goods.Goods;
import model.goods.Weapon;

import java.util.Arrays;
import java.util.List;

/**
 * - mana: Intteger
 * - money: Integer
 * - skills: List<Magic>
 * - possessions: List<Goods:
 * - experience value: Integer
 */
public class Protagonist extends Character{

    private int mana;
    private int money;
    private List<Magic> skills;
    private List<Goods> possessions;
    private int experienceValue;

    public Protagonist() {}

    public Protagonist(int health, Weapon weapon, Armor armor, String description, int mana, int money, List<Magic> skills, List<Goods> possessions, int experienceValue) {
        super(health, weapon, armor, description);
        this.mana = mana;
        this.money = money;
        this.skills = skills;
        this.possessions = possessions;
        this.experienceValue = experienceValue;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Magic> getSkills() {
        return skills;
    }

    public void setSkills(List<Magic> skills) {
        this.skills = skills;
    }

    public List<Goods> getPossessions() {
        return possessions;
    }

    public void setPossessions(List<Goods> possessions) {
        this.possessions = possessions;
    }

    public int getExperienceValue() {
        return experienceValue;
    }

    public void setExperienceValue(int experienceValue) {
        this.experienceValue = experienceValue;
    }

    @Override
    public String toString() {
        return super.toString() + "Protagonist{" +
                "mana=" + mana +
                ", money=" + money +
                ", skills=" + skills +
                ", possessions=" + possessions +
                ", experienceValue=" + experienceValue +
                '}';
    }
}
