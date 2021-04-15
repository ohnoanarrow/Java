import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Start extends Monster implements ActionListener {

  public Start() {
    super("Start");
    setEnabled(false);
    setFont(new Font("Dialog", 1, 75));
    this.defeat();
  }//constructor

  @Override
  public void actionPerformed(ActionEvent e) {

  }//actionPerformed override

}
