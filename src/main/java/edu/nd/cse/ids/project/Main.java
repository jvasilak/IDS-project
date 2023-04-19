package edu.nd.cse.ids.project;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
		private DestInfoReader reader;

		public Main() {
			this.reader = new DestInfoReader();
		}

		public DestInfo findMatch(DestInfo userDest) {
			double minDistance = Double.MAX_VALUE; // set to a value higher than the max distance
			double currDistance;
			DestInfo bestMatch = userDest;
			for(DestInfo curr: this.reader.getDestinations()) {
            	currDistance = compareDestinations(userDest, curr);
				if (currDistance < minDistance) {
					minDistance = currDistance;
					bestMatch = curr;
				}
        	}
			return bestMatch;
		}

		public static double compareDestinations(DestInfo d1, DestInfo d2) {	
			return (coordComparison(d1, d2) + populationComparison(d1, d2) + textComparison(d1, d2)) / 3;
		}

		public static double coordComparison(DestInfo d1, DestInfo d2) {
			List<Double> c1 = new ArrayList();
			c1.add(d1.getLongitude());
			c1.add(d1.getLatitude());

			List<Double> c2 = new ArrayList();
			c2.add(d2.getLongitude());
			c2.add(d2.getLatitude());
			// Calculate dot product
			double dotProduct = 0;
			assert c1.size() == c2.size();
			for (int i = 0; i < c1.size(); i++) {
				dotProduct += c1.get(i) * c2.get(i);
			}

			// Calculate magnitudes of c1 and c2
			double c1Mag = Math.sqrt(Math.pow(c1.get(0).doubleValue(), 2) + Math.pow(c1.get(1).doubleValue(), 2));
			double c2Mag = Math.sqrt(Math.pow(c2.get(0).doubleValue(), 2) + Math.pow(c2.get(1).doubleValue(), 2));

			return ((dotProduct / (c1Mag * c2Mag)) + 1) / 2;
		}

		public static double populationComparison(DestInfo d1, DestInfo d2) {
			
			return 1.0;
		}

		public static double textComparison(DestInfo d1, DestInfo d2) {
			// do this once the text data has been saved
			return 1.0;
		}

    public static void main(String[] args) throws IOException {

			// Reading from command line
			Main m1 = new Main();
			Scanner s1 = new Scanner(System.in);
			double latitude = 0;
			while (true) {
				System.out.print("Enter your desired destination latitude: ");
				latitude = s1.nextDouble();
				if(latitude <= 90 && latitude >= -90) {
					break;
				} else {
					System.out.println("Invalid input! Please make sure to enter a valid value.");
				}
			}
			double longitude = 0;
			while (true) {
				System.out.print("Enter your desired destination longitude: ");
				longitude = s1.nextDouble();
				if(longitude <= 180 && longitude >= -180) {
					break;
				} else {
					System.out.println("Invalid input! Please make sure to enter a valid value.");
				}
			}
			int population = 0;
			while (true) {
				System.out.print("Enter your desired destination population: ");
				population = s1.nextInt();
				if(population > 0) {
					break;
				} else {
					System.out.println("Invalid input! Please make sure to enter a valid value.");
				}
			}
		
			System.out.println("Describe your desired destination: ");
			String description = s1.nextLine();    

			DestInfo userSpecs = new DestInfo(longitude, latitude, population, description);
			
			// TODO: find and output desired destination based on the user specifications
			DestInfo foundDestination = m1.findMatch(userSpecs);

			s1.close();
    }
}
