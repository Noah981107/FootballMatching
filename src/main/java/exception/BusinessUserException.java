package exception;

public class BusinessUserException extends MyException {

    public BusinessUserException(ErrorCode errorCode){
        super(errorCode);
    }
}
