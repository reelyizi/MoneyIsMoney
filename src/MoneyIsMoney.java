import java.awt.EventQueue;
import java.io.* ;
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
import javax.lang.model.element.NestingKind;
import javax.sound.midi.Soundbank;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import com.jgoodies.forms.factories.DefaultComponentFactory;

public class MoneyIsMoney extends JFrame {

	private JPanel contentPane, IndividualNeedPanel, CreditCalculation, ExchangePanel;
	private JTextField textField;
	private JComboBox maturityComboBox;
	private JLabel monthlyCostIndividualLabel, interestRateIndividualLabel, totalCostIndividualLabel, CreditTypeLabel,
			SalesLabel, PurchaseLabel, lblSalesAmount, lblPurchaseAmount;

	private int money, interestRate, creditTypeIndex = -1;
	private Banka bankType;

	final String totalCostTextString = "Total Cost: ", monthlyCostTextString = "Monthly Credit Cost: ",
			interestRateTextString = "Interest Rate: ", salesLabelString = "Sales:", purchaseLabelString = "Purchase: ";
	boolean individualFlag = false;
	private JTextField ExchangeTextField;
	private JTextField isimGirmeKutusu;

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
				ResetExchangePanels();
				bankType = new Akbank(18.6510, 18.9610);
				SalesLabel.setText(salesLabelString + bankType.GetSalesRatio());
				PurchaseLabel.setText(purchaseLabelString + bankType.GetPurchaseRatio());
				ExchangePanel.setVisible(true);
			}
		});
		btnNewButton.setBounds(50, 74, 115, 45);
		contentPane.add(btnNewButton);

		JButton btnYapkredi = new JButton("Yapıkredi");
		btnYapkredi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "Yapıkredi");
				IndividualNeedPanel.setVisible(false);
				CreditCalculation.setVisible(false);
				ResetPanels();
				ResetExchangePanels();
				bankType = new YapiKredi(18.4718, 19.0826);
				SalesLabel.setText(salesLabelString + bankType.GetSalesRatio());
				PurchaseLabel.setText(purchaseLabelString + bankType.GetPurchaseRatio());
				ExchangePanel.setVisible(true);
			}
		});
		btnYapkredi.setBounds(199, 74, 115, 45);
		contentPane.add(btnYapkredi);

		JButton btnHsbc = new JButton("HSBC");
		btnHsbc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "HSBC");
				IndividualNeedPanel.setVisible(false);
				CreditCalculation.setVisible(false);
				ResetPanels();
				ResetExchangePanels();
				bankType = new HSBC(18.7498, 18.7836);
				SalesLabel.setText(salesLabelString + bankType.GetSalesRatio());
				PurchaseLabel.setText(purchaseLabelString + bankType.GetPurchaseRatio());
				ExchangePanel.setVisible(true);
			}
		});
		btnHsbc.setBounds(351, 70, 115, 45);
		contentPane.add(btnHsbc);

		JButton btnZiraat = new JButton("Ziraat");
		btnZiraat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetMainLabel(lblNewLabel, "Ziraat");
				IndividualNeedPanel.setVisible(false);
				CreditCalculation.setVisible(false);
				ResetPanels();
				ResetExchangePanels();
				bankType = new Ziraat(18.7286, 19.02095);
				SalesLabel.setText(salesLabelString + bankType.GetSalesRatio());
				PurchaseLabel.setText(purchaseLabelString + bankType.GetPurchaseRatio());
				ExchangePanel.setVisible(true);
			}
		});
		btnZiraat.setBounds(498, 70, 115, 45);
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
				if (creditTypeIndex != CreditType.getSelectedIndex())
					ResetPanels();
				creditTypeIndex = CreditType.getSelectedIndex();
				maturityComboBox.setSelectedItem(0);
				IndividualNeedPanel.setVisible(true);
				switch (creditTypeIndex) {
				case 1:
					CreditTypeLabel.setText("Individual Credit");
					break;
				case 2:
					CreditTypeLabel.setText("House Credit");
					break;
				case 3:
					CreditTypeLabel.setText("Vehicle Credit");
					break;
				}
			}
		});

		CreditType.setModel(new DefaultComboBoxModel(new String[] { "", "Individual Need", "Housing", "Vehicle" }));
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

		ExchangePanel = new JPanel();
		ExchangePanel.setBounds(798, 163, 318, 350);
		contentPane.add(ExchangePanel);
		ExchangePanel.setLayout(null);
		ExchangePanel.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("Dollar Exchange");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 10, 181, 38);
		ExchangePanel.add(lblNewLabel_1);

		ExchangeTextField = new JTextField();
		ExchangeTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				double value = Integer.valueOf(ExchangeTextField.getText());
				if (value > 0) {
					lblSalesAmount.setText("Sales amount: " + String.valueOf(bankType.CalculateSaleExchange(value)));
					lblPurchaseAmount
							.setText("Purchase amount: " + String.valueOf(bankType.CalculatePurchaseExchange(value)));
				} else {
					lblSalesAmount.setText("000");
					lblPurchaseAmount.setText("000");
				}
			}
		});
		ExchangeTextField.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {

			}
		});
		ExchangeTextField.setText("0");
		ExchangeTextField.setBounds(74, 103, 117, 32);
		ExchangePanel.add(ExchangeTextField);
		ExchangeTextField.setColumns(10);

		JLabel lblNewLabel_1_2 = new JLabel("Amount:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 100, 54, 32);
		ExchangePanel.add(lblNewLabel_1_2);

		PurchaseLabel = new JLabel("Purchase:");
		PurchaseLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PurchaseLabel.setBounds(134, 58, 129, 32);
		ExchangePanel.add(PurchaseLabel);

		SalesLabel = new JLabel("Sales:");
		SalesLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		SalesLabel.setBounds(10, 58, 114, 32);
		ExchangePanel.add(SalesLabel);

		lblSalesAmount = new JLabel("Sales Amount:");
		lblSalesAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSalesAmount.setBounds(10, 147, 181, 32);
		ExchangePanel.add(lblSalesAmount);

		lblPurchaseAmount = new JLabel("Purchase Amount:");
		lblPurchaseAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPurchaseAmount.setBounds(10, 187, 181, 32);
		ExchangePanel.add(lblPurchaseAmount);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Isminizi Giriniz");
		lblNewJgoodiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewJgoodiesLabel.setBounds(10, 6, 103, 29);
		contentPane.add(lblNewJgoodiesLabel);
		
		isimGirmeKutusu = new JTextField();
		isimGirmeKutusu.setBounds(111, 10, 103, 26);
		contentPane.add(isimGirmeKutusu);
		isimGirmeKutusu.setColumns(10);
		
		JLabel hosgeldinKutusu = DefaultComponentFactory.getInstance().createTitle("Hosgeldin");
		hosgeldinKutusu.setFont(new Font("Tahoma", Font.PLAIN, 25));
		hosgeldinKutusu.setBounds(351, 10, 139, 60);
		contentPane.add(hosgeldinKutusu);
		
		JLabel isminCikacagiYer = DefaultComponentFactory.getInstance().createTitle("");
		isminCikacagiYer.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JButton isimOnayButonu = new JButton("Click");
		isimOnayButonu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)  {
				String isim;
				isim = isimGirmeKutusu.getText();
				
				isminCikacagiYer.setText(isim);
				
				try {
					dosyaIslemleri();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		isimOnayButonu.setBounds(218, 12, 24, 21);
		contentPane.add(isimOnayButonu);
		
		//JLabel isminCikacagiYer = DefaultComponentFactory.getInstance().createTitle("");
		isminCikacagiYer.setBounds(471, 10, 172, 60);
		contentPane.add(isminCikacagiYer);
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
		totalCostIndividualLabel
				.setText(totalCostTextString + String.valueOf(String.format("%.20f", bankType.TasitKredisi(money))));
		monthlyCostIndividualLabel.setText(monthlyCostTextString
				+ String.valueOf(String.format("%.20f", bankType.TasitKredisi(money) / interestRate)));
		interestRateIndividualLabel.setText(interestRateTextString + String.valueOf(bankType.GetInterestRate()));
	}

	private void CalculateHouseCredit(int money, int interestRate) {
		individualFlag = true;
		totalCostIndividualLabel
				.setText(totalCostTextString + String.valueOf(String.format("%.20f", bankType.KonutKredisi(money))));
		monthlyCostIndividualLabel.setText(monthlyCostTextString
				+ String.valueOf(String.format("%.20f", bankType.KonutKredisi(money) / interestRate)));
		interestRateIndividualLabel.setText(interestRateTextString + String.valueOf(bankType.GetInterestRate()));
	}

	private void CalculateIndividualNeed(int Money, int interestRate) {
		individualFlag = true;
		totalCostIndividualLabel
				.setText(totalCostTextString + String.valueOf(String.format("%.20f", bankType.IhtiyacKredisi(money))));
		monthlyCostIndividualLabel.setText(monthlyCostTextString
				+ String.valueOf(String.format("%.20f", bankType.IhtiyacKredisi(money) / interestRate)));
		interestRateIndividualLabel.setText(interestRateTextString + String.valueOf(bankType.GetInterestRate()));
	}
	
	public void dosyaIslemleri() throws IOException {
		individualFlag = true;
		File file = new File("isimler.txt");
		if(!file.exists()) {
			file.createNewFile();	
		}
		
		String val= isimGirmeKutusu.getText();
		FileWriter fWriter = new FileWriter(file, false);
		BufferedWriter bWriter = new BufferedWriter(fWriter);
		bWriter.write(val);
		bWriter.close();
		
		FileReader fReader = new FileReader(file);
		String line;
		
		BufferedReader bReader = new BufferedReader(fReader);
		bReader.read();
		
		
		
		while ((line = bReader.readLine()) != null) {
			System.out.println(line);
		}
		bReader.close();
		
		
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
	
	private void ResetExchangePanels() {
		ExchangeTextField.setText("");
		lblSalesAmount.setText("Sales amount: ");
		lblPurchaseAmount.setText("Purchase amount: ");
	}
}
