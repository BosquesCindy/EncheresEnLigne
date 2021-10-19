package app.dao;

import app.model.Article;
import app.model.SignalerArticle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SignalerArticleDao extends DAO<SignalerArticle> {
//    ArrayList<SignalerArticle> signalerArticles = new ArrayList<SignalerArticle>();
    @Override
    public SignalerArticle findById(long id) {
        SignalerArticle signalerArticle=null;
        String query = "Select * from Article a, signaler_article sa Where a.article_id = sa.article_id(+) And a.article_id=?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                signalerArticle = new SignalerArticle();
                signalerArticle.setId(rs.getLong(1));
                signalerArticle.getArticle().setId(rs.getLong(2));
                signalerArticle.setCommentaire(rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  signalerArticle;
    }

    @Override
    public SignalerArticle create(SignalerArticle obj) {
        String query = "INSERT INTO SignalerArticle(article_id,signaler_article_commentaire) VALUES (?,?)";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,obj.getArticle().getId());
            preparedStatement.setString(2,obj.getCommentaire());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public SignalerArticle update(SignalerArticle obj) {
        String query = "UPDATE SignalerArticle SET signaler_article_commentaire=? WHERE article_id=?";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,obj.getCommentaire());
            preparedStatement.setLong(2,obj.getArticle().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(SignalerArticle obj) {
        String query = "DELETE FROM SignalerArticle WHERE article_id=?";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,obj.getArticle().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
