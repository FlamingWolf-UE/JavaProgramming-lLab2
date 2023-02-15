package Classes;

import Interfaces.Sticker;

import java.awt.*;
import java.rmi.server.UID;
import java.util.List;

/**

 This class represents an animated sticker, which is a type of sticker that consists of a sequence of images.

 It implements the Sticker interface, which requires implementing methods to get the image content and unique identifier of the sticker.

 @param <T> the type of the image content, which must extend the List interface and contain objects of type Image or its subtypes
 */
public class AnimatedSticker<T extends List<? extends Image>> implements Sticker<T> {

    /**

     A unique identifier for the sticker
     */
    protected UID _id;
    /**

     The image content of the sticker, represented as a sequence of images
     */
    protected T _imageFrames;
    /**

     Constructs a new AnimatedSticker with the given unique identifier and image content.
     @param id the unique identifier for the sticker
     @param imageFrames the image content of the sticker, represented as a sequence of images
     */
    public AnimatedSticker(UID id, T imageFrames) {
        _id = id;
        _imageFrames = imageFrames;
    }
    /**

     Gets the image content of the sticker.
     @return the image content of the sticker, represented as a sequence of images
     */
    @Override
    public T getImageContent() {
        return _imageFrames;
    }
    /**

     Gets the unique identifier of the sticker.
     @return the unique identifier of the sticker
     */
    @Override
    public UID get_id() {
        return _id;
    }
    /**

     Indicates whether some other object is "equal to" this one.
     Two AnimatedSticker objects are considered equal if and only if their unique identifiers and image content are equal.
     @param o the reference object with which to compare
     @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnimatedSticker<?> that)) return false;
        if (!_id.equals(that._id)) return false;
        return _imageFrames.equals(that._imageFrames);
    }
    /**

     Returns a hash code value for the object.
     This method is supported for the benefit of hash tables such as those provided by HashMap.
     @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        int result = _id.hashCode();
        result = 31 * result + _imageFrames.hashCode();
        return result;
    }
    /**

     Returns a string representation of the object.
     @return a string representation of the object
     */
    @Override
    public String toString() {
        return "AnimatedSticker{" +
                "_id=" + _id +
                ", _imageFrames=" + _imageFrames.toString() +
                '}';
    }
}