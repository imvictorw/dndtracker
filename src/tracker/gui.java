package tracker;

import org.eclipse.swt.SWT;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.*;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.wb.swt.SWTResourceManager;

import classes.MonsterType;
import classes.Player;
import classes.PlayerType;

import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.custom.StyledText;

/*
 * NOTES:
 * 
 */

public class gui implements igui {

	protected Shell shell;
	private Text txtbox;
	public StyledText logText;
	public Game gameObj = new Game();
	private String lastCommand;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public gui() {

	}

	public static void main(String[] args) {
		try {
			gui window = new gui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setGameObj(Game inputGame) {
		gameObj = inputGame;
	}

	public Game getGameObj() {
		return gameObj;
	}

	public void addLogText(String inputText) {
		String endtext = null;
		ArrayList<String> tempList = new ArrayList<String>();
		tempList = gameObj.getLogString();
		for (String thing : tempList) {
			endtext += "\n" + thing;
		}
		endtext += "\n" + inputText;
		logText.setText(endtext);
	}

	public void updateLogText() {
		String endtext = "";
		ArrayList<String> tempList = new ArrayList<String>();
		tempList = gameObj.getLogString();
		for (String thing : tempList) {
			endtext += "\n" + thing;
		}
		logText.setText(endtext);
	}

	// Sends string to text box, adds new line
	public void writeToBox(String string) {
		String curBox = txtbox.getText();
		txtbox.setText(curBox + string);
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		shell.setSize(700, 606);
		shell.setText("SWT Application");

		txtbox = new Text(shell, SWT.BORDER);

		// **** DONT TOUCH THIS ITS STUPID
		txtbox.addListener(SWT.Traverse, new Listener() {
			/*
			 * public void handleEvent(Event e) { if(e.id == SWT.TRAVERSE_RETURN)
			 * System.out.println("GOod job"); }
			 */

			// @Override
			// Removing the override made this work for some reason.
			public void handleEvent(org.eclipse.swt.widgets.Event e) {
				if (e.detail == SWT.TRAVERSE_RETURN) {
					sendToGame();
				} else if (e.detail == SWT.ARROW_UP) {
					txtbox.setText(lastCommand);
					//doesn't work.
				}

			}
		});
		// DONT TOUCH THIS ITS STUPID ****

		txtbox.setBounds(31, 503, 280, 30);

		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sendToGame();
			}
		});
		btnEnter.setBounds(317, 503, 90, 30);
		btnEnter.setText("Enter");

		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(437, -20, 2, 579);

		CLabel lblNewLabel = new CLabel(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblNewLabel.setBounds(34, 471, 76, 26);
		lblNewLabel.setText("Type here:");

		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(438, 50, 255, 2);

		Label lblMain = new Label(shell, SWT.NONE);
		lblMain.setAlignment(SWT.CENTER);
		lblMain.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblMain.setBounds(445, 58, 227, 297);
		lblMain.setText(
				"setup\r\nnext\r\nsave\r\nload\r\nadd player\r\nadd player2\r\nadd monster\r\nremove\r\nedit\r\nheal\r\nattack\r\nalive");

		Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(438, 414, 255, 2);

		Label label_3 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(437, 361, 255, 2);

		Label lblNewLabel_2 = new Label(shell, SWT.CENTER);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lblNewLabel_2.setBounds(492, 7, 136, 37);
		lblNewLabel_2.setText("Commands");

		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		canvas.setBounds(437, -12, 256, 64);

		Label lblMoreOptions = new Label(shell, SWT.CENTER);
		lblMoreOptions.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblMoreOptions.setText("More Options");
		lblMoreOptions.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lblMoreOptions.setBounds(472, 371, 180, 37);

		Canvas canvas_1 = new Canvas(shell, SWT.NONE);
		canvas_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		canvas_1.setBounds(437, 363, 256, 53);

		Button btnLog = new Button(shell, SWT.NONE);
		btnLog.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JFrame newFrame = new JFrame();
				JOptionPane.showMessageDialog(newFrame, "Help Text");
			}
		});
		btnLog.setBounds(448, 424, 112, 30);
		btnLog.setText("Help");

		Button btnNewEncounter = new Button(shell, SWT.NONE);
		btnNewEncounter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gameObj.next();
				updateLogText();
			}
		});
		btnNewEncounter.setText("New Encounter");
		btnNewEncounter.setBounds(448, 471, 112, 30);

		Button btnAddCharacter = new Button(shell, SWT.NONE);
		btnAddCharacter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String inputName = null;
				String inputType = null;
				int inputHealth = 0;
				int inputLvl = 0;

				JFrame frame = new JFrame();
				Object[] options = { "Starter Character", "Advanced Character", "Monster" };
				int n = JOptionPane.showOptionDialog(frame, "What type of character would you like to add?",
						"Add Character", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						options[2]);
				// Case statement to determine type of character chosen
				switch (n) {
				case 0:
					boolean looping = true;
					while (looping) {

						// this loop checks name for character limit
						boolean checklimit = true;
						while (checklimit) {
							boolean exists = false;
							boolean isEmpty = false;
							ArrayList<Player> playerlist = new ArrayList<Player>();
							inputName = JOptionPane.showInputDialog("Enter the player's name:").toString();
							try {
								playerlist = gameObj.getCurrEncounterList();
							} catch (NullPointerException p) {
								isEmpty = true;
							}
							if (!isEmpty) {
								for (Player checkName : playerlist) {
									if (checkName.getName().equals(inputName)) {
										exists = true;
										break;
									}
								}
							}
							// set character limit on name to be 100 characters
							if (inputName.length() <= 100) {
								checklimit = false;
							} else {
								JOptionPane.showMessageDialog(frame,
										"Your character's name cannot be more than 100 characters!");
							}

							// ends loop only if player name does not exist in currEncounterList
							if (exists) {
								JOptionPane.showMessageDialog(frame,
										"Cannot have the same name with somebody in the encounter");
							} else {
								checklimit = false;
							}
						}

						boolean checkclass = true;
						while (checkclass) {
							inputType = JOptionPane.showInputDialog("Enter the player's class:").toString()
									.toUpperCase();
							// Checks the type giving to the types allowed
							boolean found = false;
							for (PlayerType type : PlayerType.values()) {
								if (inputType.equals(type.toString())) {
									found = true;
									break;
								}
							}
							if (found) {
								checkclass = false;
								looping = false;
							} else {
								JOptionPane.showMessageDialog(frame,
										"Please enter a correct type\n" + "	BARBARIAN,\n" + "	BARD,\n"
												+ "	CLERIC,\n" + "	DRUID,\n" + "	FIGHTER,\n" + "	MONK,\n"
												+ "	PALADIN,\n" + "	RANGER,\n" + "	ROGUE,\n" + "	SORCERER,\n"
												+ "	WARLOCK,\n" + "	WIZARD");
							}
						}
					}
					gameObj.addPlayerGUI(inputName, inputType);
					break;

				case 1:
					boolean looping1 = true;
					while (looping1) {
						boolean checklimit1 = true;
						while (checklimit1) {
							boolean exists1 = false;
							inputName = JOptionPane.showInputDialog("Enter the player's name:").toString();
							for (Player checkName : gameObj.getCurrEncounterList()) {
								if (checkName.getName().equals(inputName)) {
									exists1 = true;
									break;
								}
							}
							// set character limit on name to be 100 characters
							if (inputName.length() <= 100) {
								checklimit1 = false;
							} else {
								JOptionPane.showMessageDialog(frame,
										"Your character's name cannot be more than 100 characters!");
							}
							if (exists1) {
								JOptionPane.showMessageDialog(frame,
										"Cannot have the same name with somebody in the encounter");
							} else {
								checklimit1 = false;
							}
						}
						boolean checkclass1 = true;
						int counter1 = 0;
						while (checkclass1) {
							inputType = JOptionPane.showInputDialog("Enter the player's class:").toString()
									.toUpperCase();
							// Checks the type giving to the types allowed
							boolean found1 = false;
							for (PlayerType type : PlayerType.values()) {
								if (inputType.equals(type.toString())) {
									found1 = true;
									break;
								}
							}

							if (found1) {
								checkclass1 = false;
								looping1 = false;
							} else {// if(counter1 > 0){
								JOptionPane.showMessageDialog(frame,
										"Please enter a correct type\n" + "	BARBARIAN,\n" + "	BARD,\n"
												+ "	CLERIC,\n" + "	DRUID,\n" + "	FIGHTER,\n" + "	MONK,\n"
												+ "	PALADIN,\n" + "	RANGER,\n" + "	ROGUE,\n" + "	SORCERER,\n"
												+ "	WARLOCK,\n" + "	WIZARD");
							}
							/*
							 * else { //the MessageDialog showing all player types would appear before the
							 * InputDialog, this ensures that //the MessageDialog isn't shown on the first
							 * loop through. counter1++; }
							 */
						}

						boolean checkhealth1 = true;
						while (checkhealth1) {
							boolean failed1 = false;
							boolean checkforint1 = true;
							while (checkforint1) {

								// catch exception allows only numerical input
								try {
									inputHealth = Integer
											.parseInt(JOptionPane.showInputDialog("Enter the player's health:"));
								} catch (NumberFormatException q) {
									failed1 = true;
									JOptionPane.showMessageDialog(frame, "You must enter a number!");
								} finally {
									if (!failed1) {
										// stops loop
										checkforint1 = false;
									} else {
										// restarts loop with initial value of 'failed'
										failed1 = false;
									}
								}

							}
							if (inputHealth > 2000000000) {
								JOptionPane.showMessageDialog(frame, "You're not that tough -_-");
							} else if (inputHealth <= 0) {
								JOptionPane.showMessageDialog(frame, "You cannot start with 0hp.");
							} else {
								checkhealth1 = false;
							}
						}

						boolean checklvl1 = true;
						while (checklvl1) {
							boolean fail1 = false;
							try {
								inputLvl = Integer
										.parseInt(JOptionPane.showInputDialog("Enter the player's level (1-20):"));
							} catch (NumberFormatException q) {
								fail1 = true;
								JOptionPane.showMessageDialog(frame, "You must enter a number!");
							}

							if (!fail1) {
								if (inputLvl <= 20 && inputLvl > 0) {
									checklvl1 = false;
								} else {
									JOptionPane.showMessageDialog(frame, "Character level must be between 1 and 20.");
								}
							}
						}

					}
					gameObj.addPlayerGUI2(inputName, inputType, inputHealth, inputLvl);
					break;

				// do this still
				case 2:
					boolean looping2 = true;
					while (looping2) {
						boolean checklimit2 = true;
						while (checklimit2) {
							boolean exists2 = false;
							boolean isEmpty2 = false;
							ArrayList<Player> playerlist = new ArrayList<Player>();
							inputName = JOptionPane.showInputDialog("Enter the monster's name:").toString();
							try {
								playerlist = gameObj.getCurrEncounterList();
							} catch (NullPointerException p) {
								isEmpty2 = true;
							}
							if (!isEmpty2) {
								for (Player checkName : playerlist) {
									if (checkName.getName().equals(inputName)) {
										exists2 = true;
										break;
									}
								}
							}
							// set character limit on name to be 100 characters
							if (inputName.length() <= 100) {
								checklimit2 = false;
							} else {
								JOptionPane.showMessageDialog(frame,
										"The monster's name cannot be more than 100 characters!");
							}

							// ends loop only if monster name does not exist in currEncounterList
							if (exists2) {
								JOptionPane.showMessageDialog(frame,
										"Cannot have the same name with somebody in the encounter!");
							} else {
								checklimit2 = false;
							}
						}
						boolean checkclass2 = true;
						int counter2 = 0;
						while (checkclass2) {
							inputType = JOptionPane.showInputDialog("Enter the monster's class:").toString()
									.toUpperCase();
							// Checks the type giving to the types allowed
							boolean found2 = false;
							for (MonsterType type : MonsterType.values()) {
								if (inputType.equals(type.toString())) {
									found2 = true;
									break;
								}
							}

							if (found2) {
								checkclass2 = false;
							} else {// if(counter2 > 0){
								JOptionPane.showMessageDialog(frame,
										"Please enter a correct type\n 	ABERRATION,\n" + "	BEAST,\n"
												+ "	CELESTIAL,\n" + "	CONSTRUCT,\n" + "	DEMON,\n" + "	DRAGON,\n"
												+ "	ELEMENTAL,\n" + "	FEY,\n" + "	FIEND,\n" + "	GIANT,\n"
												+ "	HUMANOID,\n" + "	MONSTROSITY,\n" + "	OOZE,\n" + "	PLANT,\n"
												+ "	UNDEAD");
							}
							/*
							 * else { //the MessageDialog showing all player types would appear before the
							 * InputDialog, this ensures that //the MessageDialog isn't shown on the first
							 * loop through. counter2++; }
							 */
						}
						boolean checkhealth2 = true;
						while (checkhealth2) {
							boolean failed2 = false;
							boolean checkforint2 = true;
							while (checkforint2) {

								// catch exception allows only numerical input
								try {
									inputHealth = Integer
											.parseInt(JOptionPane.showInputDialog("Enter the monster's health:"));
								} catch (NumberFormatException q) {
									failed2 = true;
									JOptionPane.showMessageDialog(frame, "You must enter a number!");
								} finally {
									if (!failed2) {
										// stops loop
										checkforint2 = false;
									} else {
										// restarts loop with initial value of 'failed'
										failed2 = false;
									}
								}

							}
							if (inputHealth > 2000000000) {
								JOptionPane.showMessageDialog(frame, "No monster is that tough!");
							} else if (inputHealth <= 0) {
								JOptionPane.showMessageDialog(frame, "Monsters cannot start with less than 1hp.");
							} else {
								checkhealth2 = false;
							}
						}
						boolean checklvl2 = true;
						while (checklvl2) {
							boolean fail2 = false;
							try {
								inputLvl = Integer
										.parseInt(JOptionPane.showInputDialog("Enter the monster's level (1-20):"));
							} catch (NumberFormatException q) {
								fail2 = true;
								JOptionPane.showMessageDialog(frame, "You must enter a number!");
							}

							if (!fail2) {
								if (inputLvl <= 20 && inputLvl > 0) {
									checklvl2 = false;
									looping2 = false;
								} else {
									JOptionPane.showMessageDialog(frame, "Character level must be between 1 and 20.");
								}
							}
						}
						gameObj.addMonsterGUI(inputName, inputType, inputHealth, inputLvl);
					}
					break;
				}
			}
		});
		btnAddCharacter.setText("Add Character");
		btnAddCharacter.setBounds(448, 519, 112, 30);

		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gameObj.save();
				updateLogText();
			}
		});
		btnSave.setText("Save");
		btnSave.setBounds(566, 424, 112, 30);

		Button btnLoad = new Button(shell, SWT.NONE);
		btnLoad.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				gameObj.load();
				updateLogText();
			}
		});
		btnLoad.setText("Load");
		btnLoad.setBounds(566, 471, 112, 30);

		Button btnQuit = new Button(shell, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// add dialog asking if they want to save before they quit
				System.exit(0);
			}
		});
		btnQuit.setText("Quit");
		btnQuit.setBounds(566, 519, 112, 30);

		logText = new StyledText(shell, SWT.V_SCROLL | SWT.BORDER);
		logText.addModifyListener(new ModifyListener() {
			@Override
			// Auto scrolls to bottom whenever modified.
			public void modifyText(ModifyEvent e) {
				logText.setTopIndex(logText.getLineCount() - 1);
			}
		});
		logText.setDoubleClickEnabled(false);
		logText.setEditable(false);
		logText.setBounds(31, 126, 376, 343);

		CLabel lblTest = new CLabel(shell, SWT.NONE);
		lblTest.setImage(SWTResourceManager.getImage(gui.class, "/tracker/RPGBTlogo_sm.png"));
		lblTest.setBounds(31, 10, 376, 110);
		lblTest.setText("");
		shell.setTabList(new Control[] { txtbox, btnEnter, lblNewLabel });

	}

	public void sendToGame() {
		String txtInput = txtbox.getText();
		lastCommand = txtInput;
		if (txtInput != null) {
			// Add check for acceptable strings
			gameObj.scanInput(txtInput);
			updateLogText();
			txtbox.setText("");
		}
	}
}
