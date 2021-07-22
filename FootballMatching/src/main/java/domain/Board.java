package domain;

import javax.validation.constraints.NotNull;

public class Board {

    protected String id;
    protected String writer;

    @NotNull(groups = {UserValidationGroups.boardWrite.class, UserValidationGroups.boardModification.class}, message = "팀 이름은 비워둘 수 없습니다.")
    protected String teamName;

    protected String postDate;
    protected String modifiedDate;

    @NotNull(groups = {UserValidationGroups.boardWrite.class, UserValidationGroups.boardModification.class}, message = "내용은 비워둘 수 없습니다.")
    protected String content;
    protected Boolean isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
