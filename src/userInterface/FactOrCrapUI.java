/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author 
 */
public class FactOrCrapUI extends JFrame {

    public FactOrCrapUI(){
        this.setTitle("Fact Or Crap");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1500, 1100));
        
        test();
        
        this.pack();
        this.setVisible(true);
    }
    private void test (){   
        
        this.setLayout(new GridBagLayout());
        
        JButton newCard = new JButton("New Card");
        JButton start = new JButton("start");
        JButton reset = new JButton("Reset");
        
        QuestionCardUI qc = new QuestionCardUI();
        TurnTimer t = new TurnTimer(5);
        newCard.addActionListener((ActionEvent e) -> {
            qc.getNewQuestionCard();
            System.out.println(qc.getCardQuestions().get(0).question);
        });
        start.addActionListener((ActionEvent e) -> {
            t.start();
        } );
         reset.addActionListener((ActionEvent e) -> {
            t.reset();
        } );
         
         this.add(qc);
         this.add(newCard);
         this.add(t);
         this.add(start);
         this.add(reset);       
    }       
}

