package edu.nd.cse.ids.project;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {

		public static double coordCosineDistance(DestInfo d1, DestInfo d2) {
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

		public static double populationCosineDistance(DestInfo d1, DestInfo d2) {
			return 1.0;
		}

    public static void main(String[] args) throws IOException {

			// Reading from command line
			Scanner s1 = new Scanner(System.in);
			System.out.print("Enter your desired destination latitude: ");
			double latitude = s1.nextDouble();

			System.out.print("Enter your desired destination longitude: ");
			double longitude = s1.nextDouble();

			System.out.print("Enter your desired destination population: ");
			int population = s1.nextInt();
		
			// TODO: read a string describing the desired city destination	
			

			DestInfo userSpecs = new DestInfo(longitude, latitude, population, "Warm");
			DestInfo d2 = new DestInfo(123, 12, 1000000, "Cold");
			double dist = coordCosineDistance(userSpecs, d2);

			System.out.println("Normalized lat: " + userSpecs.getNormalizedLatitude() + "Normalized long" + userSpecs.getNormalizedLongitude());
			System.out.println("Normalized lat: " + d2.getNormalizedLatitude() + "Normalized long" + d2.getNormalizedLongitude());
			System.out.println("Cosine dist: " + dist);
			// TODO: find and output desired destination based on the user specifications
			

			s1.close();
    }
}
