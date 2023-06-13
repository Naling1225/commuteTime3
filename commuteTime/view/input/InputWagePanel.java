package commuteTime.view.input;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton.ToggleButtonModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class InputWagePanel extends JPanel {
    private JTextField wageField;
    private JTextField workingDaysField;
    private JTextField commutingDaysField;
    private ButtonGroup wageTypeGroup;
    private ImageIcon selectedIcon;
    private ImageIcon unselectedIcon;
    
    private String wageType = "시급";

    public InputWagePanel() {
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(15, 50, 15, 50));
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.decode("#CCCCCC")));

        JRadioButton hourlyWageRadioButton = new JRadioButton("시급");
        JRadioButton dailyWageRadioButton = new JRadioButton("일급");
        JRadioButton monthlyWageRadioButton = new JRadioButton("월급");
        JRadioButton annualSalaryRadioButton = new JRadioButton("연봉");

        selectedIcon = new ImageIcon("images/selected.png");
        Image selectedImage = selectedIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        selectedIcon = new ImageIcon(selectedImage);

        unselectedIcon = new ImageIcon("images/unselected.png");
        Image unselectedImage = unselectedIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        unselectedIcon = new ImageIcon(unselectedImage);

        Font radioButtonFont = new Font(Font.DIALOG, Font.BOLD, 14);
        Color unselectedTextColor = Color.decode("#CCCCCC");

        hourlyWageRadioButton.setBackground(Color.WHITE);
        dailyWageRadioButton.setBackground(Color.WHITE);
        monthlyWageRadioButton.setBackground(Color.WHITE);
        annualSalaryRadioButton.setBackground(Color.WHITE);

        hourlyWageRadioButton.setIcon(selectedIcon);
        dailyWageRadioButton.setIcon(unselectedIcon);
        monthlyWageRadioButton.setIcon(unselectedIcon);
        annualSalaryRadioButton.setIcon(unselectedIcon);

        hourlyWageRadioButton.setFont(radioButtonFont);
        dailyWageRadioButton.setFont(radioButtonFont);
        monthlyWageRadioButton.setFont(radioButtonFont);
        annualSalaryRadioButton.setFont(radioButtonFont);

        dailyWageRadioButton.setForeground(unselectedTextColor);
        monthlyWageRadioButton.setForeground(unselectedTextColor);
        annualSalaryRadioButton.setForeground(unselectedTextColor);

        wageTypeGroup = new ButtonGroup();
        wageTypeGroup.add(hourlyWageRadioButton);
        wageTypeGroup.add(dailyWageRadioButton);
        wageTypeGroup.add(monthlyWageRadioButton);
        wageTypeGroup.add(annualSalaryRadioButton);

        topPanel.add(hourlyWageRadioButton);
        topPanel.add(dailyWageRadioButton);
        topPanel.add(monthlyWageRadioButton);
        topPanel.add(annualSalaryRadioButton);

        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 30));

        add(topPanel, BorderLayout.NORTH);

        wageField = new JTextField();
        wageField.setFont(new Font("NotoSans", Font.BOLD, 30));
        wageField.setText("12,000원");
        wageField.setBorder(null);
        wageField.setHorizontalAlignment(JTextField.CENTER);
        JPanel wagePanel = new JPanel(new BorderLayout());
        wagePanel.setBackground(Color.WHITE);
        wagePanel.add(wageField, BorderLayout.CENTER);
        JLabel wageUnitLabel = new JLabel("근로 스케줄");
        wageUnitLabel.setFont(new Font("NotoSans", Font.PLAIN, 17));
        wageUnitLabel.setForeground(new Color(0x5F5F5F));
        wageUnitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wageUnitLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        wagePanel.add(wageUnitLabel, BorderLayout.SOUTH);
        add(wagePanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Change the layout to FlowLayout
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Add uniform padding around the inputPanel

        JPanel workingDaysPanel = new JPanel(new BorderLayout());
        workingDaysPanel.setBackground(Color.WHITE);
        JLabel workingDaysLabel = new JLabel("일일 근로시간");
        workingDaysLabel.setFont(new Font("NotoSans", Font.PLAIN, 17));
        workingDaysLabel.setForeground(new Color(0x5F5F5F));
        workingDaysLabel.setHorizontalAlignment(SwingConstants.CENTER);
        workingDaysLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        workingDaysField = new JTextField(6);
        workingDaysField.setFont(new Font("NotoSans", Font.BOLD, 25));
        workingDaysField.setText("8시간");
        workingDaysField.setBorder(null);
        workingDaysField.setHorizontalAlignment(JTextField.CENTER);
        workingDaysPanel.add(workingDaysLabel, BorderLayout.NORTH);
        workingDaysPanel.add(workingDaysField, BorderLayout.CENTER);

        JPanel commutingDaysPanel = new JPanel(new BorderLayout());
        commutingDaysPanel.setBackground(Color.WHITE);
        JLabel commutingDaysLabel = new JLabel("한달 출근일수");
        commutingDaysLabel.setFont(new Font("NotoSans", Font.PLAIN, 17));
        commutingDaysLabel.setForeground(new Color(0x5F5F5F));
        commutingDaysLabel.setHorizontalAlignment(SwingConstants.CENTER);
        commutingDaysLabel.setBorder(new EmptyBorder(10, 0, 20, 0));
        commutingDaysField = new JTextField(6);
        commutingDaysField.setFont(new Font("NotoSans", Font.BOLD, 25));
        commutingDaysField.setText("20일");
        commutingDaysField.setBorder(null);
        commutingDaysField.setHorizontalAlignment(JTextField.CENTER);
        commutingDaysPanel.add(commutingDaysLabel, BorderLayout.NORTH);
        commutingDaysPanel.add(commutingDaysField, BorderLayout.CENTER);

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setForeground(new Color(0xCCCCCC));
        separator.setPreferredSize(new Dimension(1, 90)); // Adjust the height of the separator as needed

        inputPanel.add(workingDaysPanel);
        inputPanel.add(Box.createHorizontalStrut(10)); // Add horizontal spacing between panels
        inputPanel.add(separator);
        inputPanel.add(Box.createHorizontalStrut(10)); // Add horizontal spacing between panels
        inputPanel.add(commutingDaysPanel);

        add(inputPanel, BorderLayout.SOUTH);

        // Add ActionListener to each radio button
        ActionListener radioButtonItemListener = new RadioButtonItemListener();
        hourlyWageRadioButton.addActionListener(radioButtonItemListener);
        dailyWageRadioButton.addActionListener(radioButtonItemListener);
        monthlyWageRadioButton.addActionListener(radioButtonItemListener);
        annualSalaryRadioButton.addActionListener(radioButtonItemListener);
    }

    public String getWage() {
        String value = wageField.getText();
        return value.replace("원", "").replaceAll(",", "").trim();
    }

    public String getWorkingDays() {
        return workingDaysField.getText();
    }

    public String getCommutingDays() {
        return commutingDaysField.getText();
    }

    public String getWageType() {
    	return wageType;
    }
	
    private class RadioButtonItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Update the icons based on the selected radio button
            JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
            if (selectedRadioButton.isSelected()) {
            	wageType = selectedRadioButton.getText();
                selectedRadioButton.setIcon(selectedIcon);
                selectedRadioButton.setBackground(Color.WHITE);
                selectedRadioButton.setForeground(Color.decode("#000000"));
                for (Enumeration<AbstractButton> buttons = wageTypeGroup.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button != selectedRadioButton) {
                        button.setIcon(unselectedIcon);
                        selectedRadioButton.setBackground(Color.WHITE);
                        button.setForeground(Color.decode("#CCCCCC"));
                    }
                }
            }
        }
    }
}
