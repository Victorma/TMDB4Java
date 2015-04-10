package tbdb4java.bussiness;

import java.util.Date;
import java.util.List;

import tbdb4java.model.Film;
import tbdb4java.model.Genre;

public interface SAFilms {

	List<Film> getFilmsByGenre(int genreId, String order, String language, int page);

	List<Film> getFilmsByGenre(Genre genre, String order, String language, int page);

	List<Film> discoverFilms(Genre genre, Date ini, Date fin,
			Float calificacionMinima, Integer votosMinimo, String order,
			String language, int page);

	List<Film> searchFilms(String query, String order, String language, int page);

	List<Film> topFilms(String order, String language, int page); 
	
	List<Film> lastFilms(String language, int page); 

	Film getFilm(int filmId, String language);

	Film getFilm(Film film, String language);

	List<Film> discoverFilms(Integer genreId, Date ini, Date fin,
			Float calificacionMinima, Integer votosMinimo,
			String order, String language, int page);

	

}
