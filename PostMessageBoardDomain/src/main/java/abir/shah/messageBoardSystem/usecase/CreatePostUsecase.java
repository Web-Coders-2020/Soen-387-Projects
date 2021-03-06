package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.persistence.PostRepository;

public class CreatePostUsecase
{
    public String execute(String title , String content , String creatorUserId ,String authorizedGroupName)
    {
        Post post = new Post(title,content,creatorUserId,authorizedGroupName);
        PostRepository postRepository = new PostRepository();
        postRepository.save(post);
        return post.getId();
    }
}
