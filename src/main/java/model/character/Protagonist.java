package model.character;

import model.Magic;
import model.goods.Goods;

import java.util.Arrays;

/**
 * - magic: Magic
 * - money: Integer
 * - skills: List<Magic>
 * - possessions: List<Goods:
 * - experience value: Integer
 */
public class Protagonist extends Character{

    private Magic magic;
    private int money;
    private Magic[] skills;
    private Goods[] possessions;
    private int experienceValue;

    public Protagonist() {}

    public Protagonist(Magic magic, int money, Magic[] skills, Goods[] possessions, int experienceValue) {
        this.magic = magic;
        this.money = money;
        this.skills = skills;
        this.possessions = possessions;
        this.experienceValue = experienceValue;
    }

    public Magic getMagic() {
        return magic;
    }

    public void setMagic(Magic magic) {
        this.magic = magic;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Magic[] getSkills() {
        return skills;
    }

    public void setSkills(Magic[] skills) {
        this.skills = skills;
    }

    public Goods[] getPossessions() {
        return possessions;
    }

    public void setPossessions(Goods[] possessions) {
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
        String result = super.toString() + "Protagonist{" +
                "magic=" + magic +
                ", money=" + money +
                ", skills=" + Arrays.toString(skills) +
                ", possessions=" + Arrays.toString(possessions) +
                ", experienceValue=" + experienceValue +
                '}';
        return result;
    }
}
