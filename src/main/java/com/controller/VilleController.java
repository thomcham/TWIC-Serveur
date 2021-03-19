package com.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.DAOFactory;
import com.dao.VilleDAOImpl;


@RestController
public class VilleController {

	@RequestMapping(value = "/ville", method = RequestMethod.GET)
	@ResponseBody
	public List<String> appelGet() {
		DAOFactory factory = DAOFactory.getInstance();
		VilleDAOImpl villeDB = null;

		List<String> listeVilles =null;
		
		try {
			villeDB = factory.getImpl();
			listeVilles = villeDB.getListeVille();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listeVilles;
	}
}
