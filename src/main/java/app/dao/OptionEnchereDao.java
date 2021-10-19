package app.dao;

import app.model.OptionEnchere;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OptionEnchereDao extends DAO<OptionEnchere>{

    static final String SELECT_ALL = "SELECT * FROM option_enchere";

    public ArrayList<OptionEnchere> findAll(){

        ArrayList<OptionEnchere> optionEncheres = new ArrayList<>();
        OptionEnchere optionEnchere;
        try {
            Statement statement = super.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                optionEnchere = new OptionEnchere();
                optionEnchere.setId(resultSet.getLong("option_enchere_id"));
                optionEnchere.setLibelle(resultSet.getString("option_enchere_libelle"));
                optionEnchere.setPrixCatalogue(resultSet.getFloat("option_enchere_prix_catalogue"));
                optionEnchere.setPrixGold(resultSet.getFloat("option_enchere_prix_gold"));
                optionEncheres.add(optionEnchere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionEncheres;
    }

    @Override
    public OptionEnchere findById(long id) {
        OptionEnchere optionEnchere=null;
        String query = "SELECT * FROM optionj WHERE option_id="+id;
        try {
            Statement statement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                optionEnchere = new OptionEnchere();
                optionEnchere.setId(resultSet.getLong("option_enchere_id"));
                optionEnchere.setLibelle(resultSet.getString("option_enchere_libelle"));
                optionEnchere.setPrixCatalogue(resultSet.getFloat("option_enchere_prix_catalogue"));
                optionEnchere.setPrixGold(resultSet.getFloat("option_enchere_prix_gold"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionEnchere;
    }

    @Override
    public OptionEnchere create(OptionEnchere obj) {
        String query = "INSERT INTO optionj(option_libelle,option_prix_catalogue,option_prix_gold) " +
                "VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getLibelle());
            preparedStatement.setFloat(2, obj.getPrixCatalogue());
            preparedStatement.setFloat(3, obj.getPrixGold());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public OptionEnchere update(OptionEnchere obj) {
        String query = "UPDATE optionj SET option_prix_catalogue=?,option_prix_gold=? WHERE option_libelle=?" ;
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, obj.getPrixCatalogue());
            preparedStatement.setFloat(2, obj.getPrixGold());
            preparedStatement.setString(3, obj.getLibelle());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(OptionEnchere obj) {
        String query = "DELETE FROM option_enchere WHERE option_id=?";
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
