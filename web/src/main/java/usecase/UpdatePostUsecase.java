package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.persistence.PostRepository;

public class UpdatePostUsecase
{
    public void  execute(String postId , String newTitle , String newContent)
    {
        PostRepository postRepository = new PostRepository();
        Post post=postRepository.fetchById(postId);

        post.update(newTitle,newContent);

        postRepository.update(post);
    }
}
