import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread;

public class Fairies extends Monster implements ActionListener {

  private class FairiesGame extends JFrame implements ActionListener{

    private boolean won;
    private JLabel prompt;
    private JLabel riddle;
    private JTextField answerBox;
    private ThreadGame timer;
    private int timeElapsed;

    private FairiesGame() {

      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      timer = new ThreadGame();

      won = false;
      prompt = new JLabel("You encounter a swarm of fairies. Mystical, high-pitched whispers fill your ears:");
      riddle = new JLabel("Forward I am heavy, backwards I am not. What am I?");
      answerBox = new JTextField();

      answerBox.addActionListener(this);
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(3, 1));
      panel.add(prompt);
      panel.add(riddle);
      panel.add(answerBox);

      addActionListener(this);

      getContentPane().add(panel);
  		pack();
  		setVisible(true);
  		setSize(600, 200);
    }//constructor

    @Override
    public void actionPerformed(ActionEvent e) {
      if(answerBox.getText().toLowerCase().equals("ton")) {
        setTimeElapsed(timer.stopAndGetSeconds());
        won = true;
        setVisible(false);
        dispose();
      }
    }//actionPerformed override

  }//FairiesGame Class

  private boolean hit;
  private int timeElapsed;

  public Fairies() {
    super();
    addActionListener(this);
    hit = false;
  }//constructor

  @Override
  public void actionPerformed(ActionEvent e) {
      FairiesGame game = new FairiesGame();
      setEnabled(false);
  }//actionPerformed override

  private void setTimeElapsed(int t) {
    setTime(t);
    setText("Fairies defeated in "+t+" seconds!");
    this.defeat();
  }//setTimeElapsed

}//Fairies class
