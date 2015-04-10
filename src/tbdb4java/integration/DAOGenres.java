package tbdb4java.integration;

import java.util.List;

import tbdb4java.model.Genre;

public interface DAOGenres {

	List<Genre> getGenres(String language);
	Genre getGenre(String nombre);
	Genre getGenre(int id);
	
	boolean addGenre(Genre genre);
	boolean removeGenre(Genre genre);

}
