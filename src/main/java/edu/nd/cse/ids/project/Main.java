package edu.nd.cse.ids.project;

import org.apache.commons.cli.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

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

			// TODO: find and output desired destination based on the user specifications
			
    }
}
