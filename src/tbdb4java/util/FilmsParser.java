package tbdb4java.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import tbdb4java.model.Film;
import tbdb4java.model.Genre;

public class FilmsParser {

	private static SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.ENGLISH);

	public static List<Film> parseList(InputStream in) {

		List<Film> filmList = new ArrayList<Film>();
		try {

			JSONParser parser = new JSONParser();

			JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(in));

			JSONArray json = (JSONArray) obj.get("results");

			for (int i = 0; i < json.size(); i++) {

				JSONObject jsonObj = (JSONObject) json.get(i);
				Film film = new Film();

				film.setId(Integer.parseInt(jsonObj.get("id").toString()));
				film.setTitle((String) jsonObj.get("title"));
				film.setOriginalTitle((String) jsonObj.get("original_title"));
				film.setDate(dateFormatter.parse((String) jsonObj.get("release_date")));
				film.setValoration(((Double) jsonObj.get("vote_average")).floatValue());
				film.setPopularity(((Double) jsonObj.get("popularity")).floatValue());
				film.setVoteCount(Integer.parseInt(jsonObj.get("vote_count").toString()));
				film.setCaratula((String) jsonObj.get("poster_path"));

				filmList.add(film);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return filmList;
	}

	public static Film parseFilm(InputStream content) {
		
		Film film = null;
		try {

			JSONParser parser = new JSONParser();

			JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(content));


			film = new Film();

			// 1
			film.setId(Integer.parseInt(obj.get("id").toString()));
			film.setTitle((String) obj.get("title"));
			film.setOriginalTitle((String) obj.get("original_title"));
			film.setDate(dateFormatter.parse((String) obj.get("release_date")));
			film.setValoration(((Double) obj.get("vote_average")).floatValue());
			film.setPopularity(((Double) obj.get("popularity")).floatValue());
			if(obj.get("vote_count")!=null)
				film.setVoteCount(Integer.parseInt(obj.get("vote_count").toString()));
			film.setCaratula((String) obj.get("poster_path"));
			
			//2
			if(obj.get("runtime")!=null)
				film.setDuration(Integer.parseInt(obj.get("runtime").toString()));
			if(obj.get("overview")!=null)
				film.setOverview((String) obj.get("overview"));
			else
				film.setOverview("No se ha encontrado información");
			
			//genres
			List<Genre> genreList = new ArrayList<Genre>();
			JSONArray json = (JSONArray) obj.get("genres");
			
			for (int i = 0; i < json.size(); i++) {
				JSONObject jsonObj = (JSONObject) json.get(i);
				Genre genre = new Genre();

				genre.setId(Integer.parseInt(jsonObj.get("id").toString()));
				genre.setName((String) jsonObj.get("name"));
				
				genreList.add(genre);
			}
			film.setGenres(genreList);
			
			//production_countries
			List <String> producerCountries = new ArrayList<String>();
			json = (JSONArray) obj.get("production_countries");
			
			for(Object o : json)
				producerCountries.add((String) ((JSONObject) o).get("name"));
			
			for (int i = 0; i < json.size(); i++) {
				
			}
			film.setProducerCountries(producerCountries);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return film;
	}

}
