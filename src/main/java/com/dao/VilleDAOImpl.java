package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.DAOFactory;

public class VilleDAOImpl implements VilleDAO {

	private DAOFactory daoFactory;
	private Connection connexion;

	VilleDAOImpl(DAOFactory daoFactory) throws SQLException {
		this.daoFactory = daoFactory;
		connexion = daoFactory.getConnection();
	}

	@Override
	public List<String> getListeVille() throws SQLException {

		ArrayList<String> listeVilles = new ArrayList<String>();
		PreparedStatement preparedStatementPersonne = null;
		ResultSet resultat = null;
		preparedStatementPersonne = connexion.prepareStatement("SELECT * FROM ville_france ;");
		resultat = preparedStatementPersonne.executeQuery();

		while (resultat.next()) {
			listeVilles.add(resultat.getString("Nom_commune"));
		}

		return listeVilles;
	}

}
