import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.usecase.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "Attachments", urlPatterns = {"attachments"}, loadOnStartup = 1)
public class AttachmentsRestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String attachmentId = request.getParameter("attachmentId");
        String requesterUserId = Authentication.extractUserIdFromSession(request);

        DownloadAttachmentUsecase usecase = new DownloadAttachmentUsecase();
        response.getOutputStream().write(usecase.execute(attachmentId));
        response.setStatus(200);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requesterUserId = Authentication.extractUserIdFromSession(request);

        String postId = request.getParameter("postId");
        String fileName = request.getParameter("fileName");


        UploadAttachmentUsecase usecase = new UploadAttachmentUsecase();
        String id = usecase.execute(request.getInputStream(),fileName,"",requesterUserId,postId);


        byte[] payload = ("{\"id\": \"" + id + "\"}").getBytes();
        response.setContentType("application/json");
        response.getOutputStream().write(payload);
        response.setStatus(201);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requesterUserId = Authentication.extractUserIdFromSession(req);

        String attachmentId = req.getParameter("attachmentId");

        DeleteAttachmentUsecase deleteAttachmentUsecase = new DeleteAttachmentUsecase();
        deleteAttachmentUsecase.execute(attachmentId,requesterUserId);

        resp.setStatus(202);
    }
}