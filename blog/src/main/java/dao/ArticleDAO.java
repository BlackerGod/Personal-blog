package dao;

import ZMQ.model.Article;
import ZMQ.utli.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    //JDBC文章添加
    public static boolean addArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DBUtil.getConnection();
            String sql = "insert into article(title, content, user_id,create_time) "+
                    "values(?,?,?,?);";
            preparedStatement = connection.prepareStatement(sql);
            //替换占位符
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setString(2,article.getContent());
            preparedStatement.setInt(3,article.getUserId());
            preparedStatement.setTimestamp(4,
                    new Timestamp(new java.util.Date().getTime()));

            //成功的条数
            int num = preparedStatement.executeUpdate();
            return num > 0;
        } catch (Exception e){
                throw new RuntimeException("文章天机jdbc操作失败:",e);
        } finally {
            DBUtil.close(connection,preparedStatement);
        }
    }


    //查询文章列表
    public static List<Article> listArticle(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        try{
            connection = DBUtil.getConnection();
            String sql = "select id,title,content,user_id,create_time" +
                    " from article where user_id=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Article article = new Article();
              article.setId(resultSet.getInt("id"));
              article.setTitle(resultSet.getString("title"));
              article.setContent(resultSet.getString("content"));
              article.setUserId(id);
              article.setCreateTime(new java.util.Date(
                      resultSet.getTimestamp("create_time").getTime()));
                articles.add(article);
            }
            return articles;
        } catch (Exception e){
            throw new RuntimeException("文章查询失败:",e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
    }

    public static Article queryArticleById(Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = DBUtil.getConnection();
            String sql = "select id,title,content,user_id,create_time" +
                    " from article where user_id=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            Article article = new Article();
            while (resultSet.next()){
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(id);
                article.setCreateTime(new java.util.Date(
                        resultSet.getTimestamp("create_time").getTime()));
            }
            return article;
        } catch (Exception e){
            throw new RuntimeException("文章详细查询失败:",e);
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }
    }
}
