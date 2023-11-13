package model;

public class Magic {

    private String name;
    private int value;
    private String description;
    private int cost;

    public Magic() {}

    public Magic(String name, int value, String description, int cost) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
            return "Magic{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    ", description='" + description + '\'' +
                    ", cost=" + cost +
                    '}';
    }
}
