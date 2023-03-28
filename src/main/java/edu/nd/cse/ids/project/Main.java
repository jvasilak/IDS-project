package edu.nd.cse.ids.project;

import org.apache.commons.cli.*;

public class Main {


    public static void main(String[] args) {

			// Reading from command line
			Options options = new Options();
			Option population = new Option("p", "population", true, "desired population of the user");
			population.setRequired(true);
			options.addOption(population);
    }
}
