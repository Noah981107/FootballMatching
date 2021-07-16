package domain;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Users {

    @NotNull(groups = {UserValidationGroups.signUp.class, UserValidationGroups.signIn.class, UserValidationGroups.findPassword.class}, message = "아이디는 비워둘 수 없습니다.")
    @Size(min = 1, max = 20, groups = {UserValidationGroups.signUp.class, UserValidationGroups.signIn.class, UserValidationGroups.findPassword.class, UserValidationGroups.modification.class}, message = "아이디의 크기는 1~20글자 입니다.")
    protected String id;

    @NotNull(groups = {UserValidationGroups.signUp.class, UserValidationGroups.signIn.class, UserValidationGroups.changePassword.class}, message = "비밀번호는 비워둘 수 없습니다.")
    @Size(min = 1, max = 20, groups = {UserValidationGroups.signUp.class, UserValidationGroups.signIn.class, UserValidationGroups.changePassword.class, UserValidationGroups.modification.class}, message = "비밀번호 크기는 1~20글자 입니다.")
    protected String password;

    @NotNull(groups = {UserValidationGroups.signUp.class, UserValidationGroups.findId.class, UserValidationGroups.findPassword.class}, message = "이름은 비워둘 수 없습니다.")
    protected String name;

    @NotNull(groups = {UserValidationGroups.signUp.class, UserValidationGroups.findId.class, UserValidationGroups.findPassword.class}, message = "전화번호는 비워둘 수 없습니다.")
    @Size(min = 12, max = 13, groups = {UserValidationGroups.signUp.class, UserValidationGroups.findId.class, UserValidationGroups.findPassword.class}, message = "전화번호는 12자~13자 입니다.")
    protected String phoneNumber;

    protected int locationCode;

    @ApiModelProperty(hidden = true)
    protected String joinDate;

    @ApiModelProperty(hidden = true)
    protected Boolean isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
