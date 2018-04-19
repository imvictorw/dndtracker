package tracker;

public class GameDriver {
	
	public Game masterGame;
	public gui masterGUI;
	
	public static void main(String[] args) {
		gui window = new gui();
		window.open();
		window.getGameObj().startGameGui();
		
	}
	
}
