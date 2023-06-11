package commuteTime.view.result;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PieChartPanel extends JPanel {

    public PieChartPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g);
            }
        };
        chartPanel.setOpaque(false); // 배경 투명하게 설정
        chartPanel.setPreferredSize(new Dimension(30, 30)); // 크기를 30x30으로 지정

        JPanel textPanel = new JPanel(new GridLayout(1, 1));
        textPanel.setOpaque(false); // 배경 투명하게 설정

        JLabel textLabel = new JLabel("<html><div style='text-align: left; font-size: 12px;'>대한민국<br>상위 0.1%</div></html>");
        textLabel.setForeground(new Color(0x5F5F5F));
        textLabel.setFont(new Font("NotoSans", Font.PLAIN, 20));
        textLabel.setHorizontalAlignment(SwingConstants.LEFT); // 텍스트 정렬을 왼쪽으로 변경
        textLabel.setBorder(new EmptyBorder(0, 40, 0, 0)); // 왼쪽 여백 조정
        textPanel.add(textLabel);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());
        layeredPane.add(chartPanel, BorderLayout.CENTER); // 차트를 배경으로 추가
        layeredPane.add(textPanel, BorderLayout.WEST); // 텍스트를 차트 위로 추가

        add(layeredPane, BorderLayout.CENTER);
    }

    private void drawPieChart(Graphics g) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 4 - 1; // 반지름 크기를 조정 (기존 크기의 1/4)

        // Draw default pie chart color
        g.setColor(Color.decode("#C3C3C3"));
        g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, 0, 360);

        // Draw specific part of the pie chart
        int startAngle = 90; // 시작 각도 (시작점 기준 시계 방향으로 증가)
        int arcAngle = (int) (360 * 0.01); // 호의 각도
        g.setColor(Color.decode("#777777"));
        g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle, arcAngle);
    }
}
