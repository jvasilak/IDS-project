package edu.nd.cse.ids.project.messages;

import edu.nd.cse.ids.project.*;

public class DescribeDestinationMessage extends Message {
	private double latitude;
	private double longitude;
	private int population;
	private String description;

	public DescribeDestinationMessage() {}

	public void generate(DestInfo d1) {
		setLongitude(d1.getLongitude());
		setLatitude(d1.getLatitude());
		setPopulation(d1.getPopulation());
		setDescription(d1.getDescription());
    }

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public int getPopulation() {
		return this.population;
	}

	public String getDescription() {
		return this.description;
	}
}
