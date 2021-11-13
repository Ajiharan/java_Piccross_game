package mvcPicross;

public class GamePicross {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		exc();
	}
	public static void exc() {
		GameModel gameModel = new GameModel();
		GameView gameView = new GameView();
		GameController controller = new GameController(gameView, gameModel);
		((GameController) controller).start();
	}

}
