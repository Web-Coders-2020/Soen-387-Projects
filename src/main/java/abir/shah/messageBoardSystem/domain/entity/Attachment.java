package abir.shah.messageBoardSystem.domain.entity;

import java.util.UUID;

public class Attachment {

    private String id;
    private String postId;
    private String fileName;
    private String mediaType;
    private String fileAbsPath;
    private long length;

    public Attachment(String postId, String fileName, String mediaType, String fileAbsPath, long length) {
        this.id = UUID.randomUUID().toString();
        this.postId = postId;
        this.fileName = fileName;
        this.mediaType = mediaType;
        this.fileAbsPath = fileAbsPath;
        this.length = length;
    }

    public Attachment(String id, String postId, String fileName, String fileAbsPath, String mediaType, long length) {
        this(postId, fileName, mediaType, fileAbsPath, length);
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getFileAbsPath() {
        return fileAbsPath;
    }

    public long getLength() {
        return length;
    }

    public String getId() {
        return id;
    }
}
