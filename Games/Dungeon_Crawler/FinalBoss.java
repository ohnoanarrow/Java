import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class FinalBoss extends JFrame implements ActionListener {

  private int ctr;
  private JButton mashButton;
  private boolean won;
  private JLabel counterDown;
  private JLabel counter;
  private JTextField answerBox;
  private Timer timer;
  private int timeElapsed;
  private int timeLeft;

  public FinalBoss(int s) {
    ctr = 0;
    timeLeft = 200000-(s*1000);
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    timer = new Timer(100, countDown);
    mashButton = new JButton("Click me as fast as you can! Get to 100!");
    won = false;
    JPanel panel = new JPanel();
    counterDown = new JLabel(Integer.toString(timeLeft));
    counter = new JLabel(Integer.toString(ctr));
    mashButton.addActionListener(this);
    panel.add(counterDown);
    panel.add(counter);
    panel.add(mashButton);
    getContentPane().add(panel);
    pack();
    setVisible(true);
    setSize(600, 200);
  }

  @Override
  public void actionPerformed(ActionEvent e){
    timer.start();
    ctr++;
    counter.setText(Integer.toString(ctr));
    if(ctr == 100 && timeLeft > 0) {
      timer.stop();
      won = true;
      setVisible(false);
      dispose();
      System.out.println("You won!");
    }
  }//actionPerformed override

  ActionListener countDown=new ActionListener()
  {
      public void actionPerformed(ActionEvent e)
      {
          timeLeft -= 1000;
          counterDown.setText(Integer.toString(timeLeft));
          if(timeLeft<=0)
          {
              timer.stop();
              setVisible(false);
              dispose();
              System.out.println("You lost!");
          }
      }
  };
}
