package app.dao;

import app.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VoteDao extends DAO<Vote>{

    @Override
    public Vote findById(long id) {
        String query="SELECT * FROM vote WHERE vote_id="+id;
        try{
            PreparedStatement stmt= super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Vote vote = new Vote();
                vote.setId(id);
                vote.setCommantaire(rs.getString("vote_commentaire"));
                vote.setAvis(Avis.valueOf(rs.getString("avis")));
                ArticleDao articleDao=new ArticleDao();
                vote.setArticle(articleDao.findById(rs.getLong("article_id")));
                MembreDao membreDao=new MembreDao();
                vote.setMembre(membreDao.findById(rs.getLong("membre_id")));
                vote.setTypeMembre(TypeMembre.valueOf(rs.getString("type_membre")));
                return vote;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Vote> findByTypeMembre(Membre membre,TypeMembre typeMembre){
        String query="SELECT * FROM vote WHERE membre_id=? AND type_membre=?";
        ArrayList<Vote> votesByTypeMembre=null;
        try{
            votesByTypeMembre=new ArrayList<>();
            PreparedStatement stmt= super.connection.prepareStatement(query);
            stmt.setLong(1,membre.getId());
            stmt.setString(2, String.valueOf(typeMembre));
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                Vote vote=this.findById(rs.getLong("vote_id"));
                votesByTypeMembre.add(vote);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return votesByTypeMembre;
    }

    @Override
    public Vote create(Vote vote) {
        String query="INSERT INTO vote(vote_commentaire,avis,article_id,membre_id,type_membre) VALUEs(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, vote.getCommantaire());
            preparedStatement.setString(2, vote.getAvis().toString());
            preparedStatement.setLong(3, vote.getArticle().getId());
            preparedStatement.setLong(4, vote.getMembre().getId());
            preparedStatement.setString(5, vote.getTypeMembre().toString());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                vote.setId(rs.getLong("vote_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vote;
    }

    @Override
    public Vote update(Vote obj) {
        return null;
    }

    @Override
    public void delete(Vote obj) {

    }
}