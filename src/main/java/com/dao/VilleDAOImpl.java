package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import com.beans.Ville;

public class VilleDAOImpl implements VilleDAO {

	private DAOFactory daoFactory;
	private Connection connexion;
	private static String error = "Error";

	VilleDAOImpl(DAOFactory daoFactory) throws SQLException {
		this.daoFactory = daoFactory;
	}

	@Override
	public Ville getVille(String code) throws SQLException {
		connexion = daoFactory.getConnection();

		Ville v = new Ville();
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try {
			preparedStatement = connexion
					.prepareStatement("SELECT * FROM ville_france WHERE `Code_commune_INSEE` = ?;");
			preparedStatement.setString(1, code);

			resultat = preparedStatement.executeQuery();

			while (resultat.next()) {
				v.setnomCommune(resultat.getString("Nom_commune"));
				v.setLigne(resultat.getString("Ligne_5"));
				v.setCodeCommune(resultat.getString("Code_commune_INSEE"));
				v.setCodePostal(resultat.getString("Code_postal"));
				v.setLibelleAcheminement(resultat.getString("Libelle_acheminement"));
				v.setLatitude(resultat.getString("Latitude"));
				v.setLongitude(resultat.getString("Longitude"));
			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		} finally {
			handleErrors2(resultat, preparedStatement, connexion);
		}

		return v;
	}

	@Override
	public ArrayList<Ville> getListeVille() throws SQLException {
		connexion = daoFactory.getConnection();

		ArrayList<Ville> listeVilles = new ArrayList<>();
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		try {
			preparedStatement = connexion.prepareStatement("SELECT * FROM ville_france ;");
			resultat = preparedStatement.executeQuery();
			while (resultat.next()) {
				Ville v = new Ville();
				v.setnomCommune(resultat.getString("Nom_commune"));
				v.setLigne(resultat.getString("Ligne_5"));
				v.setCodeCommune(resultat.getString("Code_commune_INSEE"));
				v.setCodePostal(resultat.getString("Code_postal"));
				v.setLibelleAcheminement(resultat.getString("Libelle_acheminement"));
				v.setLatitude(resultat.getString("Latitude"));
				v.setLongitude(resultat.getString("Longitude"));
				listeVilles.add(v);
			}
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		} finally {
			handleErrors2(resultat, preparedStatement, connexion);
		}

		return listeVilles;
	}

	@Override
	public void addVille(Ville v) throws SQLException {
		connexion = daoFactory.getConnection();

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO `ville_france` (`Code_commune_INSEE`, `Nom_commune`, `Code_postal`, `Libelle_acheminement`, `Ligne_5`, `Latitude`, `Longitude`)"
							+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, v.getCodeCommune());
			preparedStatement.setString(2, v.getnomCommune());
			preparedStatement.setString(3, v.getCodePostal());
			preparedStatement.setString(4, v.getLibelleAcheminement());
			preparedStatement.setString(5, v.getLigne());
			preparedStatement.setString(6, v.getLatitude());
			preparedStatement.setString(7, v.getLongitude());
			preparedStatement.execute();

		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		} finally {			
			handleErrors1(preparedStatement,connexion);
		}
	}

	public void delVille(String code) throws SQLException {
		try {
			connexion = daoFactory.getConnection();
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		}

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connexion
					.prepareStatement("DELETE FROM `ville_france` WHERE `ville_france`.`Code_commune_INSEE` = ?");
			preparedStatement.setString(1, code);
			preparedStatement.execute();

		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		} finally {			
			handleErrors1(preparedStatement,connexion);
		}
	}

	public void uptdateVille(Ville v) throws SQLException {
		connexion = daoFactory.getConnection();

		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connexion.prepareStatement(
					"UPDATE `ville_france` SET `Nom_commune` = ?, `Code_postal` = ?, `Libelle_acheminement` = ?, `Ligne_5` = ?, `Latitude` = ?, `Longitude` = ?"
							+ " WHERE `ville_france`.`Code_commune_INSEE` = ?");
			preparedStatement.setString(1, v.getnomCommune());
			preparedStatement.setString(2, v.getCodePostal());
			preparedStatement.setString(3, v.getLibelleAcheminement());
			preparedStatement.setString(4, v.getLigne());
			preparedStatement.setString(5, v.getLatitude());
			preparedStatement.setString(6, v.getLongitude());
			preparedStatement.setString(7, v.getCodeCommune());
			preparedStatement.execute();

		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		} finally {
			handleErrors1(preparedStatement,connexion);
		}

	}

	public static void handleErrors1(PreparedStatement preparedStatement, Connection connexion) throws SQLException {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
			}
		}
	}

	public static void handleErrors2(ResultSet resultat, PreparedStatement preparedStatement, Connection connexion)
			throws SQLException {
		try {
			if (resultat != null) {
				resultat.close();
			}
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				try {
					connexion.close();
				} catch (SQLException e) {
					java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
				}
			}
		}
	}

}
