/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/**
 * FUNCTIONAL REQUIREMENT 1.0 - "The system shall maintain a timed functionality"
 * @author Ajay
 * 
 * Constructor takes in the time limit, and creates a progress bar that will
 * count down to 0.
 * 
 * --Public Methods:
 * * void start();
 * * void stop();
 * * void reset();
 * * bool getIsTimeUp();
 * * void setIsTimeUp(bool x);
 * 
 */
public class TurnTimer extends JPanel {
    
    //Colors
    private static final Color BAR_COLOR = Color.decode("#8080ff");
    private static final Color BACKGROUND_COLOR = Color.decode("#9999FF");
    
    //Components
    private JProgressBar bar;
    private Timer timer;
    private JLabel label;
    
    //Variables
    public int timeLimit; //The passed in argument
    public int countdown;
    
    public Boolean isTimeUp; //True when countdown = 0, or if countdown = timeLimit
                            //False when countdown is between 0 and timeLimit, exclusive
    
    //Constructor
    public TurnTimer(int limit){
        setIsTimeUp(true);
        initComponents(limit);
        initVisuals();
        this.add(bar);
        this.add(label);
    }
    
    /**
     * Returns true if countdown = 0, or countdown = timeLimit
     * Returns false when countdown is between 0 and timeLimit, exclusive. (When the clock is actually running)
     * @return 
     */
    public Boolean getIsTimeUp() {
        return isTimeUp;
    }

    /**
     * Sets the thing
     * @param isTimeUp
     */
    public void setIsTimeUp(Boolean isTimeUp) {
        this.isTimeUp = isTimeUp;
    }
    
    //Timer methods
    public void start(){
        timer.start();      
    }
    public void stop(){
        timer.stop();  
    }
    public void reset(){
        bar.setValue(timeLimit+1);
        label.setText(String.valueOf(timeLimit));
        bar.setValue(timeLimit+1);
        countdown = timeLimit;
        timer.stop();
        setIsTimeUp(true);
    }
    
    //Implements colors and borders
    private void initVisuals(){
        this.setBorder(BorderFactory.createEtchedBorder());
        bar.setForeground(BAR_COLOR);
        this.setBackground(BACKGROUND_COLOR);
        
    }

         
    //Do all the things
    private void initComponents(int timeLimit){  
       this.timeLimit = timeLimit;
       countdown = timeLimit;
        
      //Instantiate components
      bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, timeLimit+1);
      bar.setValue(timeLimit+1); 
      label = new JLabel(String.valueOf(timeLimit), JLabel.CENTER);
      this.add(new JLabel("Time Limit"));     
      
      //Timer will perform these actions every second     
      timer = new Timer(1000, new ActionListener(){                         
          @Override
          public void actionPerformed(ActionEvent ae) {
              setIsTimeUp(false);              
              bar.setValue(--countdown);
              label.setText(String.valueOf(countdown)); 
              if(countdown == 0){
                  timer.stop();
                  setIsTimeUp(true);
                  countdown = timeLimit;                                    
              }
          }        
      });
    }
    
  
}
