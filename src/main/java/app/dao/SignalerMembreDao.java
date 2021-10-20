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
        String query = "Select * from signaler_membre Where signaler_membre_id=?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query , Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                signalerMembre = new SignalerMembre();
                signalerMembre.setId(rs.getLong("signaler_membre_id"));
                MembreDao membreDao=new MembreDao();
                ServiceJuridiqueDao serviceJuridiqueDao = new ServiceJuridiqueDao();
                signalerMembre.setMembre(membreDao.findById(rs.getLong("membre_id")));
                signalerMembre.setCommentaire(rs.getString("signaler_membre_commentaire"));
                signalerMembre.setServiceJuridique(serviceJuridiqueDao.findById(rs.getLong("service_juridique_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return signalerMembre;
    }

    @Override
    public SignalerMembre create(SignalerMembre obj) {
        String query = "INSERT INTO SignalerMembre(membre_id,signaler_membre_commentaire,service_juridique_id) VALUES (?,?,?)";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,obj.getMembre().getId());
            preparedStatement.setString(2,obj.getCommentaire());
            preparedStatement.setLong(3,obj.getServiceJuridique().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                obj.setId(rs.getLong("signaler_membre_id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public SignalerMembre update(SignalerMembre obj) {
        String query = "UPDATE SignalerMembre SET signaler_membre_commentaire=? WHERE signaler_membre_id=?";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,obj.getCommentaire());
            preparedStatement.setLong(2,obj.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
        }catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(SignalerMembre obj) {
        String query = "DELETE FROM SignalerMembre WHERE signaler_membre_id=?";
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,obj.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.println(rs);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
