package edu.nd.cse.ids.project;

import com.opencsv.bean.*;

import java.util.List;
import java.util.HashMap;
import java.io.FileReader;

// TODO: this will need to be fixed once the data is placed in a file we can read from
public class DestInfoReader
{
    private List<DestInfo> destinations;

    public DestInfoReader() {
        this.destinations = null;
    }

    public void readDestInfoFile(String filename) {
        try {
            this.destinations = new CsvToBeanBuilder(new FileReader(filename))
                                .withType(DestInfo.class).build().parse();
        } catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    
    public List<DestInfo> getDestinations() {
        return this.destinations;
    }

    public double getMeanPopulation() {
        double sum = 0;
        for(DestInfo curr: this.destinations) {
            sum += curr.getPopulation();
        }
        return sum / this.destinations.size();
    }

    public double getStdevPopulation() {
        double mean = getMeanPopulation();

        double sum = 0;
        for(DestInfo curr : this.destinations) {
            sum += Math.pow(curr.getPopulation() - mean, 2);
        }

        // Calculate standard deviation
        return Math.sqrt(sum / (this.destinations.size() - 1));
    }
}