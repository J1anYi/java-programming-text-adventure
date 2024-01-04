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
        if (checkHavingGoods(task.getNeed(), protagonist)) {
            Goods currentGoods = null;
            for (int i = 0; i < protagonist.getPossessions().size(); i++) {
                if (protagonist.getPossessions().get(i).getDescription().contains(task.getNeed().getDescription())) {
                    currentGoods = protagonist.getPossessions().get(i);
                    break;
                }
            }
            protagonist.getPossessions().remove(currentGoods);

            // finish the task
            protagonist.getPossessions().add(task.getReward());
            System.out.println("You finished the task and get " + task.getReward().getAmount() + " " + task.getReward().getDescription());
            System.out.println("----------Task finished----------");
        }else {
            System.out.println("Not enough goods.The task needs " + task.getNeed().getDescription() +
                    " but you don't have it");
        }

    }

    private boolean checkHavingGoods(Goods goods, Protagonist protagonist) {
        for (int i = 0; i < protagonist.getPossessions().size(); i++) {
            if (protagonist.getPossessions().get(i).getDescription().contains(goods.getDescription())) {
                return true;
            }
        }
        return false;
    }
}
