package app.dao;

import app.model.Article;
import app.model.Categorie;
import app.model.Membre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ArticleDao extends DAO<Article>{

    private static final String SELECT_ALL = "SELECT * FROM enchere";
    private static final String SELECT_BY_ID = "SELECT * FROM enchere WHERE enchere_id = ?";
    private static final String SELECT_BY_MEMBRE = "SELECT * FROM enchere WHERE article_id = ?";
    private static final String SELECT_BY_CATEGORIE = "SELECT * FROM enchere WHERE membre_id = ?";
    private static final String SELECT_BY_ARTICLE_MEMBRE = "SELECT * FROM enchere WHERE article_id = ? AND membre_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM enchere WHERE enchere_id = ?";
    private static final String CREATE = "INSERT INTO enchere (enchere_id, enchere_montant, enchere_date, membre_id, article_id) VALUES (?, ?, ?, ?, ?)";

    @Override
    public Article findById(long id) {
        String query = "SELECT * FROM categorie WHERE article_id ="+id;
        try{
            PreparedStatement stmt= super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Article article = new Article();
                article.setId(id);
                article.setTitre(rs.getString("article_titre"));
                article.setDescription(rs.getString("article_description"));
                article.setFraisPort(rs.getFloat("article_frais_port"));
                article.setRegionLivraison(rs.getString("article_region_livraison"));
                article.setDateCloture(rs.getDate("article_date_cloture"));
                article.setModeCloture(rs.getString("article_mode_cloture"));
                article.setMontantVente(rs.getFloat("article_montant_vente"));
                article.setPrixDepart(rs.getFloat("article_prix_depart"));
                article.setPrixReserve(rs.getFloat("article_prix_reserve"));
                article.setPrixAchatImmediat(rs.getFloat("article_prix_achat_immediat"));
                CategorieDao categorieDao=new CategorieDao();
                article.setCategorie(categorieDao.find(rs.getLong("categorie_id")));
                MembreDao membreDao=new MembreDao();
                article.setVendeur(membreDao.findById(rs.getLong("membre_id")));
                OptionEnchereDao optionEnchereDao=new OptionEnchereDao();
                article.setOption(optionEnchereDao.findById(rs.getLong("option_id")));
                return article;}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Article create(Article article) {
        String query = "INSERT INTO article (article_titre,article_description,article_frais_port,article_region_livraison,article_date_cloture,article_mode_cloture,article_montant_vente,article_prix_depart,article_prix_reserve,article_prix_achat_immediat,categorie_id,membre_id,option_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt= super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,article.getTitre());
            stmt.setString(2,article.getDescription());
            stmt.setFloat(3,article.getFraisPort());
            stmt.setString(4,article.getRegionLivraison());
            stmt.setDate(5, article.getDateCloture());
            stmt.setString(6,article.getModeCloture());
            stmt.setFloat(7,article.getMontantVente());
            stmt.setFloat(8,article.getPrixDepart());
            stmt.setFloat(9,article.getPrixReserve());
            stmt.setFloat(10,article.getPrixAchatImmediat());
            stmt.setLong(11,article.getCategorie().getId());
            stmt.setLong(12,article.getVendeur().getId());
            stmt.setLong(13,article.getOption().getId());
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
    public Article update(Article obj) {
        return null;
    }

    @Override
    public void delete(Article obj) {

    }
}
