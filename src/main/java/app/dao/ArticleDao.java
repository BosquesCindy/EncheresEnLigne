package app.dao;

import app.model.Article;
import app.model.Categorie;
import app.model.Membre;
import app.model.MembrePlus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleDao extends DAO<Article>{

    private static final String SELECT_ALL = "SELECT * FROM article";
    private static String SELECT_BY_ID = "SELECT * FROM article WHERE  = ?";
    private static String SELECT_BY_VENDEUR = "SELECT * FROM article WHERE membre_vendeur_id = ?";
    private static String SELECT_BY_ACHETEUR = "SELECT * FROM article WHERE membre_acheteur_id = ?";
    private static String SELECT_BY_CATEGORIE = "SELECT * FROM article WHERE categorie_id = ?";
    private static String DELETE_BY_ID = "DELETE FROM article WHERE article_id = ?";
    private static final String CREATE = "INSERT INTO article (article_titre,article_description,article_frais_port,article_region_livraison,article_date_heure_creation,article_date_cloture,article_mode_cloture,article_montant_vente,article_prix_depart,article_prix_reserve,article_prix_achat_immediat,categorie_id,option_enchere_id,membre_vendeur_id,membre_acheteur_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    public ArrayList<Article> findAll(){
        ArrayList<Article> articles=null;
        try{
            articles=new ArrayList<>();
            PreparedStatement stmt= super.connection.prepareStatement(SELECT_ALL);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                Article article=this.findById(rs.getLong("article_id"));
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    public ArrayList<Article> findByVendeur(Membre membre){
        ArrayList<Article> articlesByVenduer=null;
        SELECT_BY_VENDEUR=SELECT_BY_VENDEUR+membre.getId();
        try{
            articlesByVenduer=new ArrayList<>();
            PreparedStatement stmt= super.connection.prepareStatement(SELECT_BY_VENDEUR);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                Article article=this.findById(rs.getLong("article_id"));
                articlesByVenduer.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articlesByVenduer;
    }

    public ArrayList<Article> findByAcheteur(Membre membre){
        ArrayList<Article> articlesByAcheteur=null;
        SELECT_BY_ACHETEUR=SELECT_BY_ACHETEUR+membre.getId();
        try{
            articlesByAcheteur=new ArrayList<>();
            PreparedStatement stmt= super.connection.prepareStatement(SELECT_BY_ACHETEUR);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                Article article=this.findById(rs.getLong("article_id"));
                articlesByAcheteur.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articlesByAcheteur;
    }

    public ArrayList<Article> findByCategorie(Categorie categorie){
        ArrayList<Article> articlesByCategorie=null;
        SELECT_BY_CATEGORIE=SELECT_BY_CATEGORIE+categorie.getId();
        try{
            articlesByCategorie=new ArrayList<>();
            PreparedStatement stmt= super.connection.prepareStatement(SELECT_BY_CATEGORIE);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                Article article=this.findById(rs.getLong("article_id"));
                articlesByCategorie.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articlesByCategorie;
    }

    @Override
    public Article findById(long id) {
        Article article=null;
        SELECT_BY_ID=SELECT_BY_ID+id;
        try{
            PreparedStatement stmt= super.connection.prepareStatement(SELECT_BY_ID, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                article=new Article();
                article.setId(id);
                article.setTitre(rs.getString("article_titre"));
                article.setDescription(rs.getString("article_description"));
                article.setFraisPort(rs.getFloat("article_frais_port"));
                article.setRegionLivraison(rs.getString("article_region_livraison"));
                article.setDateHeureCreation(rs.getTimestamp("article_date_heure_creation"));
                article.setDateCloture(rs.getDate("article_date_cloture"));
                article.setModeCloture(rs.getString("article_mode_cloture"));
                article.setMontantVente(rs.getFloat("article_montant_vente"));
                article.setPrixDepart(rs.getFloat("article_prix_depart"));
                article.setPrixReserve(rs.getFloat("article_prix_reserve"));
                article.setPrixAchatImmediat(rs.getFloat("article_prix_achat_immediat"));
                CategorieDao categorieDao=new CategorieDao();
                article.setCategorie(categorieDao.findById(rs.getLong("categorie_id")));
                OptionEnchereDao optionEnchereDao=new OptionEnchereDao();
                article.setOption(optionEnchereDao.findById(rs.getLong("option_enchere_id")));
                MembreDao membreDao=new MembreDao();
                article.setVendeur(membreDao.findById(rs.getLong("membre_vendeur_id")));
                article.setAcheteur(membreDao.findById(rs.getLong("membre_acheteur_id")));

                return article;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            stmt.setLong(12,article.getCategorie().getId());
            stmt.setLong(13,article.getOption().getId());
            stmt.setLong(14,article.getVendeur().getId());
            stmt.setLong(15,article.getAcheteur().getId());
            if(article.getVendeur() instanceof MembrePlus){
                stmt.setFloat(10,article.getPrixReserve());
                stmt.setFloat(11,article.getPrixAchatImmediat());
            }
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
        DELETE_BY_ID=DELETE_BY_ID+article.getId();
        try{
            PreparedStatement stmt = super.connection.prepareStatement(DELETE_BY_ID, Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getResultSet();
            System.out.println(rs);
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
