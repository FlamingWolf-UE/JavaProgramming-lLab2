
import Classes.*;
import Interfaces.Sticker;

import java.awt.image.BufferedImage;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        GroupChat chat = new GroupChat(new UID(), "My New Chat");
        Sticker<ArrayList<BufferedImage>> animatedsticker = new AnimatedSticker<>(new UID(), new ArrayList<>(List.of(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB))));
        Sticker<BufferedImage> staticSticker = new StaticSticker<>(new UID(), new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB));
        chat.get_msgManager().addMessage(new Message<>(
                new UID(),
                new UID(),
                new Date(),
                animatedsticker
        ));
        chat.get_msgManager().addMessage(new Message<>(
                new UID(),
                new UID(),
                new Date(),
                staticSticker
        ));
        chat.get_msgManager().getMessages();
        chat.addParticipant(new User(new UID(), "John Dough"));
        chat.addParticipant(new User(new UID(), "Dana Merser"));
        Channel ch = new Channel(new UID(), "MyChat");
        ch.addGroupChat(chat);

        

        var user1 = new User(new UID(), "Helen Par");
        var user2 = new User(new UID(), "Robert Par");

        PrivateChat pr_chat = new PrivateChat(user1, user2);
        var result = pr_chat.getOtherParticipant(user1);

        var f =ch.getGroupChatById(chat.getId()).get().get_msgManager().getNewestMessage().get().get_content();

        StrategyFactory factory = new StrategyFactory();
        var strategy = factory.createStrategy(f);

        MessageDrawer msgDrawer = new MessageDrawer();
        msgDrawer.setStrategy(strategy);

        msgDrawer.drawMessage(f);
    }
}