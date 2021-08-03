package exception;

public class TeamException extends MyException {

    public TeamException(ErrorCode errorCode){
        super(errorCode);
    }
}
