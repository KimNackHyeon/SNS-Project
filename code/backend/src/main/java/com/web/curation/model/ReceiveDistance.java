package com.web.curation.model;

public class ReceiveDistance {
	private Double distance;

	public ReceiveDistance(Double distance) {
		super();
		this.distance = distance;
	}

	public ReceiveDistance() {
		super();
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "ReceiveDistance [distance=" + distance + "]";
	}
	
}
