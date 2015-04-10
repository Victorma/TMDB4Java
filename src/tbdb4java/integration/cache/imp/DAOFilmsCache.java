package tbdb4java.integration.cache.imp;

import java.util.Date;
import java.util.List;

import tbdb4java.integration.DAOFilms;
import tbdb4java.integration.cache.DAOCache;
import tbdb4java.model.Film;

public class DAOFilmsCache implements DAOCache, DAOFilms {

	@Override
	public List<Film> discoverFilms(Integer genreId, Date ini, Date fin,
			Float calificacionMinima, Integer votosMinimo, String language,
			String order, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Film> getTopFilms(String language, String order, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Film> searchFilms(String consulta, String order,
			String language, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film getFilm(int filmId, String language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFilm(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateFilm(Film film) {
		// TODO Auto-generated method stub
		return false;
	}

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

}
