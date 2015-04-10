package tbdb4java.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import tbdb4java.model.Genre;

public class GenresParser {

	public static List<Genre> parseGenres(InputStream in) {

		List<Genre> genreList = new ArrayList<Genre>();
		try {

			JSONParser parser = new JSONParser();

			JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(in));

			JSONArray json = (JSONArray) obj.get("genres");

			for (int i = 0; i < json.size(); i++) {

				JSONObject jsonObj = (JSONObject) json.get(i);
				Genre genre = new Genre();

				genre.setId(Integer.parseInt(jsonObj.get("id").toString()));
				genre.setName((String) jsonObj.get("name"));

				genreList.add(genre);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return genreList;
	}

}
