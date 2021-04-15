package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.beans.Ville;
import com.dao.DAOFactory;

public class VilleDAOImpl implements VilleDAO {

	private DAOFactory daoFactory;
	private Connection connexion;

	VilleDAOImpl(DAOFactory daoFactory) throws SQLException {
		this.daoFactory = daoFactory;
		connexion = daoFactory.getConnection();
	}

	@Override
	public Ville getVille(String code) throws SQLException {
		Ville v = new Ville();
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		preparedStatement = connexion.prepareStatement("SELECT * FROM ville_france WHERE `Code_commune_INSEE` = ?;");
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
		return v;
	}
	
	
	@Override
	public ArrayList<Ville> getListeVille() throws SQLException {

		ArrayList<Ville> listeVilles = new ArrayList<Ville>();
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
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

		return listeVilles;
	}

	@Override
	public void addVille(Ville v) throws SQLException {
		PreparedStatement preparedStatement = null;
		preparedStatement = connexion.prepareStatement("INSERT INTO `ville_france` (`Code_commune_INSEE`, `Nom_commune`, `Code_postal`, `Libelle_acheminement`, `Ligne_5`, `Latitude`, `Longitude`)"
				+ "												VALUES (?, ?, ?, ?, ?, ?, ?)");

		preparedStatement.setString(1, v.getCodeCommune());
		preparedStatement.setString(2, v.getnomCommune());
		preparedStatement.setString(3, v.getCodePostal());
		preparedStatement.setString(4, v.getLibelleAcheminement());
		preparedStatement.setString(5, v.getLigne());
		preparedStatement.setString(6, v.getLatitude());
		preparedStatement.setString(7, v.getLongitude());
		
		preparedStatement.execute();
	}

	
	public void delVille(String code) throws SQLException {
		PreparedStatement preparedStatement = null;
		preparedStatement = connexion.prepareStatement("DELETE FROM `ville_france` WHERE `ville_france`.`Code_commune_INSEE` = ?");
		preparedStatement.setString(1, code);

		preparedStatement.execute();
	}
	
	public void uptdateVille(Ville v) throws SQLException {
		PreparedStatement preparedStatement = null;
		preparedStatement = connexion.prepareStatement("UPDATE `ville_france` SET `Nom_commune` = ?, `Code_postal` = ?, `Libelle_acheminement` = ?, `Ligne_5` = ?, `Latitude` = ?, `Longitude` = ?"
				+ "					 WHERE `ville_france`.`Code_commune_INSEE` = ?");
		preparedStatement.setString(1, v.getnomCommune());
		preparedStatement.setString(2, v.getCodePostal());
		preparedStatement.setString(3, v.getLibelleAcheminement());
		preparedStatement.setString(4, v.getLigne());
		preparedStatement.setString(5, v.getLatitude());
		preparedStatement.setString(6, v.getLongitude());
		preparedStatement.setString(7, v.getCodeCommune());

		preparedStatement.execute();
	}


	
	

}
