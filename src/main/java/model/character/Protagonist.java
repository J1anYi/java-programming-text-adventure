package model.character;

import controller.Game;
import model.Magic;
import model.goods.Armor;
import model.goods.Goods;
import model.goods.Weapon;

import java.sql.SQLOutput;
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


    private void equipWeapon(Weapon weapon) {
        possessions.add(this.getWeapon());
        super.setWeapon(weapon);
        possessions.remove(weapon);
    }

    private void equipArmor(Armor armor) {
        possessions.add(this.getArmor());
        super.setArmor(armor);
        possessions.remove(armor);
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

    public void display() {
        System.out.println("---------------------------------------------------");
        System.out.println(this.toString());
        displayPossessions();
        listChoices();
        choice();
    }

    public void displayPossessions() {
        System.out.println("---------------------------------------------------");
        System.out.println("Your possessions:");
        for (int i = 0; i < possessions.size(); i++) {
            System.out.println(i + ". " + possessions.get(i).getDescription());
        }
        System.out.println("---------------------------------------------------");
    }

    private void listChoices() {
        System.out.println("Please choice the event number you want to do: ");
        System.out.println("1. install Equipment ");
        System.out.println("2. exit this panel");
    }

    public void choice() {
        int choice = Game.scanner.nextInt();
        switch (choice) {
            case 1:
                installEquipment();
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid action");
                break;
        }
    }

    private void installEquipment() {
        System.out.println("Please choice the equipment number you want to install: ");
        displayPossessions();

        int choice = Game.scanner.nextInt();
        if (choice < 0 || choice >= possessions.size()) {
            System.out.println("Invalid equipment number");
            return;
        }

        Goods equipment = possessions.get(choice);
        if (equipment instanceof Armor) {
            Armor armor = (Armor) equipment;
            this.equipArmor(armor);
            System.out.println("You have installed " + armor.getDescription());
        } else if (equipment instanceof Weapon) {
            Weapon weapon = (Weapon) equipment;
            this.equipWeapon(weapon);
            System.out.println("You have installed " + weapon.getDescription());
        } else {
            System.out.println("Invalid equipment");
        }
    }
}
