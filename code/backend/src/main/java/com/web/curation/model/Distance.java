package com.web.curation.model;

public class Distance {
	private String Ga;
	private String Ha;
	public Distance(String ga, String ha) {
		super();
		Ga = ga;
		Ha = ha;
	}
	public Distance() {
		super();
	}
	@Override
	public String toString() {
		return "Distance [Ga=" + Ga + ", Ha=" + Ha + "]";
	}
	public String getGa() {
		return Ga;
	}
	public void setGa(String ga) {
		Ga = ga;
	}
	public String getHa() {
		return Ha;
	}
	public void setHa(String ha) {
		Ha = ha;
	}
	
}
