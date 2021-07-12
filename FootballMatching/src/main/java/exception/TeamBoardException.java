package exception;

public class TeamBoardException extends MyException {

    public TeamBoardException(ErrorCode errorCode){
        super(errorCode);
    }
}
