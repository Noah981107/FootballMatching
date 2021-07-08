package exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    //User
    Id_Already_Exists("USER_ERROR_01", "이미 아이디가 존재합니다.", HttpStatus.BAD_REQUEST),
    PhoneNumber_Already_Exists("USER_ERROR_01", "이미 휴대전화번호가 존재합니다.", HttpStatus.BAD_REQUEST),
    Id_Does_Not_Exists("USER_ERROR_03", "아이디가 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    Member_Dose_Not_Exists("USER_ERROR_04", "회원이 존재하지 않습니다.", HttpStatus.BAD_REQUEST),
    Id_Is_Empty("USER_ERROR_04", "아이디가 비어 있습니다.", HttpStatus.BAD_REQUEST),
    Password_Is_Empty("USER_ERROR_05", "비밀번호가 비어 있습니다.", HttpStatus.BAD_REQUEST),
    PhoneNumber_Is_Empty("USER_ERROR_06", "전화번호가 비어 있습니다.", HttpStatus.BAD_REQUEST),
    Name_Is_Empty("USER_ERROR_07", "이름이 비어 있습니다.", HttpStatus.BAD_REQUEST),
    Id_Does_Not_Match("USER_ERROR_08", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    Password_Does_Not_Match("USER_ERROR_09", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),

    ;

    private String code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus){
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
