package exception;

public class UserException extends MyException{

    public UserException(ErrorCode errorCode){
        super(errorCode);
    }
}
