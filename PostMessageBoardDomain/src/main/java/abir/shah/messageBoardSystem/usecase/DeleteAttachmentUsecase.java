package abir.shah.messageBoardSystem.usecase;

import abir.shah.messageBoardSystem.domain.entity.Attachment;
import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.exception.OnlyTheCreatorCanAttatchFile;
import abir.shah.messageBoardSystem.persistence.AttachmentRepository;
import abir.shah.messageBoardSystem.persistence.PostRepository;

public class DeleteAttachmentUsecase {

    public void execute(String attachmentId,String requesterUserId)
    {
        PostRepository postRepository = new PostRepository();
        AttachmentRepository attachmentRepository = new AttachmentRepository();

        Attachment attachment = attachmentRepository.fetchById(attachmentId);
        Post post = postRepository.fetchById(attachment.getPostId());

        if(!post.getCreatorUserId().equals(requesterUserId))
            throw new OnlyTheCreatorCanAttatchFile();

        attachmentRepository.delete(post.getAttachementId());
        post.deleteAttachment();
        postRepository.update(post);
    }

}
