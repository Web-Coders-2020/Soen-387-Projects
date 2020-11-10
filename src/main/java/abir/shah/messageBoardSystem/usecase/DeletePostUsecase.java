package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.exception.OnlyTheCreatorCanAttatchFile;
import abir.shah.messageBoardSystem.persistence.PostRepository;

public class DeletePostUsecase
{
    public void  execute(String postId ,String requesterUserId)
    {
        PostRepository postRepository = new PostRepository();

        Post post = postRepository.fetchById(postId);

        if(!post.getCreatorUserId().equals(requesterUserId))
            throw new OnlyTheCreatorCanAttatchFile();

        postRepository.deleteById(postId);
    }
}
