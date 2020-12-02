package abir.shah.messageBoardSystem.persistence;


import abir.shah.messageBoardSystem.domain.entity.Post;
import abir.shah.messageBoardSystem.exception.NoSuchPostWithId;
import org.h2.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostRepository {

    public PostRepository() {
        try {
            DriverManager.registerDriver(new Driver());

            Connection connection = createConnection();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS Post (" +
                    "id varchar(200)," +
                    "creatorUser varchar(200)," +
                    "title varchar(200)," +
                    "content varchar(200)," +
                    "lastModified varchar(200)," +
                    "creationDate varchar(200)," +
                    "attachmentId varchar(200)," +
                    "authorizedGroupToView varchar(200)," +
                    "isUpdated bool" +
                    ");");

            statement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void save(Post post)
    {
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();

            statement.execute("insert into Post(id,creatorUser,title,content,lastModified,creationDate,attachmentId,authorizedGroupToView,isUpdated) " +
                    "values(" +
                    "'"+post.getId()+"' , " +
                    "'"+post.getCreatorUserId()+"' , " +
                    "'"+post.getTitle()+"' , " +
                    "'"+post.getContent()+"' , " +
                    "'"+post.getLastModificationDate()+"' , " +
                    "'"+post.getCreationDate()+"' , " +
                    "'"+post.getAttachementId()+"' , " +
                    "'"+post.getAuthorizedGroupToView()+"' , " +
                    ""+post.isUpdated()+")"
            );

            statement.close();
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:h2:file:./MessageBoardSystemDataBase","user","password");


        } catch (SQLException throwables) {
            throw new  RuntimeException(throwables);
        }
    }

    public void deleteById(String postId)
    {
        try {

            Connection connection = createConnection();
            Statement statement = connection.createStatement();
            statement.execute("delete from Post where id='"+postId+"'");

        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    public Post fetchById(String postId)
    {
        try{
            return fetchPostsWithSelect("select * from Post where id = '"+ postId +"'").get(0);
        }catch (Exception e)
        {
            throw new NoSuchPostWithId(postId);
        }
    }

    private List<Post> fetchPostsWithSelect(String selectSql) {
        try {
            Connection connection = createConnection();
            Statement statement = connection.createStatement();

            List<Post> posts = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while(resultSet.next())
            {
                String  id= resultSet.getString("id");
                String  creatorUser= resultSet.getString("creatorUser");
                String  title= resultSet.getString("title");
                String  content= resultSet.getString("content");
                String  lastModified= resultSet.getString("lastModified");
                String  creationDate= resultSet.getString("creationDate");
                String  attachmentId= resultSet.getString("attachmentId");
                String  authorizedGroupToView= resultSet.getString("authorizedGroupToView");
                boolean  isUpdated= resultSet.getBoolean("isUpdated");
                posts.add(new Post(id,creatorUser,title,content,lastModified,creationDate,attachmentId,isUpdated,authorizedGroupToView));
            }

            statement.close();
            return posts;
        }catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public void update(Post post)
    {
        deleteById(post.getId());
        save(post);
    }

    public List<Post> fetch(List<String> groups,String creatorId, Date startDate , Date endDate , List<String> hashTags)
    {

        boolean hasCreatorId = creatorId!=null && !creatorId.isBlank();
        boolean hasStartDate = startDate!=null;
        boolean hasEndDate = endDate!=null;
        boolean hasHashTags = hashTags!=null && !hashTags.isEmpty();
        boolean hasGroups = groups != null;

        boolean hasCriteria = hasCreatorId || hasStartDate || hasEndDate || hasHashTags || hasGroups;

        String contentHashTagCriteria = "(";

        if(hasHashTags)
        {
            for(String hashTag : hashTags)
            {
                if(contentHashTagCriteria.length() > 1)
                    contentHashTagCriteria += " and ";

                contentHashTagCriteria += " content like '%"+hashTag+"%'  ";
            }

            contentHashTagCriteria += ")";
        }


        String allGroupsString = "(";
        if(hasGroups)
        {

            for(String group: groups)
                allGroupsString += (allGroupsString.length()>1 ?"," :"") + "'"+group+"'";

            allGroupsString += ")";
        }



        String selectSql = "select * from post " +

                (hasCriteria ? " where ":"")+
                (hasCreatorId ? " creatorUser='" + creatorId +"'   and": "")+
                (hasStartDate ?"'  creationDate> '" + startDate.toString() + "'   and" : "") +
                (hasEndDate ? "creationDate< '" + startDate.toString() + "'   and" : "") +
                (hasGroups ? "((authorizedGroupToView in   "+allGroupsString+") or authorizedGroupToView='' or authorizedGroupToView='null') and" : "") +
                (hasHashTags ? contentHashTagCriteria : "");

        if(selectSql.endsWith("and"))
            selectSql = selectSql.substring(0,selectSql.length()-3);

        return fetchPostsWithSelect(selectSql);
    }

    public List<Post> fetchAll() {
        return fetchPostsWithSelect("select * from post");
    }
}
