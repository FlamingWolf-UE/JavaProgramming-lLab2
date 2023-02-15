package Classes;

import Interfaces.IMessageDrawerStrategy;

public class MessageDrawer {
    protected IMessageDrawerStrategy _strategy;

    public MessageDrawer(){}

    public MessageDrawer(IMessageDrawerStrategy strategy)
    {
        _strategy = strategy;
    }

    public void setStrategy(IMessageDrawerStrategy strategy)
    {
        _strategy = strategy;
    }

    public void drawMessage(Object obj)
    {
        _strategy.draw(obj);
    }






}
