package domain.model;

import java.io.Serializable;
import java.rmi.server.UID;
import java.util.List;

public class User implements Serializable {

    private final UID _id;
    private String _name;
    private String _password;

    private final List<UID> _chatUIDs;

    public String get_password() {
        return _password;
    }

    public List<UID> get_chatUIDs() {
        return _chatUIDs;
    }


    public void AddChatUID(UID id)
    {
        _chatUIDs.add(id);
    }

    public void deleteChatUID(UID id)
    {
        _chatUIDs.remove(id);
    }


    public void set_password(String _password) {
        this._password = _password;
    }

    /**
     * Constructor for the User class.
     * @param name the user's name
     */
    public User(String name, String password, List<UID> chatIds) {
        _id = new UID();
        _name = name;
        _password = password;
        _chatUIDs = chatIds;
    }

    /**
     * Getter for the user's unique identifier.
     * @return the user's unique identifier
     */
    public UID get_id() {
        return _id;
    }

    /**
     * Getter for the user's name.
     * @return the user's name
     */
    public String get_name() {
        return _name;
    }

    /**
     * Setter for the user's name.
     * @param _name the new name for the user
     */
    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     * Computes a hash code for the User.
     * @return the hash code for the User
     */
    @Override
    public int hashCode() {
        return 31 * _name.hashCode() + 31 * _id.hashCode();
    }

    /**
     * Checks whether this User is equal to another object.
     * @param obj the other object to compare to
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User other)) return false;
        return this._id.equals(other._id) && this._name.equals(other._name);
    }

    /**
     * Returns a string representation of the User.
     * @return a string representation of the User
     */
    @Override
    public String toString() {
        return "User{" + "_id=" + _id.toString() + "_name= "+ _name + '}';
    }
}