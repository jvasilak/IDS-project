package edu.nd.cse.ids.project;

import edu.nd.cse.ids.project.messages.*;

import java.util.List;
import java.util.LinkedList;

import simplenlg.framework.*;
import simplenlg.lexicon.*;
import simplenlg.realiser.english.*;
import simplenlg.phrasespec.*;
import simplenlg.features.*;

public class Realizer {
    private Lexicon lexicon;
    private Realiser realizer;
    
    public Realizer()
    {
        lexicon = Lexicon.getDefaultLexicon();
        realizer = new Realiser(lexicon);
    }

    public List<String> realize(List<SPhraseSpec> sentences) {
        List<String> list = new LinkedList<>();
        while (!sentences.isEmpty()) {
            SPhraseSpec sSpec = sentences.remove(0);
            list.add(this.realizer.realiseSentence(sSpec));
        }
        return list;
    }
}
