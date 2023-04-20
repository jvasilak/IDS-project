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
		private String name;
		// add vectorized description parameter

		public DestInfo(double longitude, double latitude, int population, String description, String name) {
				this.description = description;
				this.latitude = latitude;
				this.longitude = longitude;
				this.population = population;
				this.normalizedLongitude = this.normalizeLongitude(longitude);
				this.normalizedLatitude = this.normalizeLatitude(latitude);
				this.normalizedPopulation = this.normalizePopulation(population);
				this.name = name;
		}
		
		// Normalize long/lat functions and values are obsolete at the moment, delete if still obsolete once project is complete
		private double normalizeLongitude(double longitude) {
				return (longitude);// / 360;
		}

		private double normalizeLatitude(double latitude) {
				return (latitude);// / 180;
		}

		// also obsolete at the moment
		private double normalizePopulation(int population) {
				// do zscore of the population value in the distribution of the dataset, with something that limits the zscore to a certain number, then divide by that number
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

		public String getDescription() {
			return this.description;
		}

		public String getName() {
			return this.name;
		}

		/*
		public ? getVectorizedDescription() {
			return this.vectorizedDescription;
		}
		 */
}
