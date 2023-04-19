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
            }
        }
        return list;
    }
    
    public SPhraseSpec handleDescribeDestinationMessage(DescribeDestinationMessage message) {
        SPhraseSpec s1 = this.nlgFactory.createClause();
        return s1;
    }
}
