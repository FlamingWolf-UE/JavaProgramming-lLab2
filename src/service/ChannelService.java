package service;

import domain.Interfaces.IChannelRepository;
import domain.Interfaces.IChannelService;
import domain.model.Channel;
import domain.model.Message;
import domain.model.User;

import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChannelService implements IChannelService {
    private final IChannelRepository channelRepository;

    public ChannelService(IChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @Override
    public void createChannel(Channel channel) {
        channelRepository.add(channel);
    }

    @Override
    public void deleteChannel(Channel channel) {
        channelRepository.delete(channel);
    }

    @Override
    public Optional<Channel> findChannelById(UID id) {
        return channelRepository.findById(id);
    }

    @Override
    public List<Channel> findAllChannels() {
        return channelRepository.findAll();
    }

    @Override
    public void updateChannel(Channel channel) {
        channelRepository.update(channel);
    }
    @Override
    public void addMessageToChannelGroupChat(UID channelId, UID groupId, Message<?> msg) {
        var channel = channelRepository.findById(channelId);
        if (channel.isPresent()){
            var groupChat = channel.get().getGroupChatById(groupId);
            groupChat.ifPresent(chat -> chat.get_msgManager().addMessage(msg));
        }

    }
    @Override
    public void deleteMessageFromChannelGroupChatById(UID channelId, UID groupId, UID msgId) {
        var channel = channelRepository.findById(channelId);
        if (channel.isPresent()){
            var groupChat = channel.get().getGroupChatById(groupId);
            groupChat.ifPresent(chat -> chat.get_msgManager().removeMessageById(msgId));
        }

    }
    @Override
    public void clearChannelGroupChat(UID channelId, UID groupId)
    {
        var channel = channelRepository.findById(channelId);
        if (channel.isPresent()){
            var groupChat = channel.get().getGroupChatById(groupId);
            groupChat.ifPresent(chat -> chat.get_msgManager().clearChat());
        }
    }
    @Override
    public Optional<Message<?>> getLatestMessage(UID channelId, UID groupId)
    {
        var channel = channelRepository.findById(channelId);
        if (channel.isPresent()){
            var groupChat = channel.get().getGroupChatById(groupId);
                if (groupChat.isPresent())
                {
                     return groupChat.get().get_msgManager().getNewestMessage();
                }
        }
        return Optional.empty();
    }
    @Override
    public List<Message<?>> getLastMessagesOfChannelGroupChat(UID channelId, UID groupId, int count)
    {
        var channel = channelRepository.findById(channelId);
        if (channel.isPresent()){
            var groupChat = channel.get().getGroupChatById(groupId);
            if (groupChat.isPresent())
            {
                return groupChat.get().get_msgManager().getLastMessages(count);
            }
        }
        return new ArrayList<>();
    }

    @Override
    public void addParticipantToChannel(UID channelId, User user) {
        var channel = channelRepository.findById(channelId);
        channel.ifPresent(value -> value.addParticipant(user));
    }

    @Override
    public void removeFromChannelParticipant(UID channelId, User user) {
        var channel = channelRepository.findById(channelId);
        channel.ifPresent(value -> value.removeParticipant(user));
    }


}
