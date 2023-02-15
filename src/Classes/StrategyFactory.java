package Classes;

import Interfaces.IMessageDrawerStrategy;

public class StrategyFactory {
    public IMessageDrawerStrategy createStrategy(Object obj)
    {

        if (obj instanceof StaticSticker<?>)
        {
            return new StaticStickerDrawerStrategy();
        }
        else if (obj instanceof String)
        {
            return new TextDrawerStrategy();
        }
        else if (obj instanceof AnimatedSticker<?>)
        {
            return new AnimatedStickerDrawerStrategy();
        }
        return new DefaultDrawerStrategy();
    }
}
