package domain.Interfaces;

import java.io.Serializable;
import java.rmi.server.UID;

/**
 * Sticker interface what represents methods to working with Stickers
 */
public interface Sticker<T> extends Serializable {

    /**
     * Returns the image content of the sticker.
     *
     * @return the image content of the sticker
     */
    T getImageContent();

    /**
     * Returns the unique identifier of the sticker.
     *
     * @return the unique identifier of the sticker
     */
    UID get_id();

}