package domain.model.Commands;

import domain.Interfaces.ICommand;
import domain.model.Message;

import java.rmi.server.UID;

public class SendMessageToGroupChatCommand implements ICommand {

    private final UID groupChatId;
    private final Message<?> message;



    public SendMessageToGroupChatCommand(UID groupChatId, Message<?> message) {
        this.groupChatId = groupChatId;
        this.message = message;
    }

    @Override
    public void execute() {

    }
}
