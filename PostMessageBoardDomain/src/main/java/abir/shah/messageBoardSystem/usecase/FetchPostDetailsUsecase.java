package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.UserManagerFactory;
import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.exception.OnlyTheCreatorAndAdminCanModifyPost;
import abir.shah.messageBoardSystem.persistence.PostRepository;

import java.util.Date;
import java.util.List;

public class FetchPostDetailsUsecase
{
    public Post execute(String requesterUserId,String postId)
    {
        boolean isAdmin = UserManagerFactory.createInstance().isAdmin(requesterUserId);

        PostRepository postRepository = new PostRepository();
        Post p =  postRepository.fetchById(postId);

        if(isAdmin || p.getCreatorUserId().equals(requesterUserId))
            return  p;

        throw new OnlyTheCreatorAndAdminCanModifyPost();
    }
}
