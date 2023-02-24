package domain.Interfaces;

import domain.model.PrivateChat;
import domain.model.User;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface IPrivateChatRepository {

        void add(PrivateChat privateChat);
        void delete(PrivateChat privateChat);
        public Optional<PrivateChat> findById(UID id);
        public List<PrivateChat> findAll();
        public Optional<PrivateChat> findByParticipants(User user1, User user2);
        public void update(PrivateChat privateChat);

}
