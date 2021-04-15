package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beans.Ville;
import com.dao.DAOFactory;
import com.dao.VilleDAOImpl;

@RestController
public class VilleController {

	private VilleController() {}
	
	private static String error = "Error";
	
	@RequestMapping(value = "/getVille", method = RequestMethod.GET)
	@ResponseBody
	public static List<Ville> getListeVille() {

		DAOFactory factory = DAOFactory.getInstance();
		VilleDAOImpl villeDB = null;
		List<Ville> listeVilles = null;
		try {
			villeDB = factory.getImpl();
			listeVilles = villeDB.getListeVille();
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		}
		return listeVilles;
	}

	@RequestMapping(value = "/postVille", method = RequestMethod.POST)
	public static void postVille(@RequestBody Ville v) {
		DAOFactory factory = DAOFactory.getInstance();
		VilleDAOImpl villeDB = null;
		try {
			villeDB = factory.getImpl();
			villeDB.addVille(v);
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		}
	}

	@RequestMapping(value = "/delVille", method = RequestMethod.DELETE)
	public static void deleteVille(@PathParam("code") String code) {
		DAOFactory factory = DAOFactory.getInstance();
		VilleDAOImpl villeDB = null;
		try {
			villeDB = factory.getImpl();
			villeDB.delVille(code);
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		}
	}

	@RequestMapping(value = "/putVille", method = RequestMethod.PUT)
	public static void putVille(@PathParam("code") String code,
			@PathParam("nom") String nom, @PathParam("codePostal") String codePostal, @PathParam("libelle") String libelle,
			@PathParam("ligne") String ligne,@PathParam("latitude") String latitude ,@PathParam("longitude") String longitude) {
		
		DAOFactory factory = DAOFactory.getInstance();
		VilleDAOImpl villeDB = null;
		Ville v = new Ville(code,nom,codePostal,libelle,ligne,latitude,longitude);
		try {
			villeDB = factory.getImpl();
			villeDB.uptdateVille(v);
		} catch (SQLException e) {
			java.util.logging.Logger.getLogger("Test").log(Level.INFO, error, e);
		}
	}
}
