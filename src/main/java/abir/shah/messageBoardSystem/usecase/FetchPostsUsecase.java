package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.persistence.PostRepository;

import java.util.Date;
import java.util.List;

public class FetchPostsUsecase
{
    public List<Post>  execute(String creatorId, Date startDate, Date endDate, List<String> hashTags)
    {
        PostRepository postRepository = new PostRepository();
        return postRepository.fetch(creatorId,startDate,endDate,hashTags);
    }
}
