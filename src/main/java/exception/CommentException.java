package exception;

public class CommentException extends MyException{

    public CommentException(ErrorCode errorCode){
        super(errorCode);
    }
}
