package edu.nd.cse.ids.project;

import org.apache.commons.cli.*;

public class DestInfo {

		private double normalizedLongitude;
		private double normalizedLatitude;
		private double normalizedPopulation;
		private double longitude;
		private double latitude;
		private int population;
		private String description;
		// add vectorized description parameter

		public DestInfo(double longitude, double latitude, int population, String description) {
				this.description = description;
				this.latitude = latitude;
				this.longitude = longitude;
				this.population = population;
				this.normalizedLongitude = this.normalizeLongitude(longitude);
				this.normalizedLatitude = this.normalizeLatitude(latitude);
				this.normalizedPopulation = this.normalizePopulation(population);
		}
		
		private double normalizeLongitude(double longitude) {
				return (longitude + 180) / 360;
		}

		private double normalizeLatitude(double latitude) {
				return (latitude + 90) / 180; 
		}

		private double normalizePopulation(int population) {
				// TODO: figure out equation for normalizing population
				return (double) population;
		}

		public double getNormalizedLongitude() {
				return this.normalizedLongitude;	
		}

		public double getNormalizedLatitude() {
				return this.normalizedLatitude;
		}

		public double getNormalizedPopulation() {
				return this.normalizedPopulation;
		}

		public double getLongitude() {
			return this.longitude;
		}

		public double getLatitude() {
			return this.latitude;
		}

		public int getPopulation() {
			return this.population;
		}
}
