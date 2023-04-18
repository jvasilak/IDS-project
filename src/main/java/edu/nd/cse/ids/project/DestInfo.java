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
		
		// Normalize long/lat functions and values are obsolete at the moment, delete if still obsolete once project is complete
		private double normalizeLongitude(double longitude) {
				return (longitude);// / 360;
		}

		private double normalizeLatitude(double latitude) {
				return (latitude);// / 180;
		}

		private double normalizePopulation(int population) {
				// TODO: figure out more sophisticated equation for normalizing population, keep the max value to return 1 but all other values less should return a different number
				// currently returns 1 if the population is over 10million, depending on how the data counts city population, this number should be tweaked
				if (population > 10000000) {
					return 1.0;
				} else if (population > 5000000) {
					return 0.8;
				} else if (population > 2000000) {
					return 0.6;
				} else if (population > 700000) {
					return 0.4;
				} else if (population > 200000) {
					return 0.2;
				} else {
					return 0.05;
				}
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
