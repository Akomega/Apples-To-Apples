/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ajay
 */
public class FactOrCrapUI extends JFrame {
    
    public JButton on;
    public JButton reset;
    public JPanel panel = new JPanel();
    
    
    public FactOrCrapUI(){
        initComponents();
    }
    
    
    private void initComponents(){
        turnTimer timer = new turnTimer(10);
        //this.add(timer);
        
        on = new JButton();
        on.setText("ON");
        on.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                timer.setIsTimeUp(false);
                timer.start();
                
            }
        });
        
        reset = new JButton();
        reset.setText("OFF");
        reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                timer.reset();
            }
        });
        

        panel.add(on);
        panel.add(reset);
        panel.add(timer);

        this.add(panel);
        this.setPreferredSize(new Dimension(400,400));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        
        while(true){
            System.out.println(timer.getIsTimeUp());
            if(timer.getIsTimeUp() == false){
                break;
            }
        }
        
    }
   
    
}
