# TMDB4Java
A simple java library for TMDB. It has options for caching.

Preview of use:
```java
import java.util.List;

import tbdb4java.bussiness.SAFilms;
import tbdb4java.bussiness.SAGenres;
import tbdb4java.bussiness.factories.SAFilmsFactory;
import tbdb4java.bussiness.factories.SAGenresFactory;
import tbdb4java.bussiness.orders.GenreFilmsOrder;
import tbdb4java.model.Film;
import tbdb4java.model.Genre;

public class Main {
	public static void main(String[] args) {
		SAFilms films = SAFilmsFactory.getInstance().getSAFilms();
		SAGenres genres = SAGenresFactory.getInstance().getSAGenres();
		
		List<Genre> lGenres = genres.getGenresList("en");
		List<Film> lFilms = films.getFilmsByGenre(lGenres.get(0),"en", GenreFilmsOrder.DateDesc, 1);
		
		for(Film f:lFilms){
			System.out.println(f.getTitle());
		}
	}
}
```

Enjoy :D
