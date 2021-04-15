package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.beans.Ville;

public interface VilleDAO {
	public Ville getVille(String code) throws SQLException;

	public List<Ville> getListeVille() throws SQLException;

	public void addVille(Ville v) throws SQLException;
	
	public void delVille(String code) throws SQLException;

	public void uptdateVille(Ville v) throws SQLException;

}
