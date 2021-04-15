package com.blo;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.beans.Ville;
import com.dao.VilleDAO;

public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;

	@Override
	public ArrayList<Ville> getInfoVille() throws VilleException {
		ArrayList<Ville> listeVille = new ArrayList<Ville>();

		try {
			listeVille = villeDAO.getListeVille();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Nombre de ville récupérée(s) : " + listeVille.size());

		return listeVille;
	}

}
