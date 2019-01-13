package si.um.opj.bobovnik.ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.TextField;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;
import si.feri.opj.bobovnik.izjeme.MaksimalnaObremenitevPresezenaException;
import si.feri.opj.bobovnik.izjeme.NevarniTovorException;
import si.feri.opj.bobovnik.izjeme.TovorniProstorPolnException;
import si.feri.opj.bobovnik.izjeme.ZabojnikPolnException;
import si.feri.opj.bobovnik.izjeme.ZabojnikPreobremenjenException;
import si.feri.opj.bobovnik.predmeti.Predmet;
import si.feri.opj.bobovnik.prevoz.TovornaLadja;
import si.feri.opj.bobovnik.relacija.Kraj;
import si.feri.opj.bobovnik.relacija.Pot;
import si.feri.opj.bobovnik.tipi.Nevarnost;
import si.feri.opj.bobovnik.zaboji.HladilniZabojnik;
import si.feri.opj.bobovnik.zaboji.NevarniZabojnik;
import si.feri.opj.bobovnik.zaboji.Zabojnik;
import si.feri.opj.bobovnik.prevoz.PrevoznoSredstvo;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PretovoriGUI {

	private JFrame frmPretovori;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private ArrayList<TovornaLadja> seznamLadij = new ArrayList<TovornaLadja>();
    private ArrayList<Zabojnik> seznamZabojnikov = new ArrayList<Zabojnik>();
	private ArrayList<Predmet> seznamPredmetov = new ArrayList<Predmet>();
	private JComboBox<String> comboBox_3 = new JComboBox<String>();
	private JComboBox<String> comboBox_2 = new JComboBox<String>();
	private JComboBox<String> comboBox_6 = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PretovoriGUI window = new PretovoriGUI();
					window.frmPretovori.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PretovoriGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPretovori = new JFrame();
		frmPretovori.getContentPane().setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		frmPretovori.setFont(new Font("Trebuchet MS", Font.PLAIN, 12));
		frmPretovori.setTitle("Pretovori");
		frmPretovori.setBounds(100, 100, 610, 330);
		frmPretovori.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPretovori.getContentPane().setLayout(new CardLayout(0, 0));
		frmPretovori.addWindowListener(new WindowListener() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {	
					ObjectOutputStream zabeleziPredmete = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("seznamPredmetov.ser")));
					zabeleziPredmete.writeObject(seznamPredmetov);
					zabeleziPredmete.flush();
					zabeleziPredmete.close();

					ObjectOutputStream zabeleziZabojnike = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("seznamZabojnikov.ser")));
					zabeleziZabojnike.writeObject(seznamZabojnikov);
					zabeleziZabojnike.flush();
					zabeleziZabojnike.close();

					ObjectOutputStream zabeleziLadje = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("seznamLadij.ser")));
					zabeleziLadje.writeObject(seznamLadij);
					zabeleziLadje.flush();
					zabeleziLadje.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					if((new File("seznamPredmetov.ser").exists())&&(new File("seznamZabojnikov.ser").exists())&&(new File("seznamLadij.ser").exists())) {
						ObjectInputStream povrniPredmete = new ObjectInputStream(new GZIPInputStream(new FileInputStream("seznamPredmetov.ser")));
						seznamPredmetov.clear();
						seznamPredmetov = (ArrayList<Predmet>) povrniPredmete.readObject();						
						for(Predmet i : seznamPredmetov)
							comboBox_3.addItem(i.toString());
						povrniPredmete.close();
						
						ObjectInputStream povrniZabojnike = new ObjectInputStream(new GZIPInputStream(new FileInputStream("seznamZabojnikov.ser")));
						seznamZabojnikov.clear();
						seznamZabojnikov = (ArrayList<Zabojnik>) povrniZabojnike.readObject();
						for(Zabojnik i : seznamZabojnikov)
							comboBox_2.addItem(i.toString());
						povrniZabojnike.close();
						
						ObjectInputStream povrniLadje = new ObjectInputStream(new GZIPInputStream(new FileInputStream("seznamLadij.ser")));
						seznamLadij.clear();	
						seznamLadij = (ArrayList<TovornaLadja>) povrniLadje.readObject();
						for(TovornaLadja i : seznamLadij) {
							comboBox_6.addItem(i.toString());
						}
						povrniLadje.close();
					}
					else {
			            //No file is there, start fresh
			        }
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}
		});

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		frmPretovori.getContentPane().add(tabbedPane, "name_1703331704178334");

		JPanel panel_predmeti = new JPanel();
		tabbedPane.addTab("Predmeti", null, panel_predmeti, null);
		panel_predmeti.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u010Crtna koda predmeta: ");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblNewLabel.setBounds(94, 46, 167, 23);
		panel_predmeti.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(273, 47, 224, 22);
		panel_predmeti.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Naziv predmeta: ");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(94, 83, 167, 21);
		panel_predmeti.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(273, 82, 224, 22);
		panel_predmeti.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Te\u017Ea predmeta: ");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(94, 116, 167, 23);
		panel_predmeti.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(273, 117, 224, 22);
		panel_predmeti.add(textField_2);
		textField_2.setColumns(10);

		
		comboBox_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		comboBox_3.setBounds(131, 200, 315, 40);
		panel_predmeti.add(comboBox_3);

		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		btnNewButton.setBounds(131, 162, 97, 25);
		panel_predmeti.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Predmet novPredmet = new Predmet(textField.getText(), textField_1.getText(), Integer.parseInt(textField_2.getText()));
				seznamPredmetov.add(novPredmet);
				comboBox_3.addItem(novPredmet.toString());
				JOptionPane.showMessageDialog(null, "Predmet " + novPredmet + " uspešno dodan!", "Success", JOptionPane.INFORMATION_MESSAGE);
				System.out.println(seznamPredmetov);
			}				
		});

		JButton btnNewButton_1 = new JButton("Spremeni");
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		btnNewButton_1.setBounds(240, 162, 97, 25);
		panel_predmeti.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izbranPredmet = comboBox_3.getItemAt(comboBox_3.getSelectedIndex());
				for (Predmet i : seznamPredmetov) {
					if (i.toString().compareTo(izbranPredmet)==0) {
						comboBox_3.removeItem(i.toString());
						i.setNazivPredmeta(textField_1.getText());
						i.setTeza(Integer.parseInt(textField_2.getText()));
						comboBox_3.addItem(i.toString());
						JOptionPane.showMessageDialog(null, "Predmet uspešno spremenjen na: " + i + " !", "Success", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				System.out.println(seznamPredmetov);
			}
		});

		JButton btnNewButton_2 = new JButton("Bri\u0161i");
		btnNewButton_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		btnNewButton_2.setBounds(349, 162, 97, 25);
		panel_predmeti.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izbranPredmet = comboBox_3.getItemAt(comboBox_3.getSelectedIndex());
				for (Predmet i : seznamPredmetov) {
					if (i.toString().compareTo(izbranPredmet)==0) {
						comboBox_3.removeItem(i.toString());
						seznamPredmetov.remove(i);
						JOptionPane.showMessageDialog(null, "Predmet " + i + " uspešno izbrisan!", "Success", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				System.out.println(seznamPredmetov);
			}
		});

		JLabel lblNewLabel_3 = new JLabel("Podatki o predmetu");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblNewLabel_3.setBounds(82, 0, 428, 25);
		panel_predmeti.add(lblNewLabel_3);

		JPanel panel_zabojniki = new JPanel();
		tabbedPane.addTab("Zabojniki", null, panel_zabojniki, null);
		panel_zabojniki.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4 = new JLabel("Podatki o zabojniku");
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		panel_zabojniki.add(lblNewLabel_4, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel_zabojniki.add(panel, BorderLayout.WEST);

		JComboBox<Nevarnost> comboBox_5 = new JComboBox<Nevarnost>();
		comboBox_5.setModel(new DefaultComboBoxModel(new Nevarnost[] { Nevarnost.EKSPLOZIVNO, Nevarnost.GORLJIVO,
				Nevarnost.TOKSIÈNO, Nevarnost.ŽIVI_TOVOR }));
		comboBox_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		JLabel lblNewLabel_5 = new JLabel("Oznaka: ");
		lblNewLabel_5.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel.add(lblNewLabel_5);

		textField_3 = new JTextField();
		panel.add(textField_3);
		textField_3.setColumns(8);

		JPanel panel_1 = new JPanel();
		panel_zabojniki.add(panel_1, BorderLayout.EAST);

		JLabel lblNewLabel_6 = new JLabel("Max. te\u017Ea: ");
		lblNewLabel_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_1.add(lblNewLabel_6);

		textField_4 = new JTextField();
		panel_1.add(textField_4);
		textField_4.setColumns(5);

		JPanel panel_2 = new JPanel();
		panel_zabojniki.add(panel_2, BorderLayout.SOUTH);

		JPanel panel_16 = new JPanel();
		panel_2.add(panel_16);

		JLabel lblNewLabel_16 = new JLabel("Tip zabojnika: ");
		panel_16.add(lblNewLabel_16);

		JComboBox<String> comboBox_4 = new JComboBox<String>();
		panel_16.add(comboBox_4);
		comboBox_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "Navadni", "Nevarni", "Hladilni" }));


		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {}));
		comboBox_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		JButton btnNewButton_3 = new JButton("Dodaj");
		btnNewButton_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_2.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izbranTipZabojnika = comboBox_4.getItemAt(comboBox_4.getSelectedIndex());
				Zabojnik novZabojnik;
				if (izbranTipZabojnika.compareTo("Navadni") == 0) {
					novZabojnik = new Zabojnik(textField_3.getText(), Integer.parseInt(textField_4.getText()));
				}
				else if (izbranTipZabojnika.compareTo("Nevarni") == 0) {
					novZabojnik = new NevarniZabojnik(textField_3.getText(), Integer.parseInt(textField_4.getText()), comboBox_5.getItemAt(comboBox_5.getSelectedIndex()));
				}
				else {
					novZabojnik = new HladilniZabojnik(textField_3.getText(), Integer.parseInt(textField_4.getText()), Double.parseDouble(textField_14.getText()));
				}
				seznamZabojnikov.add(novZabojnik);
				comboBox_2.addItem(novZabojnik.toString());
				JOptionPane.showMessageDialog(null, "Zabojnik " + novZabojnik + " uspešno dodan!", "Success", JOptionPane.INFORMATION_MESSAGE);
				System.out.println(seznamZabojnikov);
			}
		});

		/**/
		JButton btnNewButton_4 = new JButton("Odstrani");
		btnNewButton_4.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_2.add(btnNewButton_4);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izbranZabojnik = comboBox_2.getItemAt(comboBox_2.getSelectedIndex());
				for (Zabojnik i : seznamZabojnikov) {
					if (i.toString().compareTo(izbranZabojnik)==0) {
						comboBox_2.removeItem(i.toString());
						seznamZabojnikov.remove(i);
						JOptionPane.showMessageDialog(null, "Zabojnik " + i + " uspešno odstranjen!", "Success", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				System.out.println(seznamZabojnikov);
			}
		});

		JPanel panel_3 = new JPanel();
		panel_zabojniki.add(panel_3, BorderLayout.CENTER);

		JPanel panel_5 = new JPanel();
		panel_3.add(panel_5);

		JLabel label_izberiLadjo = new JLabel("Izberi zabojnik: ");
		label_izberiLadjo.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_5.add(label_izberiLadjo);
		panel_5.add(comboBox_2);

		JPanel panel_6 = new JPanel();
		panel_3.add(panel_6);

		JLabel lblIzberiNevarnost = new JLabel("[Nevarni] Izberi nevarnost: ");
		lblIzberiNevarnost.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_6.add(lblIzberiNevarnost);

		panel_6.add(comboBox_5);

		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);

		JLabel lblTemperatura = new JLabel("[Hladilni]Temperatura: ");
		lblTemperatura.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_4.add(lblTemperatura);

		textField_14 = new JTextField();
		textField_14.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_4.add(textField_14);
		textField_14.setColumns(5);

		/**/
		JPanel panel_ladje = new JPanel();
		tabbedPane.addTab("Ladje", null, panel_ladje, null);
		panel_ladje.setLayout(new BoxLayout(panel_ladje, BoxLayout.X_AXIS));

		JPanel panel_7 = new JPanel();
		panel_7.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_ladje.add(panel_7);

		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8);

		JLabel lblNewLabel_7 = new JLabel("Naziv: ");
		lblNewLabel_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_8.add(lblNewLabel_7);

		textField_5 = new JTextField();
		panel_8.add(textField_5);
		textField_5.setColumns(8);

		JLabel lblNewLabel_8 = new JLabel("Povp. hit.: ");
		lblNewLabel_8.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_8.add(lblNewLabel_8);

		textField_6 = new JTextField();
		panel_8.add(textField_6);
		textField_6.setColumns(8);

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9);

		JLabel lblNewLabel_9 = new JLabel("Max. obr.: ");
		lblNewLabel_9.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_9.add(lblNewLabel_9);

		textField_7 = new JTextField();
		panel_9.add(textField_7);
		textField_7.setColumns(5);

		JLabel lblNewLabel_10 = new JLabel("\u0160irina: ");
		lblNewLabel_10.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_9.add(lblNewLabel_10);

		textField_8 = new JTextField();
		panel_9.add(textField_8);
		textField_8.setColumns(5);

		JLabel lblNewLabel_11 = new JLabel("Dol\u017Eina: ");
		lblNewLabel_11.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_9.add(lblNewLabel_11);

		textField_9 = new JTextField();
		panel_9.add(textField_9);
		textField_9.setColumns(5);

		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10);

		JPanel panel_poti = new JPanel();
		tabbedPane.addTab("Poti", null, panel_poti, null);
		panel_poti.setLayout(null);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		comboBox_1.setBounds(70, 170, 130, 22);
		panel_poti.add(comboBox_1);

		TovornaLadja enaLadja = new TovornaLadja("test", 5, 5, 5, 5);
		

		comboBox_6.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_10.add(comboBox_6);

		JButton btnNewButton_7 = new JButton("Ustvari");
		btnNewButton_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_10.add(btnNewButton_7);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TovornaLadja enaLadja = new TovornaLadja(textField_5.getText(), Integer.parseInt(textField_6.getText()),
						Integer.parseInt(textField_7.getText()), Integer.parseInt(textField_8.getText()),
						Integer.parseInt(textField_9.getText()));
				seznamLadij.add(enaLadja);
				comboBox_6.addItem(enaLadja.toString());
				System.out.println(seznamLadij);
				JOptionPane.showMessageDialog(null, "Tovorna ladja " + enaLadja + " uspešno ustvarjena!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton btnNewButton_8 = new JButton("Spremeni");
		btnNewButton_8.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_10.add(btnNewButton_8);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izbranaLadja = comboBox_6.getItemAt(comboBox_6.getSelectedIndex());
				for (TovornaLadja i : seznamLadij) {
					if (i.toString().compareTo(izbranaLadja)==0) {
						comboBox_6.removeItem(i.toString());
						i.setPovprecnaHitrost(Integer.parseInt(textField_6.getText()));
						i.setMaxObremenitev(Integer.parseInt(textField_7.getText()));
						i.setSirinaPrtljaznegaProstora(Integer.parseInt(textField_8.getText()));
						i.setDolzinaPrtljaznegaProstora(Integer.parseInt(textField_9.getText()));
						i.setVelikostProstora(
								Integer.parseInt(textField_8.getText()) * Integer.parseInt(textField_9.getText()));
						comboBox_6.addItem(i.toString());
						JOptionPane.showMessageDialog(null, "Tovorna ladja uspešno spremenjena na: " + i.toString() + " !", "Success", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				System.out.println(seznamLadij);
			}
		});

		JButton btnNewButton_9 = new JButton("Bri\u0161i");
		btnNewButton_9.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		panel_10.add(btnNewButton_9);
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izbranaLadja = comboBox_6.getItemAt(comboBox_6.getSelectedIndex());
				for (TovornaLadja i : seznamLadij) {
					if (i.toString().compareTo(izbranaLadja)==0) {
						comboBox_6.removeItem(i.toString());
						seznamLadij.remove(i);
						JOptionPane.showMessageDialog(null, "Tovorna ladja " + i + " uspešno izbrisana!", "Success", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
				}
				System.out.println(seznamLadij);
			}
		});

		JLabel lblNewLabel_14 = new JLabel("Datum za\u010Detka: ");
		lblNewLabel_14.setBounds(70, 70, 96, 16);
		panel_poti.add(lblNewLabel_14);
		lblNewLabel_14.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		textField_12 = new JTextField();
		textField_12.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		textField_12.setBounds(178, 67, 116, 22);
		panel_poti.add(textField_12);
		textField_12.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Dol\u017Eina poti: ");
		lblNewLabel_15.setBounds(319, 70, 78, 16);
		panel_poti.add(lblNewLabel_15);
		lblNewLabel_15.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		textField_13 = new JTextField();
		textField_13.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		textField_13.setBounds(409, 67, 116, 22);
		panel_poti.add(textField_13);
		textField_13.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Kraj odhoda: ");
		lblNewLabel_12.setBounds(70, 99, 79, 16);
		panel_poti.add(lblNewLabel_12);
		lblNewLabel_12.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		textField_10 = new JTextField();
		textField_10.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		textField_10.setBounds(178, 96, 116, 22);
		panel_poti.add(textField_10);
		textField_10.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Kraj cilja: ");
		lblNewLabel_13.setBounds(319, 99, 62, 16);
		panel_poti.add(lblNewLabel_13);
		lblNewLabel_13.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		textField_11 = new JTextField();
		textField_11.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		textField_11.setBounds(409, 96, 116, 22);
		panel_poti.add(textField_11);
		textField_11.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(70, 207, 455, 33);
		panel_poti.add(comboBox);
		comboBox.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		JButton btnNewButton_13 = new JButton("Nastavi");
		btnNewButton_13.setBounds(212, 169, 96, 25);
		panel_poti.add(btnNewButton_13);
		btnNewButton_13.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
//		btnNewButton_13.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				Pot krEna = new Pot(new Kraj(textField_10.getText(), textField_15.getText()), new Kraj(textField_11.getText(), textField_16.getText()));
//				krEna.setDatumZacetkaPoti(LocalDate.parse(textField_12.getText()));
//				krEna.setDolzinaPoti(Integer.parseInt(textField_13.getText()));
//				TovornaLadja najdena = new TovornaLadja("test", 5, 5, 5, 5);
//				for (TovornaLadja i : seznamLadij) {
//					if(comboBox_1.getItemAt(comboBox_1.getSelectedIndex()).compareTo(i.toString())==0) {
//						najdena = i;
//						krEna.setPrevoznoSredstvo(najdena);
//						comboBox.addItem(krEna.toString());
//					}
//				}
//				
//				
//				
//				/*
//				seznamLadij.add(enaLadja);
//				comboBox_2.addItem(enaLadja.toString());
//				comboBox_1.addItem(enaLadja.toString());
//				System.out.println(seznamLadij);
//				JOptionPane.showMessageDialog(null, "Tovorna ladja " + enaLadja + " uspešno ustvarjena!", "Success",
//						JOptionPane.INFORMATION_MESSAGE);*/
//			}
//		});

		JButton btnNewButton_14 = new JButton("Spremeni");
		btnNewButton_14.setBounds(319, 169, 96, 25);
		panel_poti.add(btnNewButton_14);
		btnNewButton_14.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		JButton btnNewButton_15 = new JButton("Odstrani");
		btnNewButton_15.setBounds(429, 169, 96, 25);
		panel_poti.add(btnNewButton_15);
		btnNewButton_15.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));

		JLabel lblUpravljanjePotiTovornih = new JLabel("Upravljanje poti tovornih ladij");
		lblUpravljanjePotiTovornih.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpravljanjePotiTovornih.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblUpravljanjePotiTovornih.setBounds(0, 0, 587, 28);
		panel_poti.add(lblUpravljanjePotiTovornih);
		
		JLabel lblDravaOdhoda = new JLabel("Dr\u017Eava odhoda: ");
		lblDravaOdhoda.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		lblDravaOdhoda.setBounds(70, 128, 96, 16);
		panel_poti.add(lblDravaOdhoda);
		
		JLabel lblDravaCilja = new JLabel("Dr\u017Eava cilja: ");
		lblDravaCilja.setBounds(319, 128, 78, 16);
		panel_poti.add(lblDravaCilja);
		
		textField_15 = new JTextField();
		textField_15.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		textField_15.setBounds(178, 125, 116, 22);
		panel_poti.add(textField_15);
		textField_15.setColumns(10);
		
		textField_16 = new JTextField();
		textField_16.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		textField_16.setBounds(409, 125, 116, 22);
		panel_poti.add(textField_16);
		textField_16.setColumns(10);
		
		JLabel lblneusposobljeno = new JLabel("[NEUSPOSOBLJENO]");
		lblneusposobljeno.setHorizontalAlignment(SwingConstants.CENTER);
		lblneusposobljeno.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		lblneusposobljeno.setBounds(0, 24, 587, 33);
		panel_poti.add(lblneusposobljeno);
	}
}
/*
class PoslusalecLadij implements ActionListener {

	private JTextField textField_5, textField_6, textField_7, textField_8, textField_9;
	private ArrayList<TovornaLadja> seznamLadij;
	private TovornaLadja enaLadja;
	private JComboBox<String> comboBox_6;

	public PoslusalecLadij(JTextField textField_5, JTextField textField_6, JTextField textField_7,
			JTextField textField_8, JTextField textField_9, ArrayList<TovornaLadja> seznamLadij, TovornaLadja enaLadja,
			JComboBox<String> comboBox_6) {
		this.textField_5 = textField_5;
		this.textField_6 = textField_6;
		this.textField_7 = textField_7;
		this.textField_8 = textField_8;
		this.textField_9 = textField_9;
		this.seznamLadij = seznamLadij;
		this.enaLadja = enaLadja;
		this.comboBox_6 = comboBox_6;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TovornaLadja enaLadja = new TovornaLadja(textField_5.getText(), Integer.parseInt(textField_6.getText()),
				Integer.parseInt(textField_7.getText()), Integer.parseInt(textField_8.getText()),
				Integer.parseInt(textField_9.getText()));
		seznamLadij.add(enaLadja);
		comboBox_6.addItem(enaLadja.toString());
		System.out.println(seznamLadij);
		JOptionPane.showMessageDialog(null, "Tovorna ladja " + enaLadja + " uspešno ustvarjena!", "Success",
				JOptionPane.INFORMATION_MESSAGE);
	}
}*/