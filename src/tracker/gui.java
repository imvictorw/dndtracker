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

public class gui {

	protected Shell shell;
	private Text text;
	private Text text_1;

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
		shell.setSize(700, 606);
		shell.setText("SWT Application");
		
		text = new Text(shell, SWT.BORDER);
		text.setEditable(false);
		text.setBounds(31, 60, 376, 328);
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(31, 459, 280, 30);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(317, 459, 90, 30);
		btnNewButton.setText("Enter");
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(437, -20, 2, 579);
		
		CLabel lblNewLabel = new CLabel(shell, SWT.NONE);
		lblNewLabel.setBounds(31, 427, 76, 26);
		lblNewLabel.setText("Type here:");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setBounds(144, 24, 126, 20);
		lblNewLabel_1.setText("LOGO GOES HERE");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(450, 10, 108, 34);
		btnNewButton_1.setText("Help");
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setBounds(564, 10, 108, 34);
		btnNewButton_2.setText("More Options");
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(438, 50, 255, 2);
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		lblNewLabel_2.setBounds(448, 63, 224, 486);
		lblNewLabel_2.setText("Help:\r\n\r\nBlajflskdjfa\r\n\r\nAcceptable Commands:\r\nSetup\r\n Attack [Target] [HP]\r\n Heal [Target]\r\n Next \r\n Add [Class Type] [Name]\r\n Remove [Character Name]\r\n Edit [Character Name]");
		shell.setTabList(new Control[]{text_1, btnNewButton, lblNewLabel, text});

	}
}
