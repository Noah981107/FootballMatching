package domain;

import javax.validation.constraints.NotNull;

public class Comment {

    @NotNull(groups = {ValidationGroups.commentModification.class, ValidationGroups.commentDeletion.class }, message = "댓글 번호는 비워둘 수 없습니다.")
    protected String id;

    protected String writer;

    @NotNull(groups = {ValidationGroups.commentRegistration.class, ValidationGroups.commentModification.class, ValidationGroups.commentDeletion.class}, message = "게시글 번호는 비워둘 수 없습니다.")
    protected String boardNumber;
    protected String postDate;
    protected String modifiedDate;

    @NotNull(groups = {ValidationGroups.commentRegistration.class, ValidationGroups.commentModification.class}, message = "내용은 비워둘 수 없습니다.")
    protected String content;

    protected Boolean is_deleted;

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

    public String getBoardNumber() {
        return boardNumber;
    }

    public void setBoardNumber(String boardNumber) {
        this.boardNumber = boardNumber;
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

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
