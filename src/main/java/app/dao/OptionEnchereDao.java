package app.dao;

import app.model.OptionEnchere;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OptionEnchereDao extends DAO<OptionEnchere>{

    private static final String SELECT_ALL = "SELECT * FROM option_enchere";
    private static final String SELECT_BY_ID = "SELECT * FROM option_enchere WHERE option_enchere_id = ?";
    private static final String CREATE = "INSERT INTO option_enchere (option_enchere_libelle, option_enchere_prix_catalogue, option_enchere_prix_gold) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE option_enchere SET option_libelle = ?, option_prix_catalogue = ?, option_prix_gold = ? WHERE option_enchere_id = ?";
    private static final String DELETE = "DELETE FROM option_enchere WHERE option_enchere_id = ?";


    private ArrayList<OptionEnchere> getResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<OptionEnchere> optionEncheres = new ArrayList<>();
        OptionEnchere optionEnchere;
        while (resultSet.next()) {
            optionEnchere = new OptionEnchere();
            optionEnchere.setId(resultSet.getLong("option_enchere_id"));
            optionEnchere.setLibelle(resultSet.getString("option_enchere_libelle"));
            optionEnchere.setPrixCatalogue(resultSet.getFloat("option_enchere_prix_catalogue"));
            optionEnchere.setPrixGold(resultSet.getFloat("option_enchere_prix_gold"));
            optionEncheres.add(optionEnchere);
        }
        return optionEncheres;
    }

    public ArrayList<OptionEnchere> findAll(){

        ArrayList<OptionEnchere> optionEncheres = new ArrayList<>();
        try {
            Statement statement = super.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            optionEncheres = getResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionEncheres;
    }

    @Override
    public OptionEnchere findById(long id) {
        OptionEnchere optionEnchere = null;
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(SELECT_BY_ID, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            optionEnchere = !getResultSet(resultSet).isEmpty() ? getResultSet(resultSet).get(0) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionEnchere;
    }

    @Override
    public OptionEnchere create(OptionEnchere optionEnchere) {
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, optionEnchere.getLibelle());
            preparedStatement.setFloat(2, optionEnchere.getPrixCatalogue());
            preparedStatement.setFloat(3, optionEnchere.getPrixGold());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionEnchere;
    }

    @Override
    public OptionEnchere update(OptionEnchere optionEnchere) {
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(UPDATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, optionEnchere.getPrixCatalogue());
            preparedStatement.setFloat(2, optionEnchere.getPrixGold());
            preparedStatement.setString(3, optionEnchere.getLibelle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionEnchere;
    }

    @Override
    public void delete(OptionEnchere optionEnchere) {
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, optionEnchere.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
