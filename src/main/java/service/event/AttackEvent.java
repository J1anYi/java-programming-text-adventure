package service.event;

import controller.Game;
import model.Magic;
import model.character.Character;
import model.character.Protagonist;
import model.goods.Goods;

/**
 * this attack event is triggered when a character attacks another character
 * this is a loop, it can be stopped until someone dies or someone runs away
 *
 * - attacker: Character
 * - attackee: Character
 */
public class AttackEvent extends Event{

    private Character attacker;
    private Character attackee;

    public AttackEvent(Character attacker, Character attackee) {
        this.attacker = attacker;
        this.attackee = attackee;
    }

    @Override
    public void trigger() {
        System.out.println("----------Attack event executed----------");

        // attacker must be protagonist in this version
        // check if the attacker is protagonist
        Protagonist protagonist = null;
        if (this.attacker instanceof Protagonist) {
            protagonist = (Protagonist) attacker;
        }else {
            System.out.println("attacker is not protagonist");
            return;
        }

        // let player choose the selection
        int exit = 0;
        do{
            // attacker action
            exit = attackerAction();
            if (exit == -1) {
                break;
            }

            attackeeAction();
        }while (exit == 0 || attacker.getHealth() <= 0 || attackee.getHealth() <= 0);
        
        System.out.println("----------Attack finished----------");
    }

    private int attackerAction() {
        int exit = 0;
        listChoice();
        int choice = receiveChoice();
        exit = excuateChoice(choice);
        
        return exit;
    }
    
    private void attackeeAction() {
        attack(this.attackee, this.attacker);
    }
    /**
     * let player choose the selection
     * 1. attack
     * 2. use magic
     * 4. run away
     */
    private void listChoice() {
        System.out.println("Please choose the selection:");
        System.out.println("1. attack");
        System.out.println("2. use magic");
        System.out.println("3. run away");
    }

    private int receiveChoice() {
        int choice = Game.scanner.nextInt();

        return choice;
    }

    private int excuateChoice(int choice) {
        switch (choice) {
            case 1:
                attack(this.attacker, this.attackee);
                break;
            case 2:
                useMagic();
                break;
            case 3:
                System.out.println("You run away");
                return -1;
            default:
                System.out.println("Invalid choice");
                break;
        }
        
        return 0;
    }

    private void attack(Character attacker, Character attackee) {
        System.out.println("----------Attack----------");
        // check if the attacker has enough attack power
        if (attacker.getWeapon().getAttackPower() < attackee.getArmor().getDefensePower()) {
            System.out.println("Not enough attack power, cause no damage");
            return;
        }

        // attack
        attackee.setHealth(attackee.getHealth() - (attacker.getWeapon().getAttackPower() - attackee.getArmor().getDefensePower()));
        System.out.println("Attack success, " + attackee.getDescription() + " health: " + attackee.getHealth());
        System.out.println("----------Attack finished----------");
    }
    
    private void useMagic() {
        System.out.println("----------Use magic----------");

        // attacker must be protagonist in this version
        // check if the attacker is protagonist
        Protagonist protagonist = null;
        if (this.attacker instanceof Protagonist) {
            protagonist = (Protagonist) attacker;
        }else {
            System.out.println("attacker is not protagonist");
            return;
        }
        
        // list all the magic the protagonist has
        System.out.println("Please choose the magic you want to use:");
        for (int i = 0; i < protagonist.getSkills().size(); i++) {
            System.out.println(i + ". " + protagonist.getSkills().get(i).getName());
        }
        
        // receive the choice
        int choice = receiveChoice();

        Magic magic = protagonist.getSkills().get(choice);
        
        Event magicEvent = new MagicEvent(protagonist, this.attackee, magic);
        magicEvent.trigger();
    }
    
    
}
