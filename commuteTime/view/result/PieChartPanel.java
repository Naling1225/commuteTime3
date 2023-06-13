package commuteTime.view.result;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PieChartPanel extends JPanel {

    public static JLabel textLabel;
    private int duration;
    private int percentage;
    private static double THIRTY_MIN = 40.3;
    private static double ONE_HOUR = 45.3;
    private static double ONE_HOUR_AND_THIRTY_MIN = 13.1;
    private static double TWO_HOUR = 1.1;
    private static double MORE_THAN_TWO_HOUR = 0.2;

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public PieChartPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g);
                if (textLabel == null) {
                    createTextLabel();
                }
            }
        };
        chartPanel.setOpaque(false); // 배경 투명하게 설정
        chartPanel.setPreferredSize(new Dimension(30, 30)); // 크기를 30x30으로 지정

        JPanel textPanel = new JPanel(new GridLayout(1, 1));
        textPanel.setOpaque(false); // 배경 투명하게 설정

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

        g.setColor(Color.decode("#777777"));
        g.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2, startAngle,
                (int) (-1 * calcPercentage() * 3.6));
    }

    private void createTextLabel() {
        System.out.println("percentage:: " + percentage);
        textLabel = new JLabel(
                "<html><div style='text-align: left; font-size: 12px;'>대한민국 상위" + percentage + " % 입니다.</div></html>");
        textLabel.setForeground(new Color(0x5F5F5F));
        textLabel.setFont(new Font("NotoSans", Font.BOLD, 20));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER); // 텍스트 정렬을 왼쪽으로 변경
        textLabel.setBorder(new EmptyBorder(0, 0, 0, 0)); // 왼쪽 여백 조정
        JPanel textPanel = (JPanel) (this.getParent()).getComponent(1);

        textPanel.removeAll();
        textPanel.add(textLabel);
        textPanel.revalidate();
        textPanel.repaint();
    }

    public int calcPercentage() {
        int oneWayDuration = duration / 2;
        double percentage = 0.0;
        double detailedPercentage = 0.0;

        if (oneWayDuration >= 0 && oneWayDuration < 30) {
            if (oneWayDuration == 0) {
                detailedPercentage = 0.0;
            } else {
                detailedPercentage = (oneWayDuration - 0.0) / 30.0 * 100.0;
            }
            percentage = THIRTY_MIN * detailedPercentage / 100;
        } else if (oneWayDuration >= 30 && oneWayDuration < 60) {
            if (oneWayDuration == 30) {
                detailedPercentage = 0.0;
            } else {
                detailedPercentage = (oneWayDuration - 30.0) / 30.0 * 100.0;
            }
            percentage = THIRTY_MIN + ONE_HOUR * detailedPercentage / 100;
        } else if (oneWayDuration >= 60 && oneWayDuration < 90) {
            if (oneWayDuration == 60) {
                detailedPercentage = 0.0;
            } else {
                detailedPercentage = (oneWayDuration - 60.0) / 30.0 * 100.0;
            }
            percentage = THIRTY_MIN + ONE_HOUR + ONE_HOUR_AND_THIRTY_MIN * detailedPercentage / 100;
        } else if (oneWayDuration >= 90 && oneWayDuration < 120) {
            if (oneWayDuration == 90) {
                detailedPercentage = 0.0;
            } else {
                detailedPercentage = (oneWayDuration - 90.0) / 30.0 * 100.0;
            }
            percentage = THIRTY_MIN + ONE_HOUR + ONE_HOUR_AND_THIRTY_MIN + TWO_HOUR * detailedPercentage / 100;
        } else {
            if (oneWayDuration == 120) {
                detailedPercentage = 0.0;
            } else {
                double infinity = Double.POSITIVE_INFINITY;
                detailedPercentage = (oneWayDuration - 120.0) / infinity * 100.0;
            }
            percentage = THIRTY_MIN + ONE_HOUR + ONE_HOUR_AND_THIRTY_MIN + TWO_HOUR
                    + MORE_THAN_TWO_HOUR * detailedPercentage / 100;
        }
        this.percentage = (int) Math.round(percentage);
        return (int) Math.round(percentage);
    }
}
