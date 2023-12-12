package model.goods;

public class Armor extends Goods implements Cloneable{

    private int defensePower;

    public Armor() {}

    public Armor(String description, int amount, int defensePower) {
        super(description, amount);
        this.defensePower = defensePower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    @Override
    public String toString() {
        return super.toString() + "Armor{" +
                "defensePower=" + defensePower +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }
}
