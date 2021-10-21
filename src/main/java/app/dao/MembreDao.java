package app.dao;

import app.model.Compte;
import app.model.Membre;
import app.model.OptionEnchere;
import app.model.SignalerArticle;

import java.sql.*;
import java.util.ArrayList;

public class MembreDao extends DAO<Membre>{

    private static final String SELECT_ALL = "SELECT * FROM membre";
    private static final String SELECT_BY_ID = "SELECT * FROM membre WHERE membre_id = ?";
    private static final String SELECT_BY_COMPTE = "SELECT * FROM membre WHERE compte_id = ?";
    private static final String UPDATE = "";
    private static final String CREATE = "";
    private static final String DELETE = "";


    public ArrayList<Membre> findAll() {
        ArrayList<Membre> membres = new ArrayList<>();
        Membre membre;
        try {
            Statement statement = super.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                CompteDao compteDao = new CompteDao();
                membre = new Membre();
                membre.setId(resultSet.getLong("membre_id"));
                membre.setNom(resultSet.getString("membre_nom"));
                membre.setPrenom(resultSet.getString("membre_prenom"));
                membre.setDateNaissance(resultSet.getDate("membre_date_naissance"));
                membre.setAdressePostale(resultSet.getString("membre_adresse_postale"));
                membre.setCodePostal(resultSet.getString("membre_code_postal"));
                membre.setVille(resultSet.getString("membre_ville"));
                membre.setPays(resultSet.getString("membre_pays"));
                membre.setCompte(compteDao.findById(resultSet.getLong("compte_id")));
                membres.add(membre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    @Override
    public Membre findById(long id) {
        Membre membre = null;
        CompteDao compteDao = new CompteDao();
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                membre = new Membre();
                membre.setId(resultSet.getLong("membre_id"));
                membre.setNom(resultSet.getString("membre_nom"));
                membre.setPrenom(resultSet.getString("membre_prenom"));
                membre.setDateNaissance(resultSet.getDate("membre_date_naissance"));
                membre.setAdressePostale(resultSet.getString("membre_adresse_postale"));
                membre.setCodePostal(resultSet.getString("membre_code_postal"));
                membre.setVille(resultSet.getString("membre_ville"));
                membre.setPays(resultSet.getString("membre_pays"));
                membre.setCompte(compteDao.findById(resultSet.getLong("compte_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  membre;
    }

    public Membre findByCompte(Compte compte){
        Membre membre = null;
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(SELECT_BY_COMPTE);
            preparedStatement.setLong(1, compte.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                membre = new Membre();
                membre.setId(resultSet.getLong("membre_id"));
                membre.setNom(resultSet.getString("membre_nom"));
                membre.setPrenom(resultSet.getString("membre_prenom"));
                membre.setDateNaissance(Date.valueOf(String.valueOf(resultSet.getDate("membre_date_naissance"))));
                membre.setCodePostal(resultSet.getString("membre_code_postal"));
                membre.setAdressePostale(resultSet.getString("membre_adresse_postale"));
                membre.setVille(resultSet.getString("membre_ville"));
                membre.setPays(resultSet.getString("membre_pays"));
                membre.setMembrePlus(resultSet.getInt("membre_plus") == 1);
                membre.setCompte(compte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membre;
    }

    @Override
    public Membre create(Membre membre) {
        String query = "INSERT INTO Membre (membre_nom, membre_prenom, membre_date_naissance, membre_code_postal, membre_adresse_postale, membre_ville, membre_pays, compte_id) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setDate(3, membre.getDateNaissance());
            preparedStatement.setString(4, membre.getCodePostal());
            preparedStatement.setString(5, membre.getAdressePostale());
            preparedStatement.setString(6, membre.getVille());
            preparedStatement.setString(7, membre.getPays());
            preparedStatement.setLong(8, membre.getCompte().getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                membre.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membre;
    }

    @Override
    public Membre update(Membre membre) {
        String query = "UPDATE Membre SET membre_nom=?, membre_prenom=?, membre_date_naissance=?, membre_code_postal=?, membre_adresse_postale=?, membre_ville=?, membre_pays=?, compte_id=? WHERE membre_id=?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setDate(3, membre.getDateNaissance());
            preparedStatement.setString(4, membre.getCodePostal());
            preparedStatement.setString(5, membre.getAdressePostale());
            preparedStatement.setString(6, membre.getVille());
            preparedStatement.setString(7, membre.getPays());
            preparedStatement.setLong(8, membre.getCompte().getId());
            preparedStatement.setLong(9,membre.getId());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                membre.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membre;
    }

    @Override
    public void delete(Membre membre) {
        String query = "DELETE FROM Membre WHERE membre_id="+membre.getId();
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getResultSet();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}