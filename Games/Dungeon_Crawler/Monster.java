import java.awt.font.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Monster extends JButton {

  private boolean defeated;
  private int timeElapsed;

  public Monster() {
    super();
    defeated = false;
    timeElapsed = 0;
  }//constructor

  public Monster(String n) {
    super(n);
    defeated = false;
  }

  public void defeat() {
    defeated = true;
  }//defeat method

  public boolean isDefeated() {
    return defeated;
  }

  public int getTimeElapsed() {
    return timeElapsed;
  }//getter

  public void setTime(int s) {
    timeElapsed = s;
  }
}
