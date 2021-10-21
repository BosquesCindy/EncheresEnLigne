package app.dao;

import app.model.Article;
import app.model.Categorie;
import app.model.Membre;
import app.model.MembrePlus;

import java.lang.invoke.TypeDescriptor;
import java.sql.*;
import java.util.ArrayList;

public class ArticleDao extends DAO<Article>{

    private static final String SELECT_ALL = "SELECT * FROM article";
    private static final String SELECT_BY_ID = "SELECT * FROM article WHERE artcle_id = ?";
    private static final String SELECT_BY_VENDEUR = "SELECT * FROM article WHERE membre_vendeur_id = ?";
    private static final String SELECT_BY_ACHETEUR = "SELECT * FROM article WHERE membre_acheteur_id = ?";
    private static final String SELECT_BY_CATEGORIE = "SELECT * FROM article WHERE categorie_id = ?";
    private static final String DELETE = "DELETE FROM article WHERE article_id = ?";
    private static final String CREATE = "INSERT INTO article (article_titre," +
                                                                " article_description," +
                                                                " article_frais_port," +
                                                                " article_region_livraison," +
                                                                " article_date_heure_creation," +
                                                                " article_date_cloture," +
                                                                " article_mode_cloture," +
                                                                " article_montant_vente," +
                                                                " article_prix_depart," +
                                                                " article_prix_reserve," +
                                                                " article_prix_achat_immediat," +
                                                                " categorie_id," +
                                                                " option_enchere_id," +
                                                                " membre_vendeur_id)" +
                                                                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private ArrayList<Article> getResulSet(ResultSet resultSet) throws SQLException {
        ArrayList<Article> articles = new ArrayList<>();
        CategorieDao categorieDao = new CategorieDao();
        Categorie categorie;
        Membre membre;
        OptionEnchereDao optionEnchereDao = new OptionEnchereDao();
        MembreDao membreDao = new MembreDao();
        Article article;
        while (resultSet.next()) {

            article = new Article();
            article.setId(resultSet.getLong("article_id"));
            article.setTitre(resultSet.getString("article_titre"));
            article.setDescription(resultSet.getString("article_description"));
            article.setFraisPort(resultSet.getFloat("article_frais_port"));
            article.setRegionLivraison(resultSet.getString("article_region_livraison"));
            article.setDateHeureCreation(resultSet.getTimestamp("article_date_heure_creation"));
            article.setDateCloture(resultSet.getDate("article_date_cloture"));
            article.setModeCloture(resultSet.getString("article_mode_cloture"));
            article.setMontantVente(resultSet.getFloat("article_montant_vente"));
            article.setPrixDepart(resultSet.getFloat("article_prix_depart"));
            article.setPrixReserve(resultSet.getFloat("article_prix_reserve"));
            article.setPrixAchatImmediat(resultSet.getFloat("article_prix_achat_immediat"));
            article.setCategorie(categorieDao.findById(resultSet.getLong("categorie_id")));
            article.setOption(optionEnchereDao.findById(resultSet.getLong("option_enchere_id")) != null ? optionEnchereDao.findById(resultSet.getLong("option_enchere_id")) : null);
            article.setVendeur(membreDao.findById(resultSet.getLong("membre_vendeur_id")));
            article.setAcheteur(membreDao.findById(resultSet.getLong("membre_acheteur_id")) != null ? membreDao.findById(resultSet.getLong("membre_acheteur_id")) : null);
            articles.add(article);
        }
        return articles;
    }

    public ArrayList<Article> findAll(){
        ArrayList<Article> articles = new ArrayList<>();
        try{
            Statement statement= super.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            articles = getResulSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    public ArrayList<Article> findByVendeur(long id){
        ArrayList<Article> articles = new ArrayList<>();
        try{
            PreparedStatement preparedStatement= super.connection.prepareStatement(SELECT_BY_VENDEUR);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            articles = getResulSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }


    public ArrayList<Article> findByAcheteur(long id){
        ArrayList<Article> articles = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(SELECT_BY_ACHETEUR);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            articles = getResulSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    public ArrayList<Article> findByCategorie(long id){
        ArrayList<Article> articles = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(SELECT_BY_CATEGORIE);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            articles = getResulSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    @Override
    public Article findById(long id) {
        Article article = null;
        try{
            PreparedStatement preparedStatement= super.connection.prepareStatement(SELECT_BY_ID, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            article = getResulSet(resultSet).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article create(Article article) {
        try{
            PreparedStatement stmt= super.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,article.getTitre());
            stmt.setString(2,article.getDescription());
            stmt.setFloat(3,article.getFraisPort());
            stmt.setString(4,article.getRegionLivraison());
            stmt.setTimestamp(5,article.getDateHeureCreation());
            stmt.setDate(6, article.getDateCloture());
            stmt.setString(7,article.getModeCloture());
            stmt.setFloat(8,article.getMontantVente());
            stmt.setFloat(9,article.getPrixDepart());
            stmt.setFloat(10,article.getPrixReserve());
            stmt.setFloat(11,article.getPrixAchatImmediat());
            stmt.setLong(12,article.getCategorie().getId());
            if (article.getOption() != null)
                stmt.setLong(13,article.getOption().getId());
            else
                stmt.setNull(13, Types.NULL);


            stmt.setLong(14,article.getVendeur().getId());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                article.setId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public Article update(Article article) {
        String update="UPDATE article SET article_titre=?,article_description=?,article_frais_port=?,article_region_livraison=?,article_date_heure_creation?,article_date_cloture=?, article_mode_cloture=?,article_montant_vente=?,article_prix_depart=?,article_prix_reserve=?,article_prix_achat_immediat=?,categorie_id=?,option_enchere_id=?,membre_vendeur_id=?,membre_acheteur_id=? WHERE article_id=?"+article.getId();
        try{
            PreparedStatement stmt= super.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,article.getTitre());
            stmt.setString(2,article.getDescription());
            stmt.setFloat(3,article.getFraisPort());
            stmt.setString(4,article.getRegionLivraison());
            stmt.setTimestamp(5,article.getDateHeureCreation());
            stmt.setDate(6, article.getDateCloture());
            stmt.setString(7,article.getModeCloture());
            stmt.setFloat(8,article.getMontantVente());
            stmt.setFloat(9,article.getPrixDepart());
            stmt.setFloat(10,article.getPrixReserve());
            stmt.setFloat(11,article.getPrixAchatImmediat());
            stmt.setLong(12,article.getCategorie().getId());
            stmt.setLong(13,article.getOption().getId());
            stmt.setLong(14,article.getVendeur().getId());
            stmt.setLong(15,article.getAcheteur().getId());
            stmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return article;
    }

    @Override
    public void delete(Article article) {
        try{
            PreparedStatement preparedStatement = super.connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, article.getId());
            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean etatBloque(Article article){
        String query="SELECT * FROM signaler_article Where article_id=?";
        try{
            PreparedStatement stmt = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1,article.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getResultSet();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
