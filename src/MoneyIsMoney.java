import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.sound.midi.Soundbank;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class MoneyIsMoney extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JComboBox maturityComboBox;
	private JLabel monthlyCostIndividualLabel, interestRateIndividualLabel, totalCostIndividualLabel;

	private int money, interestRate;
	private Banka bankType;
	
	final String totalCostTextString ="Total Cost: ", monthlyCostTextString="Monthly Credit Cost: ", interestRateTextString = "Interest Rate: ";
	boolean individualFlag = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MoneyIsMoney frame = new MoneyIsMoney();
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
	public MoneyIsMoney() {
		setResizable(false);
		setTitle("Money is money");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 922, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("BankType");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(430, 125, 115, 29);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		JButton btnNewButton = new JButton("Akbank");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "Akbank");
				bankType = new Akbank();
			}
		});
		btnNewButton.setBounds(50, 40, 115, 45);
		contentPane.add(btnNewButton);

		JButton btnYapkredi = new JButton("Yapıkredi");
		btnYapkredi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "Yapıkredi");
				bankType = new YapiKredi();
			}
		});
		btnYapkredi.setBounds(200, 40, 115, 45);
		contentPane.add(btnYapkredi);

		JButton btnHsbc = new JButton("HSBC");
		btnHsbc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "HSBC");
				bankType = new HSBC();
			}
		});
		btnHsbc.setBounds(350, 40, 115, 45);
		contentPane.add(btnHsbc);

		JButton btnZiraat = new JButton("Ziraat");
		btnZiraat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "Ziraat");
				bankType = new Ziraat();
			}
		});
		btnZiraat.setBounds(500, 40, 115, 45);
		contentPane.add(btnZiraat);

		JPanel ButtonsPanel = new JPanel();
		ButtonsPanel.setBounds(50, 165, 264, 300);
		contentPane.add(ButtonsPanel);
		ButtonsPanel.setLayout(new GridLayout(5, 1, 0, 0));

		JPanel CreditCalculation = new JPanel();
		FormVariables.CreditCalculation = CreditCalculation;
		CreditCalculation.setBounds(350, 165, 520, 354);
		contentPane.add(CreditCalculation);
		CreditCalculation.setLayout(null);

		JComboBox CreditType = new JComboBox();
		CreditType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer value = CreditType.getSelectedIndex();
				switch (value) {
				case 1:

				default:
					throw new IllegalArgumentException("Unexpected value: " + value);
				}
			}
		});
		CreditType.setModel(new DefaultComboBoxModel(new String[] {"Individual Need", "Housing", "Vehicle"}));
		CreditType.setBounds(74, 11, 147, 25);
		CreditCalculation.add(CreditType);

		JPanel IndividualNeedPanel = new JPanel();
		IndividualNeedPanel.setBounds(67, 47, 348, 274);
		CreditCalculation.add(IndividualNeedPanel);
		IndividualNeedPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Individual Needs Credit");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 11, 228, 26);
		IndividualNeedPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Calculation Type: with the credit amount");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 51, 310, 26);
		IndividualNeedPanel.add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(textField.getText().length() > 0)
					money = Integer.valueOf(textField.getText());
				System.out.println("textField: "+money);
				if (money > 1000 && interestRate > 0)
					CalculateIndividualNeed(money, interestRate);
				else
					ResetIndividualTexts();
			}
		});
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});	
		textField.setBounds(10, 133, 228, 33);
		IndividualNeedPanel.add(textField);
		textField.setColumns(10);

		maturityComboBox = new JComboBox();
		maturityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interestRate = (int) maturityComboBox.getSelectedItem();
				System.out.println("Combobox");
				if (money > 1000 && interestRate > 0)
					CalculateIndividualNeed(money, interestRate);
				else
					ResetIndividualTexts();
			}
		});
		maturityComboBox.setBounds(10, 88, 140, 34);
		IndividualNeedPanel.add(maturityComboBox);
		for (int i = 0; i < 37; i++) {
			maturityComboBox.addItem(i);
		}

		monthlyCostIndividualLabel = new JLabel("Monthly Credit Cost:");
		monthlyCostIndividualLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		monthlyCostIndividualLabel.setBounds(10, 200, 183, 26);
		IndividualNeedPanel.add(monthlyCostIndividualLabel);

		interestRateIndividualLabel = new JLabel("Interest Rate:");
		interestRateIndividualLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		interestRateIndividualLabel.setBounds(10, 177, 183, 26);
		IndividualNeedPanel.add(interestRateIndividualLabel);

		totalCostIndividualLabel = new JLabel("Total Cost:");
		totalCostIndividualLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		totalCostIndividualLabel.setBounds(10, 224, 183, 26);
		IndividualNeedPanel.add(totalCostIndividualLabel);
		CreditCalculation.setVisible(false);

		DefineButton(ButtonsPanel);
	}

	private void DefineButton(JPanel ButtonsPanel) {
		JButton btnNewButton_1 = new JButton("Individual Calculation Tool");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormVariables.CreditCalculation.setVisible(true);
			}
		});
		ButtonsPanel.add(btnNewButton_1);

		JButton btnNewButton_3 = new JButton("Döviz Hesaplama");
		ButtonsPanel.add(btnNewButton_3);

		JButton btnNewButton_2 = new JButton("Coming Soon");
		ButtonsPanel.add(btnNewButton_2);

		JButton btnNewButton_5 = new JButton("Coming Soon");
		ButtonsPanel.add(btnNewButton_5);

		JButton btnNewButton_4 = new JButton("Coming Soon");
		ButtonsPanel.add(btnNewButton_4);
	}

	private void SetMainLabel(JLabel jLabel, String text) {
		jLabel.setText(text);
		jLabel.setVisible(true);
	}

	private void CalculateIndividualNeed(int Money, int interestRate) {
		individualFlag = true;
		System.out.println(bankType);
		totalCostIndividualLabel.setText(totalCostTextString + String.valueOf(String.format("%.20f",bankType.IhtiyacKredisi(money))));
		monthlyCostIndividualLabel.setText(monthlyCostTextString +  String.valueOf(String.format("%.20f",bankType.IhtiyacKredisi(money)/interestRate)));
		interestRateIndividualLabel.setText(interestRateTextString + String.valueOf(bankType.GetInterestRate()));
	}
	
	private void ResetIndividualTexts() {
		if(individualFlag) {
			totalCostIndividualLabel.setText(totalCostTextString);
			monthlyCostIndividualLabel.setText(monthlyCostTextString);
			interestRateIndividualLabel.setText(interestRateTextString);
			individualFlag =false;
		}		
	}
	
}
