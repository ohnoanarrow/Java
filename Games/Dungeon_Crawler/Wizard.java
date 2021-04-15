import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread;

public class Wizard extends Monster implements ActionListener {

  private class WizardGame extends JFrame implements ActionListener{

    private boolean won;
    private JLabel prompt;
    private JLabel riddle;
    private JTextField answerBox;
    private ThreadGame timer;
    private int timeElapsed;

    private WizardGame() {
      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      timer = new ThreadGame();

      won = false;
      prompt = new JLabel("You encounter a dark wizard, who demands you answer his riddle or be smote!");
      riddle = new JLabel("What gets wetter as it dries?");
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
      if(answerBox.getText().toLowerCase().equals("towel")) {
        setTimeElapsed(timer.stopAndGetSeconds());
        won = true;
        setVisible(false);
        dispose();
      }
    }//actionPerformed override

  }//WizardGame Class

  private boolean hit;
  private int timeElapsed;

  public Wizard() {
    super();
    addActionListener(this);
    hit = false;
  }//constructor

  @Override
  public void actionPerformed(ActionEvent e) {
      WizardGame game = new WizardGame();
      setEnabled(false);
  }//actionPerformed override

  private void setTimeElapsed(int t) {
    setTime(t);

    setText("Wizard defeated in "+t+" seconds!");
    this.defeat();
  }//setTimeElapsed

}//Wizard class
