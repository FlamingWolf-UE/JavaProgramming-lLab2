package domain.Interfaces;

import java.io.Serializable;

public interface ICommand extends Serializable {
    public void execute();
}
