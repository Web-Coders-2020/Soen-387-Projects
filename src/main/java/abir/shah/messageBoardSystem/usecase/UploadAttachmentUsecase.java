package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.entity.Attachment;
import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.exception.OnlyTheCreatorCanAttatchFile;
import abir.shah.messageBoardSystem.persistence.AttachmentRepository;
import abir.shah.messageBoardSystem.persistence.PostRepository;

import java.io.*;

public class UploadAttachmentUsecase {

    public String execute(InputStream attachmentStream, String fileName, String mediaType, String uploaderUserId, String postId)
    {
        PostRepository postRepository = new PostRepository();
        AttachmentRepository attachmentRepository = new AttachmentRepository();

        Post post = postRepository.fetchById(postId);

        if(!post.getCreatorUserId().equals(uploaderUserId))
            throw new OnlyTheCreatorCanAttatchFile();

        File file = saveFile(attachmentStream, fileName, postId);

        Attachment attachment = new Attachment(postId,fileName,mediaType,file.getAbsolutePath(),file.length());
        attachmentRepository.save(attachment);

        post.attach(attachment);

        postRepository.update(post);

        return attachment.getId();
    }


    private File saveFile(InputStream attachmentStream, String fileName, String postId) {
        try {
            File directory =new  File("cache");
            directory.mkdirs();

            File file = new File(directory,"Post_"+ postId +"_"+ fileName);
            file.createNewFile();

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(attachmentStream.readAllBytes());
            outputStream.close();
            return file;
        }catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
    }


}
