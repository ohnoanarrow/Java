import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread;

public class Pirate extends Monster implements ActionListener {

  private class PirateGame extends JFrame implements ActionListener{

    private boolean won;
    private JLabel prompt;
    private JLabel riddle;
    private JTextField answerBox;
    private ThreadGame timer;
    private int timeElapsed;

    private PirateGame() {

      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      timer = new ThreadGame();

      won = false;
      prompt = new JLabel("You encounter a horde of pirates! They demand that yee answer their riddle,\n or you'll be walkin' the plank, matey!");
      riddle = new JLabel("\"If me crew has eighteen buccaneers,\n and a third o' them have peg legs,\n how many peg legs do I need to order on Amazon for me crew?\"");
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
  		setSize(1000, 200);
    }//constructor

    @Override
    public void actionPerformed(ActionEvent e) {
      if(answerBox.getText().toLowerCase().equals("six")) {
        setTimeElapsed(timer.stopAndGetSeconds());
        won = true;
        setVisible(false);
        dispose();
      }
    }//actionPerformed override

  }//PirateGame Class

  private boolean hit;
  private int timeElapsed;

  public Pirate() {
    super();
    addActionListener(this);
    hit = false;
  }//constructor

  @Override
  public void actionPerformed(ActionEvent e) {
      PirateGame game = new PirateGame();
      setEnabled(false);
  }//actionPerformed override

  private void setTimeElapsed(int t) {
    setTime(t);
    setText("Pirate defeated in "+t+" seconds!");
    this.defeat();
  }//setTimeElapsed

}//Pirate class
