package tbdb4java.model;

import java.util.Date;
import java.util.List;

public class Film {

	private int id; // 1
	private String title; // 1
	private String originalTitle; // 1
	private Date date; // 1
	private int duration; // en mins //2
	private List<String> producerCountries; // 2
	private List<Genre> genres; // varias categorias!! //2
	private String overview; // 2
	private float valoration; // del 1 al 10. //1
	private int voteCount; // del 1 al 10. //1
	private float popularity; // 1
	private String caratula; // 1
	private boolean completada;

	public Film() {
		super();
		this.id = -1;
		this.title = "";
		this.originalTitle = "";
		this.date = null;
		this.duration = 0;
		this.producerCountries = null;
		this.genres = null;
		this.overview = "";
		this.valoration = 0;
		this.voteCount = 0;
		this.popularity = 0;
		this.caratula = null;
		this.completada = false;
	}

	public Film(int id, String title, String originalTitle, Date date,
			int duration, List<String> producerCountries, List<Genre> genres,
			String overview, float valoration, int voteCount, float popularity,
			String caratula, boolean completada) {
		super();
		this.id = id;
		this.title = title;
		this.originalTitle = originalTitle;
		this.date = date;
		this.duration = duration;
		this.producerCountries = producerCountries;
		this.genres = genres;
		this.overview = overview;
		this.valoration = valoration;
		this.voteCount = voteCount;
		this.popularity = popularity;
		this.caratula = caratula;
		this.completada = completada;
	}

	public boolean isCompletada() {
		return completada;
	}

	public void setCompletada(boolean completada) {
		this.completada = completada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<String> getProducerCountries() {
		return producerCountries;
	}

	public void setProducerCountries(List<String> producerCountries) {
		this.producerCountries = producerCountries;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public float getValoration() {
		return valoration;
	}

	public void setValoration(float valoration) {
		this.valoration = valoration;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public int getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public float getPopularity() {
		return popularity;
	}

	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}

}
