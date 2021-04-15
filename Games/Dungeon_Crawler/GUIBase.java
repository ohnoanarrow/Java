import java.util.Random;
import java.util.Stack;
import java.awt.font.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Arrays;

public class GUIBase implements ActionListener{

	private static JButton boss;
	private static Monster[] buttons;

	public GUIBase() {
		boss = new JButton("Fight the Boss!");
		boss.addActionListener(this);
		gamePanel(boss);
	}

	public static void main(String[] args)
	{
		new GUIBase(); //launch game
	}

	private static void gamePanel(JButton boss){

		buttons = new Monster[9]; //create 9 buttons
	  SquareHandler handle = new SquareHandler();
	  Stack<Monster> stack = new Stack<Monster>();

		JFrame frame = new JFrame ("The Riddle Dungeon");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);


		JPanel panel = new JPanel(); //creating a panel with a box like a tic tac toe board
		panel.setLayout (new GridLayout (4, 3));
		panel.setBorder (BorderFactory.createLineBorder (Color.gray, 3));
		panel.setBackground (Color.white);

    stack = handle.getMonsters();
		for(int i=0; i<=8; i++){ //placing the button onto the board
			buttons[i] = stack.pop();
			panel.add(buttons[i]);
		}
		JButton dummyButton = new JButton();
		dummyButton.setEnabled(false);

		panel.add(dummyButton);
		panel.add(boss);

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(800, 800);// set frame size and let teh game begin
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean allLevelsWon = true;
		for(Monster room : Arrays.asList(buttons)) {
			if(!room.isDefeated()){
				allLevelsWon = false;
			}//if
		}//loop through all rooms
		if(allLevelsWon){
			new FinalBoss(allSeconds());
			boss.setEnabled(false);
		} else {
			System.out.println("Boss fight failed -- beat all the rooms first!");
		}
	}//actionPerformed override

	private int allSeconds(){
		int temp = 0;
		for(Monster room : Arrays.asList(buttons)) {
			temp += room.getTimeElapsed();
		}//loop through all rooms
		return temp;
	}
}
