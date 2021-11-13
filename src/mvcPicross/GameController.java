package mvcPicross;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GameController {

	private GameView gameView;
	private GameModel gameModel;

	//ActionEvent e;

	public GameController( ) {

	}

	public GameController(GameView gameView , GameModel gameModel ) {
		this.gameView = gameView;
		this.gameModel = gameModel;

	}
	public void start() {

		gameView.setVisible(true);
		gameModel.defaultGame();
		gameView.addMyListener(new Controller());

	}



	private Object eventObject;
	//public Object getObject () {return eventObject;}
	public void setObject(Object eventObject) {this.eventObject=eventObject;}
	
	public void resetGame() {
		for(int i =0; i <5 ;i++) {
			for(int  j =0; j < 5; j++) {
				gameView.squares[i][j].setBackground(Color.WHITE);
				gameView.squares[i][j].setEnabled(true);
			}
		}
		gameView.pointsField.setText("");
		gameView.stopTimer();
	}

	public void gameGame(int i, int j) {	
		if(gameView.myBox.isSelected()) {
			if(gameModel.getMyBox(i, j)==0) {
				gameView.myTotalPoints = gameView.myTotalPoints + 1;
				gameView.squares[i][j].setBackground(Color.YELLOW);
			}else {
				gameView.myTotalPoints = gameView.myTotalPoints - 1;
				gameView.squares[i][j].setBackground(Color.RED);
			}
		}else {
			if(gameModel.getMyBox(i, j)==1) {
				gameView.myTotalPoints = gameView.myTotalPoints + 1;
				gameView.squares[i][j].setBackground(Color.GREEN);
			}else {
				gameView.myTotalPoints = gameView.myTotalPoints - 1;
				gameView.squares[i][j].setBackground(Color.RED);
			}
		}
				
		
//		System.out.println(i + " == " + j);
//		System.out.println(" myTotalPoints : " + gameView.myTotalPoints);
		gameView.pointsField.setText("" + gameView.myTotalPoints);
		gameView.squares[i][j].setEnabled(false);

	}
	class Controller implements ActionListener {
		private ActionEvent e;
		public ActionEvent getActionEvent () {return e;}
		public void setActionEvent(ActionEvent e) {this.e=e;}


		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
			int i = 0;
			int j = 0;
			Object eventObject = e.getSource();
			

			
			if (eventObject == gameView.reset) {

				gameView.jTextArea.setText("");
				gameView.jTextArea.append("Mark " + gameView.reset.getText() + "\n");
				resetGame();
			}
			else if (eventObject == gameView.myBox) {
				if (gameView.myBox.isSelected() == true)
					gameView.jTextArea.append(gameView.myBox.getText() + " Selected\n");
				else
					gameView.jTextArea.append(gameView.myBox.getText() + " Unselected\n");

			} else if(eventObject==gameView.solution) {
				gameView.cancelTimer();
				gameView.showSolution(gameModel);
				
			}else {
				for (i = 0; i < 5; i++) {
					for (j = 0; j < 5; j++) {
						if (eventObject == gameView.squares[i][j]) {
							//System.out.println(" eventObject -"+gameView.squares[i][j].getText());
							gameView.jTextArea.append("Pos "+gameView.squares[i][j].getText() + " " + i + " " + j + " clicked\n");
							
							gameGame(i, j);

						}

					}
				}
			}

		}

	}
}
