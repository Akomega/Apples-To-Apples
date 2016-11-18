/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Ajay
 * 
 * Constructor parses the XML file containing questions and answers and
 * populates the ArrayList, "list", containing all questions and answers
 * 
 * --Public Methods:
 * * QuestionFormat getList(int location);
 * * ArrayList<QuestionFormat> getList();
 * * void printList();
 */
public class QuestionList {
    
    private Document document;  //Holds the XML file
    private static final ArrayList<QuestionFormat> list = new ArrayList<>(); //The list containing all questions and answers

    //Constructor
    public  QuestionList(){
        parseXmlFile();
    }
    
    //Static method returns a specific index in the list
    public static QuestionFormat getList(int location){
        return list.get(location);
    }
    
    //Static method returns entire the list
    public static ArrayList<QuestionFormat> getList(){
        return list;
    }
    
    //Static method prints list of questions and answers
    public static void printList(){
        if(!list.isEmpty()){
            for(int i = 0; i < list.size(); i++){
                System.out.println(i);
                System.out.println("Question:"+ list.get(i).question + '\n' + "Answer: " + list.get(i).answer + '\n');
                
            }
        }
    }
    
    //Populates the ArrayList "list"
    private void parseXmlFile(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            
            //XML into document as a tree
            document = db.parse(ClassLoader.getSystemResourceAsStream("Logic/cards.xml"));
            
            //Should get the <factOrCrap> element
            NodeList factOrCrap = document.getDocumentElement().getChildNodes();
            
            for(int i = 0; i < factOrCrap.getLength(); i++){
                
                //Get <card> elements
                Node card = factOrCrap.item(i);
                
                if(card instanceof Element){
                    String question = "";
                    String answer = "";
                    
                    NodeList childNodes = card.getChildNodes();
                    
                    //Get <question> and <answer> elements, put them into ArrayList
                    for(int k = 0; k < childNodes.getLength(); k ++){
                        Node cNode = childNodes.item(k);
                        
                        if(cNode instanceof Element){
                            String content = cNode.getLastChild().getTextContent().trim();
                            switch(cNode.getNodeName()){
                                case "question":
                                    question = content;
                                    break;
                                case "answer":
                                    answer = content;
                                    break;
                            }
                        }
                    }
                    //Do the thing
                    QuestionFormat q = new QuestionFormat(question, answer);
                    list.add(q);     
                }
                
            }
           //Sure 
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(QuestionList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}