package domain.Interfaces;

import domain.model.Channel;
import domain.model.Message;
import domain.model.User;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface IChannelService {
    void createChannel(Channel channel);
    void deleteChannel(Channel channel);
    Optional<Channel> findChannelById(UID id);
    List<Channel> findAllChannels();
    void updateChannel(Channel channel);
    public void addMessageToChannelGroupChat(UID channelId, UID groupId, Message<?> msg);
    public void deleteMessageFromChannelGroupChatById(UID channelId, UID groupId, UID msgId);
    public void clearChannelGroupChat(UID channelId, UID groupId);
    public Optional<Message<?>> getLatestMessage(UID channelId, UID groupId);
    public List<Message<?>> getLastMessagesOfChannelGroupChat(UID channelId, UID groupId, int count);
    public void addParticipantToChannel(UID channelId, User user);
    public void removeFromChannelParticipant(UID channelId, User user);


}
