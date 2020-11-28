package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.UserManagerFactory;
import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.exception.OnlyTheCreatorAndAdminCanModifyPost;
import abir.shah.messageBoardSystem.persistence.PostRepository;

public class UpdatePostUsecase
{
    public void  execute(String postId , String newTitle , String newContent,String requesterUserId)
    {
        PostRepository postRepository = new PostRepository();
        Post post=postRepository.fetchById(postId);

        boolean requesterIdAdmin = UserManagerFactory.createInstance().isAdmin(requesterUserId);

        if(!post.getCreatorUserId().equals(requesterUserId) && !requesterIdAdmin)
            throw new OnlyTheCreatorAndAdminCanModifyPost();

        post.update(newTitle,newContent);

        postRepository.update(post);
    }
}
