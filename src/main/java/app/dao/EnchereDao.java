package app.dao;

import app.model.Enchere;
import app.model.OptionEnchere;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EnchereDao extends DAO<Enchere> {

    private static final String SELECT_ALL = "SELECT * FROM enchere";
    private static final String SELECT_BY_ID = "SELECT * FROM enchere WHERE enchere_id = ?";
    private static final String SELECT_BY_ARTICLE = "SELECT * FROM enchere WHERE article_id = ?";
    private static final String SELECT_BY_MEMBRE = "SELECT * FROM enchere WHERE membre_id = ?";
    private static final String SELECT_BY_ARTICLE_MEMBRE = "SELECT * FROM enchere WHERE article_id = ? AND membre_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM enchere WHERE enchere_id = ?";
    private static final String CREATE = "INSERT INTO enchere (enchere_id, enchere_montant, enchere_date, membre_id, article_id) VALUES (?, ?, ?, ?, ?)";

    private Enchere getResultSet(ResultSet resultSet) {
        return null;
    }

    public ArrayList<Enchere> findAll(){
        ArrayList<Enchere> encheres = new ArrayList<>();
        Enchere enchere;
        try {
            Statement statement = super.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Enchere> findByArticle(long id) {
        return null;
    }

    public ArrayList<Enchere> findByMembre(long id) {
        return null;
    }

    public ArrayList<Enchere> findByArticleMembre(long articleId, long membreId) {
        return null;
    }

    @Override
    public Enchere findById(long id) {
        return null;
    }

    @Override
    public Enchere create(Enchere obj) {
        return null;
    }

    @Override
    public Enchere update(Enchere obj) {
        return null;
    }

    @Override
    public void delete(Enchere obj) {

    }
}
