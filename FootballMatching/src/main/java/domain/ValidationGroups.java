package domain;

public class ValidationGroups {

    //user
    public interface signUp {};
    public interface signIn {};
    public interface findId {};
    public interface findPassword {};
    public interface changePassword {};
    public interface modification {};

    //team
    public interface teamRegistration {};
    public interface teamModification {};

    //board
    public interface boardWrite {};
    public interface boardModification {};

    //comment
    public interface commentRegistration {};
    public interface commentModification {};
    public interface commentDeletion {};

}
