package domain;

import javax.validation.constraints.NotNull;

public class Team {

    @NotNull(groups = {UserValidationGroups.teamRegistration.class, UserValidationGroups.teamModification.class}, message = "팀 이름은 비워둘 수 없습니다.")
    protected String name;

    protected String representative;

    @NotNull(groups = {UserValidationGroups.teamModification.class}, message = "지역 코드는 비워둘 수 없습니다.")
    protected int locationCode;

    @NotNull(groups = {UserValidationGroups.teamRegistration.class, UserValidationGroups.teamModification.class}, message = "팀 정보를 비워둘 수 없습니다.")
    protected String information;
    protected String registrationDate;
    protected String modifiedDate;
    protected Boolean isDeleted;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public int getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(int locationCode) {
        this.locationCode = locationCode;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}