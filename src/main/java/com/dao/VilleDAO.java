package com.dao;

import java.sql.SQLException;
import java.util.List;

public interface VilleDAO {
	
	public List<String> getListeVille() throws SQLException;

}
