import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Thread;

public class Item extends Monster implements ActionListener {

  private class ItemGame extends JFrame implements ActionListener{

    private boolean won;
    private JLabel prompt;
    private JLabel riddle;
    private JTextField answerBox;
    private ThreadGame timer;
    private int timeElapsed;

    private ItemGame() {

      setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
      timer = new ThreadGame();

      won = false;
      prompt = new JLabel("You find a mysterious box of treasure. Inside is a shield, a helmet, a dagger, a sword, and an arrow.");
      riddle = new JLabel("\"Take that which is mightier than I.\" -Mr. Penn. Which do you take?");
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
  		setSize(750, 200);
    }//constructor

    @Override
    public void actionPerformed(ActionEvent e) {
      if(answerBox.getText().toLowerCase().equals("sword")) {
        setTimeElapsed(timer.stopAndGetSeconds());
        won = true;
        setVisible(false);
        dispose();
      }
    }//actionPerformed override

  }//ItemGame Class

  private boolean hit;
  private int timeElapsed;

  public Item() {
    super();
    addActionListener(this);
    hit = false;
  }//constructor

  @Override
  public void actionPerformed(ActionEvent e) {
      ItemGame game = new ItemGame();
      setEnabled(false);
  }//actionPerformed override

  private void setTimeElapsed(int t) {
    setTime(t);
    setText("Item recovered in "+t+" seconds!");
    this.defeat();
  }//setTimeElapsed

}//Item class
