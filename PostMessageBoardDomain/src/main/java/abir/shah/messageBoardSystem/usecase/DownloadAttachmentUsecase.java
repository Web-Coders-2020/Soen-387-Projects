package abir.shah.messageBoardSystem.usecase;


import abir.shah.messageBoardSystem.domain.entity.Attachment;
import abir.shah.messageBoardSystem.persistence.AttachmentRepository;

import java.io.FileInputStream;

public class DownloadAttachmentUsecase {

    public byte[] execute(String attachmentId)
    {
        AttachmentRepository repository = new AttachmentRepository();
        Attachment attachment = repository.fetchById(attachmentId);
        byte[] allBytes = readAllBytesOfFile(attachment);
        return allBytes;
    }

    private byte[] readAllBytesOfFile(Attachment attachment) {
       try {
           FileInputStream inputStream = new FileInputStream(attachment.getFileAbsPath());
           byte[] allBytes=inputStream.readAllBytes();
           inputStream.close();
           return allBytes;
       }catch (Exception e)
       {
           throw new RuntimeException(e);
       }
    }

}
