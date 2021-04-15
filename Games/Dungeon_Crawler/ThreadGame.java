import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ThreadGame extends Frame implements ActionListener {

  private Label counter;
  private Timer timer;
  private int elapsedSeconds;
  private int elapsedMinutes;
  private int elapsedHours;

  public ThreadGame(){
    setLayout(new FlowLayout());

    elapsedSeconds = 0;
    elapsedMinutes = 0;
    elapsedHours = 0;

    timer = new Timer(1000, this);
    timer.setInitialDelay(0);
    timer.start();

    counter = new Label("0"); // construct the Label
    add(counter);

    setTitle("Counting Timer");
    setSize(250, 100);
  }//constructor

  public static void main(String[] args) {
    new ThreadGame();
  }//main

  @Override
  public void actionPerformed(ActionEvent evt) {
    elapsedSeconds++;

    if(elapsedSeconds == 60) {
      elapsedMinutes++;
      elapsedSeconds = 0;
    }

    if(elapsedMinutes == 60) {
      elapsedHours++;
      elapsedMinutes = 0;
    }

    counter.setText(String.format("%02d", elapsedHours) + ":" + String.format("%02d", elapsedMinutes) + ":" + String.format("%02d", elapsedSeconds)); // Convert int to String and print
  }

  public int stopAndGetSeconds() {
    timer.stop();
    return ((elapsedHours*3600)+(elapsedMinutes*60)+elapsedSeconds);
  }//stop timer and return integer representing seconds

}//class
