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

	private JPanel contentPane, IndividualNeedPanel, CreditCalculation;
	private JTextField textField;
	private JComboBox maturityComboBox;
	private JLabel monthlyCostIndividualLabel, interestRateIndividualLabel, totalCostIndividualLabel, CreditTypeLabel;

	private int money, interestRate, creditTypeIndex = -1;
	private Banka bankType;

	final String totalCostTextString = "Total Cost: ", monthlyCostTextString = "Monthly Credit Cost: ",
			interestRateTextString = "Interest Rate: ";
	boolean individualFlag = false;
	private JTextField ExchangeAmountTextField;

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
		setBounds(100, 100, 1140, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("BankType");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(422, 125, 115, 29);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		JButton btnNewButton = new JButton("Akbank");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "Akbank");
				IndividualNeedPanel.setVisible(false);
				CreditCalculation.setVisible(false);
				ResetPanels();
				bankType = new Akbank();
			}
		});
		btnNewButton.setBounds(50, 40, 115, 45);
		contentPane.add(btnNewButton);

		JButton btnYapkredi = new JButton("Yapıkredi");
		btnYapkredi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "Yapıkredi");
				IndividualNeedPanel.setVisible(false);
				CreditCalculation.setVisible(false);
				ResetPanels();
				bankType = new YapiKredi();
			}
		});
		btnYapkredi.setBounds(200, 40, 115, 45);
		contentPane.add(btnYapkredi);

		JButton btnHsbc = new JButton("HSBC");
		btnHsbc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "HSBC");
				IndividualNeedPanel.setVisible(false);
				CreditCalculation.setVisible(false);
				ResetPanels();
				bankType = new HSBC();
			}
		});
		btnHsbc.setBounds(350, 40, 115, 45);
		contentPane.add(btnHsbc);

		JButton btnZiraat = new JButton("Ziraat");
		btnZiraat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "Ziraat");
				IndividualNeedPanel.setVisible(false);
				CreditCalculation.setVisible(false);
				ResetPanels();
				bankType = new Ziraat();
			}
		});
		btnZiraat.setBounds(500, 40, 115, 45);
		contentPane.add(btnZiraat);

		JPanel ButtonsPanel = new JPanel();
		ButtonsPanel.setBounds(50, 165, 264, 300);
		contentPane.add(ButtonsPanel);
		ButtonsPanel.setLayout(new GridLayout(5, 1, 0, 0));

		CreditCalculation = new JPanel();
		FormVariables.CreditCalculation = CreditCalculation;
		CreditCalculation.setBounds(350, 165, 438, 348);
		contentPane.add(CreditCalculation);
		CreditCalculation.setLayout(null);

		JComboBox CreditType = new JComboBox();
		CreditType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(creditTypeIndex != CreditType.getSelectedIndex())
					ResetPanels();
				creditTypeIndex = CreditType.getSelectedIndex();
				maturityComboBox.setSelectedItem(0);
				IndividualNeedPanel.setVisible(true);
				switch (creditTypeIndex) {
					case 1:
						CreditTypeLabel.setText("Individual Credit");break;
					case 2:
						CreditTypeLabel.setText("House Credit");break;
					case 3:
						CreditTypeLabel.setText("Vehicle Credit");break;
				}
			}			
		});

		CreditType.setModel(new DefaultComboBoxModel(new String[] {"", "Individual Need", "Housing", "Vehicle"}));
		CreditType.setBounds(171, 13, 147, 25);
		CreditCalculation.add(CreditType);

		IndividualNeedPanel = new JPanel();
		IndividualNeedPanel.setBounds(67, 47, 348, 274);
		CreditCalculation.add(IndividualNeedPanel);
		IndividualNeedPanel.setLayout(null);
		IndividualNeedPanel.setVisible(false);

		CreditTypeLabel = new JLabel("Individual Needs Credit");
		CreditTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CreditTypeLabel.setBounds(10, 11, 228, 26);
		IndividualNeedPanel.add(CreditTypeLabel);

		JLabel lblNewLabel_1_1 = new JLabel("Calculation Type: with the credit amount");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 51, 310, 26);
		IndividualNeedPanel.add(lblNewLabel_1_1);

		textField = new JTextField();
		textField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (textField.getText().length() > 0)
					money = Integer.valueOf(textField.getText());				
				if (money > 1000 && interestRate > 0)
					CalculateGivenIndex(creditTypeIndex, money, interestRate);
				else
					ResetIndividualTexts();
			}
		});
		textField.setBounds(10, 133, 228, 33);
		IndividualNeedPanel.add(textField);
		textField.setColumns(10);

		maturityComboBox = new JComboBox();
		maturityComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interestRate = (int) maturityComboBox.getSelectedItem();
				if (money > 1000 && interestRate > 0)
					CalculateGivenIndex(creditTypeIndex, money, interestRate);
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

		JLabel lblNewLabel_2 = new JLabel("Credit Type:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(77, 11, 84, 25);
		CreditCalculation.add(lblNewLabel_2);
		CreditCalculation.setVisible(false);

		DefineButton(ButtonsPanel);
		
		JPanel panel = new JPanel();
		panel.setBounds(798, 163, 318, 350);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Dollar Exchange");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 10, 87, 38);
		panel.add(lblNewLabel_1);
		
		ExchangeAmountTextField = new JTextField();
		ExchangeAmountTextField.setText("0");
		ExchangeAmountTextField.setBounds(74, 103, 117, 32);
		panel.add(ExchangeAmountTextField);
		ExchangeAmountTextField.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Amount:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 100, 54, 32);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Purchase:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 145, 76, 32);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Sales:");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3_1.setBounds(10, 58, 76, 32);
		panel.add(lblNewLabel_1_3_1);
	}

	private void DefineButton(JPanel ButtonsPanel) {
		JButton IndividualCalculationToolButton = new JButton("Individual Calculation Tool");
		IndividualCalculationToolButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormVariables.CreditCalculation.setVisible(true);				
			}
		});
		ButtonsPanel.add(IndividualCalculationToolButton);

		JButton btnNewButton_3 = new JButton("Coming Soon");
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

	private void CalculateGivenIndex(int index, int money, int interestRate) {
		switch (index) {
		case 1:
			CalculateIndividualNeed(money, interestRate);
			break;
		case 2:
			CalculateHouseCredit(money, interestRate);
			break;
		case 3:
			CalculateVehicleCredit(money, interestRate);
			break;
		}
	}

	private void CalculateVehicleCredit(int money, int interestRate) {
		individualFlag = true;
		totalCostIndividualLabel.setText(totalCostTextString + String.valueOf(String.format("%.20f", bankType.TasitKredisi(money))));
		monthlyCostIndividualLabel.setText(monthlyCostTextString+ String.valueOf(String.format("%.20f", bankType.TasitKredisi(money) / interestRate)));
		interestRateIndividualLabel.setText(interestRateTextString + String.valueOf(bankType.GetInterestRate()));
	}

	private void CalculateHouseCredit(int money, int interestRate) {
		individualFlag = true;
		totalCostIndividualLabel.setText(totalCostTextString + String.valueOf(String.format("%.20f", bankType.KonutKredisi(money))));
		monthlyCostIndividualLabel.setText(monthlyCostTextString+ String.valueOf(String.format("%.20f", bankType.KonutKredisi(money) / interestRate)));
		interestRateIndividualLabel.setText(interestRateTextString + String.valueOf(bankType.GetInterestRate()));
	}

	private void CalculateIndividualNeed(int Money, int interestRate) {
		individualFlag = true;
		totalCostIndividualLabel.setText(totalCostTextString + String.valueOf(String.format("%.20f", bankType.IhtiyacKredisi(money))));
		monthlyCostIndividualLabel.setText(monthlyCostTextString+ String.valueOf(String.format("%.20f", bankType.IhtiyacKredisi(money) / interestRate)));
		interestRateIndividualLabel.setText(interestRateTextString + String.valueOf(bankType.GetInterestRate()));
	}

	private void ResetIndividualTexts() {
		if (individualFlag) {
			totalCostIndividualLabel.setText(totalCostTextString);
			monthlyCostIndividualLabel.setText(monthlyCostTextString);
			interestRateIndividualLabel.setText(interestRateTextString);
			individualFlag = false;
		}
	}
	
	private void ResetPanels() {	
		maturityComboBox.setSelectedItem(0);
		money = interestRate = 0;
		textField.setText("");
		totalCostIndividualLabel.setText(totalCostTextString);
		monthlyCostIndividualLabel.setText(monthlyCostTextString);
		interestRateIndividualLabel.setText(interestRateTextString);
	}
}
