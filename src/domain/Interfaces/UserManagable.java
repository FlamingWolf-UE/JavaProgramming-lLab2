package domain.Interfaces;

import domain.model.*;

import java.rmi.server.UID;
import java.util.List;
import java.util.Optional;

public interface UserManagable {

     /**
      * Removes a participant from the conversation.
      *
      * @param user the user to be removed
      */
     void removeParticipant(User user);

     /**
      * Adds a participant to the conversation.
      *
      * @param user the user to be added
      */
     void addParticipant(User user);

     /**
      * Returns an optional containing the user with the specified ID, if such a user exists in the conversation.
      *
      * @param uid the ID of the user to find
      * @return an optional containing the user, or an empty optional if no such user exists
      */
     Optional<User> getParticipantById(UID uid);

     /**
      * Returns a list of all participants in the conversation.
      *
      * @return a list of participants
      */
     List<User> getParticipants();

}