package model.goods;


/**
 * Goods is a superclass of Weapon and Armor
 * in order to represent the goods in the game
 * decrption: String
 * amount: Integer
 */
public class Goods implements Cloneable{

    private String description;
    private int amount;

    public Goods() {}

    public Goods(String description, int amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }
}
