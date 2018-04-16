package tracker;

public class GameDriver {
	
	public static void main(String[] args) {
		gui window = new gui();
		Game game = new Game();
		window.setGameObj(game);
		window.getGameObj().startGameGui();
	}
	
}
