package app.dao;

import app.model.Article;
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
    private static final String SELECT_BY_ARTICLE = "SELECT * FROM enchere WHERE article_id = ? ORDER BY enchere_montant DESC";
    private static final String SELECT_BY_MEMBRE = "SELECT * FROM enchere WHERE membre_id = ? ORDER BY enchere_montant DESC ";
    private static final String SELECT_BY_ARTICLE_MEMBRE = "SELECT * FROM enchere WHERE article_id = ? AND membre_id = ? ORDER BY enchere_montant DESC";
    private static final String DELETE_BY_ID = "DELETE FROM enchere WHERE enchere_id = ?";
    private static final String CREATE = "INSERT INTO enchere (enchere_montant, enchere_date_heure, membre_id, article_id) VALUES (?, ?, ?, ?)";

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

    public Enchere findDernierEnchere(Article article){
        return null;
    }

    public ArrayList<Enchere> findByArticle(Article article) {
        ArrayList<Enchere> encheres = new ArrayList<>();
        MembreDao membreDao = new MembreDao();
        Enchere enchere = null;
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(SELECT_BY_ARTICLE);
            preparedStatement.setLong(1, article.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                enchere = new Enchere();
                enchere.setId(resultSet.getLong("enchere_id"));
                enchere.setArticle(article);
                enchere.setDate(resultSet.getDate("enchere_date_heure"));
                enchere.setMembre(membreDao.findById(resultSet.getLong("membre_id")));
                enchere.setMontant(resultSet.getFloat("enchere_montant"));
                encheres.add(enchere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encheres;
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
    public Enchere create(Enchere enchere) {
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, enchere.getMontant());
            preparedStatement.setDate(2, enchere.getDate());
            preparedStatement.setLong(3, enchere.getMembre().getId());
            preparedStatement.setLong(4, enchere.getArticle().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) enchere.setId(rs.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enchere;
    }

    @Override
    public Enchere update(Enchere obj) {
        return null;
    }

    @Override
    public void delete(Enchere obj) {

    }
}
