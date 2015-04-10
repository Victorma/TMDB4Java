package tbdb4java.integration.online.imp;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import tbdb4java.integration.DAOGenres;
import tbdb4java.model.Genre;
import tbdb4java.util.GenresParser;

public class DAOGenresOnline implements DAOGenres {

	private static final String API_KEY = "7d3938edb64159d9e153fe61c4438a70";

	@Override
	public List<Genre> getGenres(String language) {
		List<Genre> genres = null;
		
		HttpClient hc = new DefaultHttpClient();
		
		// Creating API query
		
		StringBuilder sb = new StringBuilder("http://api.themoviedb.org/3/genre/list?language="+language+"&api_key=");
		sb.append(API_KEY);

		// Connecting through httpget
		
		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);
			
			// Parsing results
			genres = GenresParser.parseGenres(hr.getEntity().getContent());
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return genres;
	}

	@Override
	public Genre getGenre(String nombre) {
		return null;
	}

	@Override
	public Genre getGenre(int id) {
		return null;
	}

	@Override
	public boolean addGenre(Genre genre) {
		throw new RuntimeException("Qué haces intentando añadir géneros en la base de datos online???");
	}

	@Override
	public boolean removeGenre(Genre genre) {
		throw new RuntimeException("Qué haces intentando borrar géneros en la base de datos online???");
	}

}
