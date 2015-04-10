package tbdb4java.integration;

import java.util.Date;
import java.util.List;

import tbdb4java.model.Film;

public interface DAOFilms {


	List<Film> discoverFilms(Integer genreId, Date ini, Date fin,
			Float calificacionMinima, Integer votosMinimo, String language,
			String order, int page);
	
	List<Film> getTopFilms(String language, String order, int page);

	List<Film> searchFilms(String consulta, String order, String language,
			int page);



	Film getFilm(int filmId, String language);
	
	boolean addFilm(Film film);
	
	boolean updateFilm(Film film);

	

	


	




	





}
