package exception;

import org.springframework.http.HttpStatus;

public class MyException extends RuntimeException {

    private String code;
    private HttpStatus httpStatus;
    private String detail;
    private String name;

    public MyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
