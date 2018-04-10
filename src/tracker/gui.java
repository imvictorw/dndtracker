package tracker;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
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
import org.eclipse.swt.widgets.Canvas;

public class gui {

	protected Shell shell;
	private Text text;
	private Text txtbox;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			gui window = new gui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		text = new Text(shell, SWT.BORDER);
		text.setEditable(false);
		text.setBounds(31, 126, 376, 328);
		
		txtbox = new Text(shell, SWT.BORDER);
		txtbox.setBounds(31, 503, 280, 30);
		
		Button btnEnter = new Button(shell, SWT.NONE);
		btnEnter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblName.setBounds(150, 100, 126, 20);
		lblName.setText("RPG Battle Tracker");
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(438, 50, 255, 2);
		
		Label lblMain = new Label(shell, SWT.NONE);
		lblMain.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblMain.setBounds(445, 58, 227, 277);
		lblMain.setText("Commands Include:\r\n   Setup\r\n   Attack [Target] [HP]\r\n   Heal [Target]\r\n   Next \r\n   Add [Class Type] [Name]\r\n   Remove [Character Name]\r\n   Edit [Character Name]");
		
		Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(438, 414, 255, 2);
		
		Label label_3 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(437, 361, 255, 2);
		
		Label lblNewLabel_2 = new Label(shell, SWT.CENTER);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblNewLabel_2.setFont(SWTResourceManager.getFont("Segoe UI", 15, SWT.NORMAL));
		lblNewLabel_2.setBounds(524, 7, 76, 37);
		lblNewLabel_2.setText("Help");
		
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
		btnLog.setBounds(448, 424, 112, 30);
		btnLog.setText("Log");
		
		Button btnNewEncounter = new Button(shell, SWT.NONE);
		btnNewEncounter.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
			}
		});
		btnNewEncounter.setText("New Encounter");
		btnNewEncounter.setBounds(448, 471, 112, 30);
		
		Button btnAddCharacter = new Button(shell, SWT.NONE);
		btnAddCharacter.setText("Add Character");
		btnAddCharacter.setBounds(448, 519, 112, 30);
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.setText("Save");
		btnSave.setBounds(566, 424, 112, 30);
		
		Button btnLoad = new Button(shell, SWT.NONE);
		btnLoad.setText("Load");
		btnLoad.setBounds(566, 471, 112, 30);
		
		Button btnQuit = new Button(shell, SWT.NONE);
		btnQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnQuit.setText("Quit");
		btnQuit.setBounds(566, 519, 112, 30);
		shell.setTabList(new Control[]{txtbox, btnEnter, lblNewLabel, text});

	}
}
