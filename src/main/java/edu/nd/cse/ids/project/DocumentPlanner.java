package edu.nd.cse.ids.project;

import edu.nd.cse.ids.project.messages.*;

import java.util.List;
import java.util.LinkedList;
import java.io.File;
import java.util.Arrays;

public class DocumentPlanner
{
    private List<Message> messages;

    public DocumentPlanner()
    {
        this.messages = new LinkedList<Message>();
    }

    public void createMessage(DestInfo d1, int typeFlag) {
        if (typeFlag == 1) {
            DescribeDestinationMessage m1 = new DescribeDestinationMessage();
            m1.setLongitude(d1.getLongitude());
            m1.setLatitude(d1.getLatitude());
            m1.setLongitude(d1.getPopulation());
            //m1.setDescription(d1.getDesrciption());
            this.messages.add(m1);
        }
         
    }
    
    public List<Message> getMessages() {
        return messages;
    }
}