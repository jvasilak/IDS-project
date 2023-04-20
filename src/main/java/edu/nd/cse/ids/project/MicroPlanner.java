package edu.nd.cse.ids.project;

import edu.nd.cse.ids.project.messages.*;

import java.util.List;
import java.util.LinkedList;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class MicroPlanner {
    private Lexicon lexicon;
    private NLGFactory nlgFactory;
    
    public MicroPlanner() {
        this.lexicon = Lexicon.getDefaultLexicon();
        this.nlgFactory = new NLGFactory(this.lexicon);
    }

    public List<SPhraseSpec> lexicalize(List<Message> documentPlan)
    {
        List<SPhraseSpec> list = new LinkedList<>();
        while(!documentPlan.isEmpty()) {
            Message m = documentPlan.remove(0);
            if (m instanceof DescribeDestinationMessage) {
                list.add(handleDescribeDestinationMessage((DescribeDestinationMessage) m));
                list.add(handleCoordinateMessage((CoordinateMessage) m));
                list.add(handlePopulationMessage((PopulationMessage) m));
            }
        }
        return list;
    }
    /*
    here is a sample output:
    Based on the information provided, LOCATIONNAME sounds like a good destination.
    LOCATIONNAME is located at LATITUDE, LONGITUDE.
    It has a population of POPULATION.
    Here is a short description of LOCATIONNAME:
    DESCRIPTION
    */

    public SPhraseSpec handleDescribeDestinationMessage(DescribeDestinationMessage message) {
        SPhraseSpec s1 = this.nlgFactory.createClause();
        WordElement we_place = new WordElement(message.getName(), LexicalCategory.NOUN);
        WordElement we_sounds = new WordElement("sound", LexicalCategory.VERB);
        WordElement we_good = new WordElement("good", LexicalCategory.ADJECTIVE);
        WordElement we_destination = new WordElement("destination", LexicalCategory.NOUN);
        s1.setSubject(we_place);
        s1.setVerb(we_sounds);
        NPPhraseSpec destinationPhrase = this.nlgFactory.createNounPhrase(we_destination);
        destinationPhrase.setSpecifier("a");
        destinationPhrase.addPreModifier(we_good);
        s1.setObject(destinationPhrase);
        return s1;
    }

    public SPhraseSpec handleCoordinateMessage(CoordinateMessage message) {
        SPhraseSpec s1 = this.nlgFactory.createClause();
        String longString = Double.toString(message.getLongitude());
        String latString = Double.toString(message.getLatitude());
        WordElement we_place = new WordElement(message.getName(), LexicalCategory.NOUN);
        WordElement we_verb = new WordElement("be", LexicalCategory.VERB);
        VPPhraseSpec verbPhrase = this.nlgFactory.createVerbPhrase(we_verb);
        PPPhraseSpec pp = nlgFactory.createPrepositionPhrase();
        pp.setPreposition("at");
        String coordString = "(" + latString + ", " + longString + ")";
        pp.addComplement(coordString);
        s1.setSubject(we_place);
        s1.setVerb(we_verb);
        s1.addComplement(pp);
        return s1;
    }

    public SPhraseSpec handlePopulationMessage(PopulationMessage message) {
        SPhraseSpec s1 = this.nlgFactory.createClause();
        String populationString = Integer.toString(message.getPopulation());
        WordElement we_it = new WordElement("it", LexicalCategory.NOUN);
        WordElement we_verb = new WordElement("has", LexicalCategory.VERB);
        WordElement we_popword = new WordElement("population", LexicalCategory.NOUN);
        WordElement we_popval = new WordElement(populationString, LexicalCategory.NOUN);
        NPPhraseSpec populationPhrase = this.nlgFactory.createNounPhrase(we_popword);
        PPPhraseSpec pp = nlgFactory.createPrepositionPhrase();
        pp.setPreposition("of");
        pp.addComplement(populationString);
        populationPhrase.setSpecifier("a");
        s1.setSubject(we_it);
        s1.setVerb(we_verb);
        s1.setObject(populationPhrase);
        s1.addComplement(pp);
        return s1;
    }
}
