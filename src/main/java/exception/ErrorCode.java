package exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode{

    //User, Business_User
    /*있음*/Id_Already_Exists("USER_ERROR_01", "이미 아이디가 존재합니다.", HttpStatus.BAD_REQUEST),
    /*있음*/PhoneNumber_Already_Exists("USER_ERROR_02", "이미 휴대전화번호가 존재합니다.", HttpStatus.BAD_REQUEST),
    /*있음*/Id_Does_Not_Exists("USER_ERROR_03", "아이디가 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    /*있음*/Member_Dose_Not_Exists("USER_ERROR_04", "회원이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    Id_Is_Empty("USER_ERROR_05", "아이디가 비어 있습니다.", HttpStatus.BAD_REQUEST),
    Password_Is_Empty("USER_ERROR_06", "비밀번호가 비어 있습니다.", HttpStatus.BAD_REQUEST),
    PhoneNumber_Is_Empty("USER_ERROR_07", "전화번호가 비어 있습니다.", HttpStatus.BAD_REQUEST),
    Name_Is_Empty("USER_ERROR_08", "이름이 비어 있습니다.", HttpStatus.BAD_REQUEST),
    /*있음*/Id_Does_Not_Match("USER_ERROR_09", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    /*있음*/Password_Does_Not_Match("USER_ERROR_10", "비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST),
    Field_Name_Is_Empty("USER_ERROR_11", "구장 이름이 비어있습니다.", HttpStatus.BAD_REQUEST),

    //Team
    /*있음*/Registered_Team_Is_Empty("TEAM_ERROR_01", "등록된 팀이 없습니다.", HttpStatus.NOT_FOUND),
    /*있음*/Already_Registered_Team("TEAM_ERROR_02", "이미 등록하신 팀이 있습니다.", HttpStatus.BAD_REQUEST),
    /*있음*/Not_Team_Registered_For("TEAM_ERROR_03", "본인이 등록한 팀이 아닙니다.", HttpStatus.BAD_REQUEST),

    //Field
    /*있음*/Registered_Field_Is_Empty("FIELD_ERROR_01", "등록된 구장이 없습니다.", HttpStatus.NOT_FOUND),
    Filed_Already_Exists("FIELD_ERROR_02", "이미 구장이 등록되어 있습니다.", HttpStatus.BAD_REQUEST),

    //Team-Board
    Registered_Post_Is_Empty("TEAMBOARD_ERROR_01", "등록된 게시물이 없습니다.",HttpStatus.NOT_FOUND),

    //Comment
    Registered_Comment_Is_Empty("COMMENT_ERROR_01", "등록된 댓글이 없습니다.", HttpStatus.NOT_FOUND),

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
