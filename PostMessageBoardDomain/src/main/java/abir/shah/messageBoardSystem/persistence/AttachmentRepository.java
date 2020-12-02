package abir.shah.messageBoardSystem.persistence;


import abir.shah.messageBoardSystem.domain.entity.Attachment;
import org.h2.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttachmentRepository {

    public AttachmentRepository()
    {
        try {
            DriverManager.registerDriver(new Driver());

            Connection connection = createConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Attachment (" +
                    "id varchar(200)," +
                    "postId varchar(200)," +
                    "fileName varchar(200)," +
                    "mediaType varchar(200)," +
                    "fileAbsPath varchar(200)," +
                    "leng int(20)" +
                    ");");

            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:file:./MessageBoardSystemDataBase","user","password");


        } catch (SQLException throwables) {
            throw new  RuntimeException(throwables);
        }
    }

    public void save(Attachment attachment)
    {
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();

            statement.execute("insert into Attachment(" +
                    "id," +
                    "postId," +
                    "fileName," +
                    "mediaType," +
                    "fileAbsPath," +
                    "leng) " +
                    "values(" +
                    "'"+attachment.getId()+"' , " +
                    "'"+attachment.getPostId()+"' , " +
                    "'"+attachment.getFileName()+"' , " +
                    "'"+attachment.getMediaType()+"' , " +
                    "'"+attachment.getFileAbsPath()+"' , " +
                    ""+attachment.getLength() + ")"
            );

            statement.close();
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Attachment fetchByPostId(String postId)
    {
        for(Attachment attachment : fetchAll())
        {
            if(attachment.getPostId().equals(postId))
                return attachment;
        }

        throw new RuntimeException("no attachment with post id :"+postId);
    }

    public void delete(String attachementId) {
        try {

            Connection connection = createConnection();
            Statement statement = connection.createStatement();
            statement.execute("delete from Attachment where id='"+attachementId+"'");

        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public Attachment fetchById(String attachmentId) {
        for(Attachment attachment : fetchAll())
        {
            if(attachment.getId().equals(attachmentId))
                return attachment;
        }

        throw new RuntimeException("no attachment with id :"+attachmentId);
    }

    public  List<Attachment> fetchAll()
    {
        return fetchWithSelect("Select * from Attachment");
    }


    private List<Attachment> fetchWithSelect(String selectSql) {
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();

            List<Attachment> attachments = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while(resultSet.next())
            {
                String  id= resultSet.getString("id");
                String  postId= resultSet.getString("postId");
                String  fileName= resultSet.getString("fileName");
                String  mediaType= resultSet.getString("mediaType");
                String  fileAbsPath= resultSet.getString("fileAbsPath");
                long  length= resultSet.getLong("leng");

                attachments.add(new Attachment(id,postId,fileName,fileAbsPath,mediaType,length));
            }

            statement.close();
            return attachments;
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
