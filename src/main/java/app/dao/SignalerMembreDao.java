package app.dao;

import app.model.SignalerMembre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignalerMembreDao extends DAO<SignalerMembre> {
    @Override
    public SignalerMembre findById(long id) {
        SignalerMembre signalerMembre=null;
        String query = "Select * from Membre m, signaler_membre sm Where m.membre_id = sm.membre_id(+) And m.membre_id=?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                signalerMembre = new SignalerMembre();
                signalerMembre.setId(rs.getLong(1));
                signalerMembre.getMembre().setId(rs.getLong(2));
                signalerMembre.setCommentaire(rs.getString(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return signalerMembre;
    }

    @Override
    public SignalerMembre create(SignalerMembre obj) {
        String query = "INSERT INTO SignalerMembre(membre_id,signaler_membre_commentaire) VALUES ("+obj.getMembre().getId()+","+obj.getCommentaire()+")";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public SignalerMembre update(SignalerMembre obj) {
        String query = "UPDATE SignalerMembre SET signaler_membre_commentaire=? WHERE membre_id=?";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,obj.getCommentaire());
            preparedStatement.setLong(2,obj.getMembre().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(SignalerMembre obj) {
        String query = "DELETE FROM SignalerMembre WHERE membre_id=?";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,obj.getMembre().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
