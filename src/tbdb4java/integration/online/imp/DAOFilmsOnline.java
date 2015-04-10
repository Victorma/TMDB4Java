package tbdb4java.integration.online.imp;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import tbdb4java.bussiness.orders.DiscoverFilmsOrder;
import tbdb4java.bussiness.orders.TopFilmsOrder;
import tbdb4java.integration.DAOFilms;
import tbdb4java.model.Film;
import tbdb4java.util.FilmsParser;

public class DAOFilmsOnline implements DAOFilms {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.ENGLISH);
	
	private static final String API_KEY = "7d3938edb64159d9e153fe61c4438a70";
	

	@Override
	public List<Film> discoverFilms(Integer genreId, Date ini, Date fin,
			Float calificacionMinima, Integer votosMinimo, String language,
			String order, int page) {

		List<Film> films = null;

		HttpClient hc = new DefaultHttpClient();

		// Creating API query

		StringBuilder sb = new StringBuilder(
				"http://api.themoviedb.org/3/discover/movie");
		sb.append("?api_key=").append(API_KEY);

		if (genreId != null)
			sb.append("&with_genres=").append(genreId);
		if (ini != null)
			sb.append("&release_date.gte=").append(dateFormatter.format(ini));
		if (fin != null)
			sb.append("&release_date.lte=").append(dateFormatter.format(fin));
		if (calificacionMinima != null)
			sb.append("&vote_average.gte=").append(calificacionMinima);
		if (votosMinimo != null)
			sb.append("&vote_count.gte=").append(votosMinimo);
		if (language != null)
			sb.append("&language=").append(language);
		if (order == null)
			order = DiscoverFilmsOrder.PopularityDesc;

		sb.append("&sort_by=").append(order);
		sb.append("&page=").append(page);

		if (page != 1)
			sb.append("&page=").append(page);

		// Connecting through httpget

		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);

			// Parsing results
			films = FilmsParser.parseList(hr.getEntity().getContent());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return films;
	}

	@Override
	public List<Film> getTopFilms(String language, String order, int page) {
		List<Film> films = null;

		HttpClient hc = new DefaultHttpClient();

		// Creating API query

		StringBuilder sb = new StringBuilder();
		if(order.equals(TopFilmsOrder.Valoration)){
			sb.append("http://api.themoviedb.org/3/movie/top_rated");
		}
		else if(order.equals(TopFilmsOrder.Popularity)){
			sb.append("http://api.themoviedb.org/3/movie/popular");
		}
		else{
			return null;
		}
		
		sb.append("?api_key=").append(API_KEY);
	
		if (language != null)
			sb.append("&language=").append(language);

		sb.append("&page=").append(page);

		if (page != 1)
			sb.append("&page=").append(page);

		// Connecting through httpget

		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);

			// Parsing results
			films = FilmsParser.parseList(hr.getEntity().getContent());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return films;
	}

	@Override
	public List<Film> searchFilms(String consulta, String order, String language, int page) {
		List<Film> films = null;

		HttpClient hc = new DefaultHttpClient();

		// Creating API query

		StringBuilder sb = new StringBuilder(
				"http://api.themoviedb.org/3/search/movie");
		sb.append("?api_key=").append(API_KEY);
		sb.append("&query=").append(consulta);
		
		if (language != null)
			sb.append("&language=").append(language);
		if (order == null)
			order = DiscoverFilmsOrder.PopularityDesc;

		sb.append("&page=").append(page);

		if (page != 1)
			sb.append("&page=").append(page);

		// Connecting through httpget

		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);

			// Parsing results
			films = FilmsParser.parseList(hr.getEntity().getContent());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return films;
	}

	@Override
	public Film getFilm(int filmId, String language) {

		Film film = null;

		HttpClient hc = new DefaultHttpClient();

		// Creating API query

		StringBuilder sb = new StringBuilder(
				"http://api.themoviedb.org/3/movie/");
		sb.append(filmId).append("?api_key=").append(API_KEY);

		if (language != null)
			sb.append("&language=").append(language);

		// Connecting through httpget

		HttpGet hg = new HttpGet(sb.toString());
		try {
			HttpResponse hr = hc.execute(hg);

			// Parsing results
			film = FilmsParser.parseFilm(hr.getEntity().getContent());
			film.setCompletada(true);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public boolean addFilm(Film film) {
		throw new RuntimeException("Cannot add online");
	}

	@Override
	public boolean updateFilm(Film film) {
		throw new RuntimeException("Cannot update online");
	}

}
