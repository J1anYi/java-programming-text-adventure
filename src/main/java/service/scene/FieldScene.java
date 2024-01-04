package service.scene;

import model.goods.Goods;

import java.util.Map;

public class FieldScene extends Scene{

    private Map<String, Goods> fieldItems;

    @Override
    public Scene act() {
        Scene nextScene = super.act();
        return nextScene;
    }
}
