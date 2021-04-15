import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread;

public class Monolith extends Monster implements ActionListener {

  private class MonolithGame extends JFrame implements ActionListener{

    private boolean won;
    private JLabel prompt;
    private JLabel riddle;
    private JTextField answerBox;
    private ThreadGame timer;
    private int timeElapsed;

    private MonolithGame() {

      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      timer = new ThreadGame();

      won = false;
      prompt = new JLabel("You find an ancient monolith with the inscription:");
      riddle = new JLabel("What is broken when you say its name?");
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
  		setSize(400, 200);
    }//constructor

    @Override
    public void actionPerformed(ActionEvent e) {
      if(answerBox.getText().toLowerCase().equals("silence")) {
        setTimeElapsed(timer.stopAndGetSeconds());
        won = true;
        setVisible(false);
        dispose();
      }
    }//actionPerformed override

  }//MonolithGame Class

  private boolean hit;
  private int timeElapsed;

  public Monolith() {
    super();
    addActionListener(this);
    hit = false;
  }//constructor

  @Override
  public void actionPerformed(ActionEvent e) {
      MonolithGame game = new MonolithGame();
      setEnabled(false);
  }//actionPerformed override

  private void setTimeElapsed(int t) {
    setTime(t);
    setText("Monolith solved in "+t+" seconds!");
    this.defeat();
  }//setTimeElapsed

}//Monolith class
