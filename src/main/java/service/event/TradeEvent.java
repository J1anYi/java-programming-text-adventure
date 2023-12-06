package service.event;

import model.character.Character;
import model.character.Protagonist;
import model.goods.Goods;

/**
 * TradeEvent is a subclass of Event
 * in order to trade some goods between one characters and one marketPlace by using money
 */
public class TradeEvent extends Event{

    private Character buyer;

    private Goods goods;
    private int money;

    @Override
    public void trigger() {
        System.out.println("-------------Trade event executed-------------");
        // now only protagonist can cast magic
        Protagonist protagonist = null;
        if (this.buyer instanceof Protagonist) {
            protagonist = (Protagonist) buyer;
        }else {
            System.out.println("Buyer is not protagonist");
            return;
        }

        if (protagonist.getMoney() < this.money) {
            System.out.println("Not enough money");
            return;
        }

        // trade
        protagonist.setMoney(protagonist.getMoney() - this.money);
        if (protagonist.getPossessions().contains(this.goods)) {
            Goods currentGoods = protagonist.getPossessions().get(protagonist.getPossessions().indexOf(this.goods));
            currentGoods.setAmount(currentGoods.getAmount() + this.goods.getAmount());
        }else {
            protagonist.getPossessions().add(this.goods);
        }
        System.out.println("------------Trade finished-------------");
    }
}
