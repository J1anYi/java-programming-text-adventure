package model.goods;

public class Weapon extends Goods implements Cloneable{

    private int attackPower;

    public Weapon() {}

    public Weapon(String description, int amount, int attackPower) {
        super(description, amount);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public String toString() {
        return super.toString() + "Weapon{" +
                "attackPower=" + attackPower +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }
}
