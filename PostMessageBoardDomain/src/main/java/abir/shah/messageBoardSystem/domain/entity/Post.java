package abir.shah.messageBoardSystem.domain.entity;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Post {

    private String id;
    private String title;
    private String content;
    private String creatorUserId;
    private String creationDate;
    private boolean isUpdated = false;
    private String lastModificationDate ;
    private String attachmentId;
    private String authorizedGroupToView;

    public Post(String title, String content, String creatorUserId,String authorizedGroupToView) {

        this.id = UUID.randomUUID().toString();
        this.authorizedGroupToView = authorizedGroupToView;

        this.title = title;
        this.content = content;
        this.creatorUserId = creatorUserId;

        creationDate = new Date().toString();
        isUpdated =false;
        lastModificationDate = creationDate;
    }

    public Post(String id, String creatorUser, String title, String content, String lastModified, String creationDate,String attachmentId, boolean isUpdated,String authorizedGroupToView) {
        this.id = id;
        this.creationDate = creationDate;
        this.title = title;
        this.content = content;
        this.lastModificationDate = lastModified;
        this.isUpdated = isUpdated;
        this.creatorUserId = creatorUser;
        this.attachmentId = attachmentId;
        this.authorizedGroupToView = authorizedGroupToView;
    }

    public String getId() {
        return id;
    }

    public void update(String newTitle, String newContent) {
        this.title = newTitle;
        this.content = newContent;

        lastModificationDate = new Date().toString();
        isUpdated = true;
    }

    public String getCreatorUserId() {
        return creatorUserId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getLastModificationDate() {
        return lastModificationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public boolean isUpdated()
    {
        return isUpdated;
    }

    public List<String> getHashTagsOffTheContent()
    {
        List<String> hashtags = new ArrayList<>();
        boolean tagIsSeen = false;
        int tagLastPosition = -1;
        int index = -1;

        for(char ch :content.toCharArray())
        {
            index++;

            if(ch == '#')
            {
                tagIsSeen = true;
                tagLastPosition = index;
            }

            if(ch==' ' && tagIsSeen)
            {
                hashtags.add(content.substring(tagLastPosition+1,index));
                tagIsSeen = false;
            }
        }

        if(tagIsSeen)
            hashtags.add(content.substring(tagLastPosition+1));

        return hashtags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creatorUserId='" + creatorUserId + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", isUpdated=" + isUpdated +
                ", lastModificationDate='" + lastModificationDate + '\'' +
                '}';
    }

    public void attach(Attachment attachment) {

        if( attachmentId ==null || !attachmentId.isBlank())
            isUpdated = true;

        lastModificationDate = new Date().toString();
        this.attachmentId = attachment.getId();
    }

    public String getAttachementId() {
        return attachmentId;
    }

    public void deleteAttachment() {
        attachmentId = "";
        lastModificationDate = new Date().toString();
    }

    public String getAuthorizedGroupToView() {
        return authorizedGroupToView ;
    }
}
