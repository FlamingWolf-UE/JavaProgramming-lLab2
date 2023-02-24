import domain.Interfaces.IGroupChatRepository;
import domain.Interfaces.IGroupChatService;
import domain.Interfaces.Sticker;
import domain.model.*;
import domain.repository.GroupChatRepository;
import service.GroupChatService;

import java.awt.image.BufferedImage;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        GroupChat chat = new GroupChat( "My New Chat");
        Sticker<ArrayList<BufferedImage>> animatedsticker = new AnimatedSticker<>(new UID(), new ArrayList<>(List.of(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB))));
        Sticker<BufferedImage> staticSticker = new StaticSticker<>(new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB));
        chat.get_msgManager().addMessage(new Message<>(

                new UID(),
                new Date(),
                animatedsticker
        ));
        chat.get_msgManager().addMessage(new Message<>(

                new UID(),
                new Date(),
                staticSticker
        ));
        chat.get_msgManager().getMessages();
        chat.addParticipant(new User( "John Dough"));
        chat.addParticipant(new User( "Dana Merser"));
        Channel ch = new Channel(new UID(), "MyChat");


        

        var user1 = new User( "Helen Par");
        var user2 = new User( "Robert Par");

        PrivateChat pr_chat = new PrivateChat(user1, user2);
        var result = pr_chat.getOtherParticipant(user1);

      //  var f =ch.getGroupChatById(chat.getId()).get().get_msgManager().getNewestMessage().get().get_content();

        IGroupChatRepository rep = new GroupChatRepository();
        rep.add(new GroupChat("Hello world"));
        IGroupChatService service = new GroupChatService(rep);
        var chatc = rep.findAll().get(0);
        service.addMessageToGroupChat(chatc.getId(), new Message<>(
                new UID(),
                new Date(),
                animatedsticker
        ));
        service.addParticipantToGroupChat(chatc.getId(), new User("Ddd hello"));

        var l = rep.findById(chatc.getId()).get().getParticipants();
        System.out.println();





    }
}