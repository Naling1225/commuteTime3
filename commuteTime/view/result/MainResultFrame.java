package commuteTime.view.result;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainResultFrame extends JFrame {

    private static final long serialVersionUID = 1L;
	ExchangeResultPanel exchangeResultPanel;
    PieChartPanel pieChartPanel;
    ResetButtonPanel resetButtonPanel;
    JFrame parent;
    public MainResultFrame(JFrame parent) {
    	this.parent = parent;
        setTitle("이동 비용 계산기");
        setSize(500, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        exchangeResultPanel = new ExchangeResultPanel();
        contentPane.add(exchangeResultPanel, BorderLayout.NORTH);

        pieChartPanel = new PieChartPanel();
        contentPane.add(pieChartPanel, BorderLayout.CENTER);

        resetButtonPanel = new ResetButtonPanel(this, parent);
        contentPane.add(resetButtonPanel, BorderLayout.SOUTH);
        
        setResizable(false);
    }
    
    public void setPosition(String start, String end) {
    	exchangeResultPanel.setStartPosition(start);
    	exchangeResultPanel.setEndPosition(end);
    }
    
    public void setTime(String time) {
    	exchangeResultPanel.setTime(time);
    }
    
    public void setCost(int cost) {
    	exchangeResultPanel.setCost(cost);
    }

}
