/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import Logic.QuestionFormat;
import Logic.QuestionList;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author Ajay
 * 
 * Sets up the visual layout of a question card and populates it with 
 * 3 pairs of questions and answers
 * 
 * --Public Methods:
 * * void getNewQuestionCard();
 * * void putFaceDown();
 * * ArrayList<QuestionFormat> getCardQuestions();
 */
public class QuestionCardUI extends JPanel {
    
    //Colors
    private final Color QUESTION_BACKGROUND = Color.decode("#ccffff");
    private final Color ANSWER_BACKGROUND = Color.decode("#ccffff");
    private final Color CARD_BACKGROUND = Color.decode("#ffcc00");
    private final Color FACE_DOWN_BACKGROUND = Color.LIGHT_GRAY;
    
    //Contains question and answer pairs that are on the current card
    private ArrayList<QuestionFormat> cardQuestions = new ArrayList<>();
    
    private GridLayout gridLayout;  //Card face layout
    private CardLayout cardLayout; //Container layout
    
    private JPanel faceDown;
   
    //Constructor
    public QuestionCardUI(){
        this.setLayout(cardLayout = new CardLayout());
        this.setPreferredSize(new Dimension(250, 450));
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.setBackground(CARD_BACKGROUND);   
        this.setVisible(true);
        initFaceDown();
        putFaceDown();    
    }
    
    //Flips the card
    public void getNewQuestionCard(){
        JPanel newCard = initCardFace();
        newCard.setVisible(true);
        this.add(newCard, "newCard");
        cardLayout.show(this, "newCard");
    }
    
    //Puts the card face down
    public void putFaceDown(){
        this.add(faceDown, "faceDown");
        cardLayout.show(this, "faceDown");
    }
    
    //Set up the faceDown JPanel
    private void initFaceDown(){
        JLabel label = new JLabel("Card is face down");
        faceDown = new JPanel();
        faceDown.setPreferredSize(new Dimension(250, 450));
        faceDown.setBackground(FACE_DOWN_BACKGROUND);
        faceDown.add(label);
        faceDown.setVisible(true);
    }
    
    
    //Returns an ArrayList of the questions and answers currently on the card
    //Top to bottom, index 0 to 2
    public ArrayList<QuestionFormat> getCardQuestions(){
        return cardQuestions;
    }
    
    //Shuffles the ArrayList of questions, picks the first 3, and adds
    //them and the answers to the panel
    private void addText(JPanel panel){
        
        Collections.shuffle(QuestionList.getList());
        for(int i = 0; i < 3; i++){
            
            cardQuestions.add(i, QuestionList.getList(i));
            
            //Text area for questions
            JTextPane q = new JTextPane();
            q.setToolTipText("Question");
            q.setOpaque(true);
            q.setBorder(BorderFactory.createTitledBorder("Question"));
            q.setBackground(QUESTION_BACKGROUND);
            q.setEditable(false);
            q.setFont(new Font("Serif", Font.BOLD, 16));
            q.setText(QuestionList.getList(i).question);
            q.setEnabled(true);
            panel.add(q);
            
            //Text area for answers
            JTextPane a = new JTextPane();
            a.setToolTipText("Answer");
            a.setOpaque(false);
            //a.setBorder(BorderFactory.createEtchedBorder());
            a.setBackground(ANSWER_BACKGROUND);
            a.setBorder(BorderFactory.createTitledBorder("Answer"));
            a.setEditable(false);
            a.setText("~ "+QuestionList.getList(i).answer+"!");
            a.setFont(new Font("Serif", Font.BOLD+Font.ITALIC, 30));
            a.setEnabled(true);
            panel.add(a);

        }
    }
    
    //Creates new card face
    private JPanel initCardFace(){
        JPanel panel = new JPanel();
        gridLayout = new GridLayout(6, 1, 0, 2);
        panel.setLayout(gridLayout);
        panel.setBackground(CARD_BACKGROUND);
        panel.setPreferredSize(new Dimension(250, 450));
        addText(panel);
        return panel;
    }
          
}
