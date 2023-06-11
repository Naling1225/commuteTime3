package commuteTime.view.input;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainInputFrame extends JFrame {
    private static final long serialVersionUID = 1L;
	InputLocationPanel inputLocationPanel;
    InputWagePanel inputWagePanel;
    SearchButtonPanel searchButtonPanel;

    public MainInputFrame() {
        setTitle("이동 비용 계산기");
        setSize(500, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        inputLocationPanel = new InputLocationPanel();
        contentPane.add(inputLocationPanel, BorderLayout.NORTH);

        inputWagePanel = new InputWagePanel();
        contentPane.add(inputWagePanel, BorderLayout.CENTER);

        searchButtonPanel = new SearchButtonPanel();
        contentPane.add(searchButtonPanel, BorderLayout.SOUTH);
        setResizable(false);
        setVisible(true);
    }

    public double getWage() {
        return Double.parseDouble(inputWagePanel.getWage());
    }
    
    public String getWageType() {
        return inputWagePanel.getWageType();
    }

    public String getDepartureLocation() {
        return inputLocationPanel.getDeparture();
    }

    public String getDestinationLocation() {
        return inputLocationPanel.getDestination();
    }
    
    public String getComputeDay() {
    	return inputWagePanel.getCommutingDays();
    }

	public String getWorkingTime() {
    	return inputWagePanel.getWorkingDays();
    }

    public void setDuration(String duration){
        inputLocationPanel.setDuration(duration);
    }
    public void addSubmitButtonListener(ActionListener listener) {
        searchButtonPanel.getSearchButton().addActionListener(listener);
    }
    public void addRequiredTimeButtonListener(ActionListener listener) {
        inputLocationPanel.getDurationButton().addActionListener(listener);
    }
    
}