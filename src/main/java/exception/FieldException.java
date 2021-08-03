package exception;

public class FieldException extends MyException {

    public FieldException(ErrorCode errorCode){
        super(errorCode);
    }
}
