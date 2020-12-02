package abir.shah.messageBoardSystem.exception;

public class NoSuchPostWithId extends RuntimeException{
    String postId;

    public NoSuchPostWithId( String postId) {
        super(postId);
        this.postId = postId;
    }
}
