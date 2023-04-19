package edu.nd.cse.ids.project;
import edu.nd.cse.ids.project.messages.*;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;


public class Main {
	private DestInfoReader reader;

	public Main() {
		this.reader = new DestInfoReader();
	}

	public DestInfo findMatch(DestInfo userDest) {
		double maxSimilarity = -1;
		double currSimilarity;
		DestInfo bestMatch = userDest;
		for(DestInfo curr: this.reader.getDestinations()) {
			currSimilarity = compareDestinations(userDest, curr, this.reader);
			if (currSimilarity > maxSimilarity) {
				maxSimilarity = currSimilarity;
				bestMatch = curr;
			}
		}
		return bestMatch;
	}

	public static double compareDestinations(DestInfo d1, DestInfo d2, DestInfoReader reader) {	
		// right now we want highest return value, a value of 1 for coordinates and population means they are identical so try to emulate the same thing with text comparison
		return (coordComparison(d1, d2) + populationComparison(d1, d2, reader) + textComparison(d1, d2)) / 3;
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

	public static double populationComparison(DestInfo d1, DestInfo d2, DestInfoReader reader) {
		double mean = reader.getMeanPopulation();
		double stdev = reader.getStdevPopulation();
		double z1 = (d1.getPopulation() - mean) / stdev;
		double z2 = (d2.getPopulation() - mean) / stdev;
		// simplifies calculation by accounting for outlier cities with massive populations or places with incredibly low populations
		// assumes the distribution of populations is skewed so that there are a lot more places with very low populations than high ones
		if(z1 > 3) {
			z1 = 3;
		} else if (z1 < -2) {
			z1 = -2;
		}

		if(z2 > 3) {
			z2 = 3;
		} else if (z2 < -2) {
			z2 = -2;
		}
		double distance = Math.abs(z1 - z2);
		distance =  ((distance - 5) / 5) * -1;

		return distance;
	}

	public static double textComparison(DestInfo d1, DestInfo d2) {
		// TODO: do this once the text data has been saved
		return 1.0;
	}

	public List<String> describeDestination(DestInfo d1)
    {
        DocumentPlanner docplanner = new DocumentPlanner();
        docplanner.createMessage(d1, 1);
        List<Message> documentPlan = docplanner.getMessages();
		MicroPlanner microplanner = new MicroPlanner();
        List<SPhraseSpec> sentences;
		sentences = microplanner.lexicalize(documentPlan);
        Realizer realizer = new Realizer();        
        return(realizer.realize(sentences));
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
		
		DestInfo foundDestination = m1.findMatch(userSpecs);
		// TODO: output desired destination just found

		s1.close();
    }
}
