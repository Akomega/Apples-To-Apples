/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 *
 * Constructor takes in the time limit, and creates a progress bar
 * that counts from 0 up to that time.
 */
public class turnTimer extends JPanel {
    
    private int counter = 0;
    private JProgressBar bar;
    private Timer timer;
    private JLabel label;
    
    public Boolean isTimeUp;
    
    public turnTimer(int limit){
        setIsTimeUp(true);
        initComponents(limit);
        this.add(bar);
        this.add(label);

    }
    
    public void start(){
        timer.start();
        
    }
    
    public void stop(){
        timer.stop();
    }
    
    public void reset(){
        counter = 0;
        label.setText(String.valueOf(counter));
        bar.setValue(0);
        timer.stop();
        setIsTimeUp(true);
    }
    
    private void initComponents(int timeLimit){
        
        
      bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, timeLimit);  
      label = new JLabel(String.valueOf(counter), JLabel.CENTER);
      timer = new Timer(1000, new ActionListener(){

          @Override
          public void actionPerformed(ActionEvent ae) {
              label.setText(String.valueOf(counter+1));
        
                  setIsTimeUp(false);
              
              bar.setValue(++counter);
              if(counter == timeLimit){
                  timer.stop();
                  setIsTimeUp(true);
              }
          }
          
      });
    }

    /**
     * @return the isTimeUp
     */
    public Boolean getIsTimeUp() {
        return isTimeUp;
    }

    /**
     * @param isTimeUp the isTimeUp to set
     */
    public void setIsTimeUp(Boolean isTimeUp) {
        this.isTimeUp = isTimeUp;
    }
    
    
}
