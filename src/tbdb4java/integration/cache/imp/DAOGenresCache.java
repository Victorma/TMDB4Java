package tbdb4java.integration.cache.imp;

import java.util.List;

import tbdb4java.integration.DAOGenres;
import tbdb4java.integration.cache.DAOCache;
import tbdb4java.model.Genre;

public class DAOGenresCache implements DAOGenres, DAOCache {

	@Override
	public String getCreateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genre> getGenres(String language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genre getGenre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genre getGenre(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addGenre(Genre genre) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeGenre(Genre genre) {
		// TODO Auto-generated method stub
		return false;
	}

}
