package domain.model.Commands;

import domain.Interfaces.ICommand;
import domain.model.AuthRequest;

public class AuthUserCommand implements ICommand {

    private AuthRequest request;

    public AuthRequest getRequest() {
        return request;
    }

    public void setRequest(AuthRequest request) {
        this.request = request;
    }

    @Override
    public void execute() {

    }
}
