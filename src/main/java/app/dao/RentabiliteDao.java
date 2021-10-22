package app.dao;

import app.model.Rentabilite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class RentabiliteDao extends DAO<Rentabilite>{

    private static final String SELECT_ALL = "SELECT * FROM rentabilite";
    private static final String SELECT_BY_ID = "SELECT * FROM rentabilite WHERE rentabilite_id = ?";
    private static final String CREATE = "INSERT INTO rentabilite (rentabilite_RepaPrev," +
            " rentabilite_NbMoyInsertion," +
            " rentabilite_NbMoyVisite," +
            " rentabilite_DureeMoyEnchere," +
            " rentabilite_PrixVenteMoy," +
            " rentabilite_TauxVenteR," +
            " rentabilite_ChargePrev," +
            " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String REPARTION_PREVISIONNELLE = "";
    private static final String NOMBRE_MOYEN_INSERTION = "SELECT COUNT(*) FROM enchere";
    private static final String NOMBRE_MOYEN_VISITE = "SELECT COUNT(*) FROM visite WHERE GROUP BY DATEFORMAT(visite_date_heure,'dd')";
    private static final String DUREE_MOYENNE_ENCHERE = "SELECT heureEnchere, dateEnchere FROM enchere";
    private static final String PPRIX_VENTE_MOYEN = "SELECT montantVenteArticle FROM Article";
    private static final String TAUX_VENTE = "SELECT COUNT(*) FROM Article WHERE modeClotureArticle = ?";
    private static final String CHARGE_PREVISIONNELLE = "";

    private ArrayList<Rentabilite> getResulSet(ResultSet resultSet) throws SQLException {
        ArrayList<Rentabilite> rentabilites = new ArrayList<>();
        Rentabilite rentabilite;
        while (resultSet.next()) {
            rentabilite = new Rentabilite();
            rentabilite.setId(resultSet.getLong("rentabilite_id"));
            rentabilite.setRepaPrev(resultSet.getFloat("rentabilite_RepaPrev"));
            rentabilite.setNbMoyInsertion(resultSet.getInt("rentabilite_NbMoyInsertion"));
            rentabilite.setNbMoyVisite(resultSet.getInt("rentabilite_NbMoyVisite"));
            rentabilite.setDureeMoyEnchere(resultSet.getInt("rentabilite_DureeMoyEnchere"));
            rentabilite.setPrixVenteMoy(resultSet.getInt("rentabilite_PrixVenteMoy"));
            rentabilite.setTauxVenteR(resultSet.getFloat("rentabilite_TauxVenteR"));
            rentabilite.setChargePrev(resultSet.getInt("rentabilite_ChargePrev"));
            rentabilites.add(rentabilite);
        }
        return rentabilites;
    }

    public ArrayList<Rentabilite> findAll() {
        ArrayList<Rentabilite> rentabilites = new ArrayList<>();
        try {
            Statement statement = super.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            rentabilites = getResulSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentabilites;
    }

    @Override
    public Rentabilite findById(long id) {
        Rentabilite rentabilites = null;
        try{
            PreparedStatement preparedStatement= super.connection.prepareStatement(SELECT_BY_ID, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            rentabilites = getResulSet(resultSet).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rentabilites;
    }

    @Override
    public Rentabilite create(Rentabilite rentabilite) {
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1, rentabilite.getRepaPrev());
            preparedStatement.setInt(2, rentabilite.getNbMoyInsertion());
            preparedStatement.setInt(3, rentabilite.getNbMoyVisite());
            preparedStatement.setInt(4, rentabilite.getDureeMoyEnchere());
            preparedStatement.setInt(5, rentabilite.getPrixVenteMoy());
            preparedStatement.setFloat(6, rentabilite.getTauxVenteR());
            preparedStatement.setInt(7, rentabilite.getChargePrev());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                rentabilite.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentabilite;
    }

    @Override
    public Rentabilite update(Rentabilite rentabilite) {
        String query = "UPDATE Rentabilite SET rentabilite_id=?, repartitionPrevisionnelle=?, nombreMoyenInsertion=?, nombreMoyenVisite=?, dureeMoyenneEnchere=?, prixVenteMoyen=?, tauxVente=?, chargePrevisionnelle=? WHERE rentabilite_id=?";
        try {
            PreparedStatement preparedStatement = super.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1,rentabilite.getId());
            preparedStatement.setFloat(2,rentabilite.getRepaPrev());
            preparedStatement.setInt(3,rentabilite.getNbMoyInsertion());
            preparedStatement.setInt(4,rentabilite.getNbMoyVisite());
            preparedStatement.setInt(5,rentabilite.getDureeMoyEnchere());
            preparedStatement.setInt(6,rentabilite.getPrixVenteMoy());
            preparedStatement.setFloat(7,rentabilite.getTauxVenteR());
            preparedStatement.setInt(8,rentabilite.getChargePrev());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                rentabilite.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentabilite;
    }

    @Override
    public void delete(Rentabilite rentabilite) {
        String query = "DELETE FROM Rentabilite WHERE rentabilite_id="+rentabilite.getId();
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
