package domain.model;

import domain.Interfaces.Sticker;
import java.awt.*;
import java.rmi.server.UID;

public class StaticSticker<T extends Image> implements Sticker<T> {

    // The unique identifier of the sticker.
    protected UID _id;

    // The image content of the sticker.
    private final T _image;

    /**
     * Constructs a new static sticker object with the given id and image content.
     *
     * @param id The unique identifier of the sticker.
     * @param image The image content of the sticker.
     */
    public StaticSticker(UID id, T image) {
        _id = id;
        _image = image;
    }

    /**
     * Returns the unique identifier of the sticker.
     *
     * @return The unique identifier of the sticker.
     */
    @Override
    public UID get_id() {
        return _id;
    }

    /**
     * Returns the image content of the sticker.
     *
     * @return The image content of the sticker.
     */
    @Override
    public T getImageContent() {
        return _image;
    }

    /**
     * Returns the hash code of the sticker.
     *
     * @return The hash code of the sticker.
     */
    @Override
    public int hashCode() {
        int result = _id.hashCode();
        result = 31 * result + _image.hashCode();
        return result;
    }

    /**
     * Compares the sticker to the given object for equality.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StaticSticker<?> that)) return false;
        if (!_id.equals(that._id)) return false;
        return _image.equals(that._image);
    }

    /**
     * Returns a string representation of the sticker.
     *
     * @return A string representation of the sticker.
     */
    @Override
    public String toString() {
        return "StaticSticker{" +
                "_id=" + _id +
                ", _image=" + _image.toString() +
                '}';
    }
}