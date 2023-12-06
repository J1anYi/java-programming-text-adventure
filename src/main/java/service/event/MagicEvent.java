package service.event;

import model.Magic;
import model.character.Character;
import model.character.Protagonist;

/**
 * - caster: Character
 * - target: Character
 * - magic: Magic
 */
public class MagicEvent extends Event{
    private Character caster;
    private Character target;
    private Magic magic;

    public MagicEvent(Character caster, Character target, Magic magic) {
        this.caster = caster;
        this.target = target;
        this.magic = magic;
    }

    @Override
    public void trigger() {
        System.out.println("Magic event executed");

        // now only protagonist can cast magic
        Protagonist protagonist = null;
        if (this.caster instanceof Protagonist) {
            protagonist = (Protagonist) caster;
        }else {
            System.out.println("Caster is not protagonist");
            return;
        }

        // check if protagonist has the magic
        boolean hasMagic = false;
        for (Magic magic : protagonist.getSkills()) {
            if (magic.getName().equals(this.magic.getName())) {
                hasMagic = true;
                break;
            }
        }
        if (!hasMagic) {
            System.out.println("Protagonist does not have the magic");
            return;
        }

        // check if protagonist has enough magic power
        if (protagonist.getMana() < this.magic.getCost()) {
            System.out.println("Not enough magic power");
            return;
        }

        // cast magic
        protagonist.setMana(protagonist.getMana() - this.magic.getCost());
        target.setHealth(target.getHealth() - this.magic.getValue());
        System.out.println("Magic casted");
    }
}
