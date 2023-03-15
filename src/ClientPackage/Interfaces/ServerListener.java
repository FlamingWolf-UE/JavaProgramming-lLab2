package ClientPackage.Interfaces;

import domain.model.Commands.LoadUserDataCommand;
import domain.model.Commands.SendMessageToGroupChatCommand;

public interface ServerListener {
    void onMessageRecieved(SendMessageToGroupChatCommand command);
    void onAuthentificate(LoadUserDataCommand command);
}
