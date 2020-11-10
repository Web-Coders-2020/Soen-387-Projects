import abir.shah.messageBoardSystem.domain.entity.Attachment;
import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.domain.entity.User;
import abir.shah.messageBoardSystem.persistence.AttachmentRepository;
import abir.shah.messageBoardSystem.persistence.UserRepository;
import abir.shah.messageBoardSystem.usecase.*;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

public class Test1 {

    @Test
    public void test1()
    {
        CreatePostUsecase usecase = new CreatePostUsecase();
        String postId1 = usecase.execute("title1","content1 #hash1 dlsfhjldjh #hash2  dfhh","creatorUser1");
        String postId2 = usecase.execute("title2","content2","creatorUser2");
        String postId3 = usecase.execute("title3","content3  #hash1 fdhkljdsfh","creatorUser1");
        String postId4 = usecase.execute("title4","content4","creatorUser2");
        String postId5 = usecase.execute("title5","content5","creatorUser2");
        String postId6 = usecase.execute("title6","content6 #hash1","creatorUser3");
        String postId7 = usecase.execute("title7","content7 #hash2 eryery #hash1","creatorUser4");

        FetchPostsUsecase fetchPostsUsecase = new FetchPostsUsecase();
        List<Post> posts = fetchPostsUsecase.execute("creatorUser1",null,null,List.of("#hash1" ));

        posts.forEach((post)->{
            System.out.println(post.getTitle());
        });
    }

    @Test
    public void testingAttachment()
    {
        CreatePostUsecase createPostUsecase = new CreatePostUsecase();
        String postId1 = createPostUsecase.execute("title1","content1","creatorUser1");

        byte[] fileBytes = "Lorem ipsum.....".getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);


        UploadAttachmentUsecase uploadAttachmentUsecase = new UploadAttachmentUsecase();
        String attachmentId = uploadAttachmentUsecase.execute(byteArrayInputStream,"file1.txt","txt","creatorUser1",postId1);


        DownloadAttachmentUsecase downloadAttachmentUsecase = new DownloadAttachmentUsecase();
        byte[] downloadedData = downloadAttachmentUsecase.execute(attachmentId);


        System.out.println(new String(downloadedData));
    }


    @Test
    public void testingDelteAttachment()
    {
        CreatePostUsecase createPostUsecase = new CreatePostUsecase();
        String postId1 = createPostUsecase.execute("title1","content1","creatorUser1");

        byte[] fileBytes = "Lorem ipsum.....".getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);


        UploadAttachmentUsecase uploadAttachmentUsecase = new UploadAttachmentUsecase();
        String attachmentId = uploadAttachmentUsecase.execute(byteArrayInputStream,"file1.txt","txt","creatorUser1",postId1);

        DeleteAttachmentUsecase deleteAttachmentUsecase = new DeleteAttachmentUsecase();
        deleteAttachmentUsecase.execute(attachmentId,"creatorUser1");


        DownloadAttachmentUsecase downloadAttachmentUsecase = new DownloadAttachmentUsecase();
        byte[] downloadedData = downloadAttachmentUsecase.execute(attachmentId);


        System.out.println(new String(downloadedData));
    }

    @Test
    public void testPreDefinedUsersAuthentication()
    {
        UserRepository userRepository = new UserRepository();
        User user1 = userRepository.fetchAll().get(0);

        AuthenticateUserUsecase userUsecase = new AuthenticateUserUsecase();
        System.out.println(userUsecase.isAuthenticated(user1.getId(),user1.getHashedPassword()));
        System.out.println(userUsecase.isAuthenticated("fdh","sdglkj"));
    }

    @Test
    public void getHashTags()
    {
        Post post = new Post("title","sdkjgh  #lsd  #jh #lfdsjh","");
        System.out.println(post.getHashTagsOffTheContent());
    }
}
