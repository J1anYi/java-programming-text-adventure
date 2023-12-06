package service.event;

import model.Task;
import model.character.Protagonist;
import model.character.Character;
import model.goods.Goods;

public class TaskEvent extends Event{

    private Character character;
    private Task task;

    public TaskEvent() {}

    public TaskEvent(Character character, Task task) {
        this.character = character;
        this.task = task;
    }

    @Override
    public void trigger() {
        System.out.println("----------Task event executed----------");

        // now only protagonist can cast magic
        Protagonist protagonist = null;
        if (this.character instanceof Protagonist) {
            protagonist = (Protagonist) character;
        }else {
            System.out.println("Caster is not protagonist");
            return;
        }

        // check if the character has the goods to finish the task
        if (protagonist.getPossessions().contains(task.getNeed())) {
            Goods currentGoods = protagonist.getPossessions().get(protagonist.getPossessions().indexOf(task.getNeed()));
            if (currentGoods.getAmount() >= task.getNeed().getAmount()) {
                currentGoods.setAmount(currentGoods.getAmount() - task.getNeed().getAmount());
            }else {
                System.out.println("Not enough goods.The task needs " + task.getNeed().getAmount() +
                        "  " + task.getNeed().getDescription() +
                        " but you only have " + currentGoods.getAmount());
                return;
            }
        }

        // finish the task
        protagonist.getPossessions().add(task.getReward());
        System.out.println("You finished the task and get " + task.getReward().getAmount() + " " + task.getReward().getDescription());
        System.out.println("----------Task finished----------");
    }
}
