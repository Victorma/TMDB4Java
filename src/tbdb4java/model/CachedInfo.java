package tbdb4java.model;

import java.util.Date;

public class CachedInfo {

	private String consult;
	private Date date;
	private int lastPage;
	
	
	
	public CachedInfo() {
		super();
		this.consult = null;
		this.date = null;
		this.lastPage = 0;
	}



	public CachedInfo(String consult, Date date, int lastPage) {
		super();
		this.consult = consult;
		this.date = date;
		this.lastPage = lastPage;
	}



	public String getConsult() {
		return consult;
	}



	public void setConsult(String consult) {
		this.consult = consult;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public int getLastPage() {
		return lastPage;
	}



	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	
	
}
