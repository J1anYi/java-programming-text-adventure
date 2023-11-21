package model.character;

import model.goods.Armor;
import model.goods.Weapon;

/**
 * health: Integer
 * weapon: Weapon
 * armor Armor
 * description: String
 */
public class Character {

    private int health;
    private Weapon weapon;
    private Armor armor;
    private String description;

    public Character() {}

    public Character(int health, Weapon weapon, Armor armor, String description) {
        this.health = health;
        this.weapon = weapon;
        this.armor = armor;
        this.description = description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Character{" +
                "health=" + health +
                ", weapon=" + weapon +
                ", armor=" + armor +
                ", description='" + description + '\'' +
                '}';
    }
}
