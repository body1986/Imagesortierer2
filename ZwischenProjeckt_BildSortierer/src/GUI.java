

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.isr.Imagesortierer.Configfile;
import de.isr.Imagesortierer.Main;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private JPanel				contentPane;
	private JTextField			txtSortpath;
	Configfile					configFiles			= new Configfile();
	private JTextField			txtRootPath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				// configFiles.createConfigFiles();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 478, 361);
		contentPane = new JPanel();
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {

			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblFinish = new JLabel("Fertig");
		lblFinish.setVisible(false);
		lblFinish.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFinish.setForeground(Color.GREEN);
		lblFinish.setBounds(104, 232, 71, 34);
		contentPane.add(lblFinish);

		final JLabel lblinProcess = new JLabel("in Arbeit");
		lblinProcess.setVisible(false);
		lblinProcess.setForeground(Color.RED);
		lblinProcess.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblinProcess.setBounds(10, 232, 104, 34);
		contentPane.add(lblinProcess);

		JLabel lblLogoHeader = new JLabel("Bild Sortierer");
		lblLogoHeader.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblLogoHeader.setBounds(0, 3, 147, 14);
		contentPane.add(lblLogoHeader);

		JLabel lbRootpath = new JLabel("Pfad der zu Sortierenden Bilder(von):");
		lbRootpath.setBounds(10, 28, 335, 14);
		contentPane.add(lbRootpath);

		/**
		 * Öffnet einen OpenFileDialog für den zu sortierenden Ordner
		 */
		JButton btnOpenRootpath = new JButton("Ordner \u00F6ffnen");
		btnOpenRootpath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtRootPath.setText(openfiledialog()); // öffnet einen OpenFiledialog
			}
		});
		btnOpenRootpath.setBounds(352, 44, 104, 21);
		contentPane.add(btnOpenRootpath);

		JLabel lblImageSortTo = new JLabel("Bilder Sortieren nach :");
		lblImageSortTo.setBounds(10, 122, 147, 14);
		contentPane.add(lblImageSortTo);

		final JRadioButton rbtnYear = new JRadioButton("nach Jahr");
		rbtnYear.setBounds(10, 143, 109, 23);
		contentPane.add(rbtnYear);

		final JRadioButton rbtnMonath = new JRadioButton("nach Monat");
		rbtnMonath.setBounds(137, 143, 109, 23);
		contentPane.add(rbtnMonath);

		final JRadioButton rbtnDay = new JRadioButton("nach Tag");
		rbtnDay.setSelected(true);
		rbtnDay.setBounds(269, 143, 109, 23);
		contentPane.add(rbtnDay);

		final ButtonGroup sortier_group = new ButtonGroup();
		sortier_group.add(rbtnYear);
		sortier_group.add(rbtnMonath);
		sortier_group.add(rbtnDay);
		
		final JComboBox cbArchive = new JComboBox();
		cbArchive.setModel(new DefaultComboBoxModel(new String[] {"Ja", "Nein"}));
		cbArchive.setSelectedIndex(0);
		cbArchive.setBounds(17, 189, 116, 20);
		contentPane.add(cbArchive);
		
		JLabel lblBackupArchiveErstellen = new JLabel("Backup Archive erstellen?");
		lblBackupArchiveErstellen.setBounds(17, 173, 130, 14);
		contentPane.add(lblBackupArchiveErstellen);
		
		final JComboBox cbTreansvermode = new JComboBox();
		cbTreansvermode.setModel(new DefaultComboBoxModel(new String[] {"Kopieren", "Verschieben"}));
		cbTreansvermode.setSelectedIndex(1);
		cbTreansvermode.setBounds(156, 189, 116, 20);
		contentPane.add(cbTreansvermode);
		
		final JComboBox cbinvalideData = new JComboBox();
		cbinvalideData.setModel(new DefaultComboBoxModel(new String[] {"Ja", "Nein"}));
		cbinvalideData.setSelectedIndex(1);
		cbinvalideData.setBounds(294, 189, 116, 20);
		contentPane.add(cbinvalideData);
		
		JLabel lblTranvermode = new JLabel("Tranver-Mode");
		lblTranvermode.setBounds(156, 173, 130, 14);
		contentPane.add(lblTranvermode);
		
		JLabel label = new JLabel("Ung\u00FCltige Daten Transverrieren");
		label.setBounds(294, 173, 162, 14);
		contentPane.add(label);

		/**
		 * Startet den Sortierprocess
		 */
		JButton btnStartSort = new JButton("Sortieren Starten");
		btnStartSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblinProcess.setVisible(true);
				lblFinish.setVisible(false);
				String rootPath = txtRootPath.getText();
				System.out.println(rootPath);
				String sortPath = txtSortpath.getText();
				System.out.println(sortPath);
				String sortTyp = "";
				if (rbtnYear.isSelected() == true) {
					sortTyp = "Year";
				}
				if (rbtnMonath.isSelected() == true) {
					sortTyp = "Month";
				}
				if (rbtnDay.isSelected() == true) {
					sortTyp = "Day";
				}

//				Sortierer Sorter = new Sortierer(); // Initialisiert die Klasse "Sortierer"
//				Sorter.SortingImages(rootPath, sortPath, sortTyp); // geht in den Sortierprocess
				
//				Test tester = new Test();
//				tester.SortingImages(rootPath, sortPath, sortTyp);
				
				Main main = new Main();
				main.StartSortiringImages(rootPath, sortPath, sortTyp, cbArchive.getSelectedItem().toString(),cbTreansvermode.getSelectedItem().toString(),cbinvalideData.getSelectedItem().toString());
				
				lblinProcess.setVisible(false);
				lblFinish.setVisible(true);				
			}
		});
		btnStartSort.setBounds(10, 277, 147, 34);
		contentPane.add(btnStartSort);

		/**
		 * Öffnet einen OpenFileDialog für den Ordner der sortierten Bilder
		 */
		JButton btnOpenSortpath = new JButton("Ordner \u00F6ffnen");
		btnOpenSortpath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtSortpath.setText(openfiledialog());
			}
		});
		btnOpenSortpath.setBounds(352, 90, 104, 21);
		contentPane.add(btnOpenSortpath);

		txtSortpath = new JTextField();
		txtSortpath.setText("D:\\Tests\\Sorttest\\ferigsortiert");
		txtSortpath.setColumns(10);
		txtSortpath.setBounds(10, 91, 335, 20);
		contentPane.add(txtSortpath);

		JLabel lblSortpath = new JLabel("Pfad der Sortierten Bilder(zu):");
		lblSortpath.setBounds(10, 75, 335, 14);
		contentPane.add(lblSortpath);

		/**
		 * Öffnet den sortierten Ordner 
		 */
		JButton btnNewButton = new JButton("Sortierten Ordner \u00F6ffnen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Notefolder = txtSortpath.getText();
				Desktop d = Desktop.getDesktop();
				try {
					d.open(new File(Notefolder));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(167, 277, 153, 34);
		contentPane.add(btnNewButton);
		txtRootPath = new JTextField();
		txtRootPath.setText("D:\\Tests\\Sorttest\\Sort");
		txtRootPath.setBounds(10, 44, 335, 20);
		contentPane.add(txtRootPath);
		txtRootPath.setColumns(10);
		
		
	}

	/**
	 * Öffnet einen OpenFileDialog
	 */
	private String openfiledialog() {
		JFileChooser FileChooser = new JFileChooser();
		FileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		FileChooser.showOpenDialog(null);
		String path="";
		try {
			 path = FileChooser.getSelectedFile().getAbsolutePath();
		} catch (Exception e) {
		}
		return path;
	}
}
