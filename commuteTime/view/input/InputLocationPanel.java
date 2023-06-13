package commuteTime.view.input;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

import commuteTime.TransitAPI;
import commuteTime.view.result.ResetButtonPanel;

public class InputLocationPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    TransitAPI transitAPI;

    private final JTextField departureField;
    private final JTextField destinationField;
    private final JTextField departureTimeField;
    private final JButton durationButton;
    JLabel durationLabel;
    String duration = "";

    public String getDeparture() {
        return departureField.getText();
    }

    public String getDestination() {
        return destinationField.getText();
    }

    public String getDepartureTime() {
        return departureTimeField.getText();
    }

    public JButton getDurationButton() {
        return durationButton;
    }

    public void setDuration(String duration) {
        this.duration = duration;
        durationLabel.setText(duration);
    }

    public InputLocationPanel() {
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        departureField = new JTextField();
        destinationField = new JTextField();
        departureTimeField = new JTextField();

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(new EmptyBorder(15, 90, 35, 90));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        JLabel departureLabel = new JLabel("출발");
        departureLabel.setFont(new Font("NotoSans", Font.PLAIN, 17));
        departureLabel.setForeground(new Color(0x5F5F5F));
        topPanel.add(departureLabel);

        ImageIcon icon = new ImageIcon("../../images/exchangeIcon.png");
        Image scaledImage = icon.getImage().getScaledInstance(75, 35, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(scaledIcon);
        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(iconLabel);
        topPanel.add(Box.createHorizontalGlue());

        JLabel destinationLabel = new JLabel("도착");
        destinationLabel.setFont(new Font("NotoSans", Font.PLAIN, 17));
        destinationLabel.setForeground(new Color(0x5F5F5F));
        topPanel.add(destinationLabel);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        centerPanel.setBackground(Color.WHITE);
        departureField.setFont(new Font("NotoSans", Font.BOLD, 18));
        departureField.setText("덕영대로 924");
        departureField.setHorizontalAlignment(JTextField.CENTER);
        destinationField.setFont(new Font("NotoSans", Font.BOLD, 18));
        destinationField.setText("청파로47길 100");
        destinationField.setHorizontalAlignment(JTextField.CENTER);

        // transitAPI = new TransitAPI(this.getDeparture(),this.getDestination());

        departureField.setBorder(null);
        destinationField.setBorder(null);

        centerPanel.add(departureField);
        centerPanel.add(destinationField);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel departureTimeLabel = new JLabel("출발시간");
        departureTimeLabel.setFont(new Font("NotoSans", Font.PLAIN, 17));
        departureTimeLabel.setForeground(new Color(0x5F5F5F));
        departureTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        departureTimeLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0xCCCCCC)),
                BorderFactory.createEmptyBorder(7, 0, 7, 0)));
        bottomPanel.add(departureTimeLabel, BorderLayout.NORTH);

        // Add spacing between departureTimeLabel and departureTimeField
        bottomPanel.add(Box.createVerticalStrut(30), BorderLayout.CENTER);
        // bottomPanel.add(Box.createVerticalStrut(30), BorderLayout.CENTER);

        // Create a panel to hold the departure time field and duration button
        JPanel departureTimeFieldPanel = new JPanel(new BorderLayout());
        departureTimeFieldPanel.setBackground(Color.WHITE);
        departureTimeFieldPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        departureTimeFieldPanel.add(departureTimeField, BorderLayout.CENTER);

        durationButton = new JButton("소요시간 확인");
        durationButton.setFont(new Font("NotoSans", Font.PLAIN, 14));
        durationButton.setFocusPainted(false);
        durationButton.setBackground(Color.decode("#EDEDED"));
        durationButton.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        durationButton.setUI(new RoundedButtonUI(50));

        departureTimeFieldPanel.add(durationButton, BorderLayout.SOUTH);

        bottomPanel.add(departureTimeFieldPanel, BorderLayout.CENTER);

        durationLabel = new JLabel();
        durationLabel.setText(duration);
        durationLabel.setFont(new Font("NotoSans", Font.BOLD, 18));
        durationLabel.setBorder(new EmptyBorder(15, 0, 0, 0));
        durationLabel.setForeground(new Color(0x5F5F5F));
        durationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(durationLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 (E) HH:mm");
        String currentDateTime = currentTime.format(formatter);
        departureTimeField.setFont(new Font("NotoSans", Font.BOLD, 20));
        departureTimeField.setText(currentDateTime);
        departureTimeField.setBorder(null);
        departureTimeField.setHorizontalAlignment(JTextField.CENTER);
        departureTimeField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(20, 0, 20, 0), // Add top and bottom margin of 20 pixels
                departureTimeField.getBorder()));
        // departureLabel.setBorder(null);
        // bottomPanel.add(departureTimeField, BorderLayout.CENTER);

    }

    private static class RoundedButtonUI extends BasicButtonUI {
        private int borderRadius;

        public RoundedButtonUI(int borderRadius) {
            this.borderRadius = borderRadius;
        }

        @Override
        protected void installDefaults(AbstractButton button) {
            super.installDefaults(button);
            button.setOpaque(false);
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            AbstractButton button = (AbstractButton) c;
            ButtonModel model = button.getModel();
            boolean isPressed = model.isPressed();
            boolean isRollover = model.isRollover();

            int width = button.getWidth();
            int height = button.getHeight();

            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, borderRadius,
                    borderRadius);

            if (isPressed) {
                g2.setColor(button.getBackground().darker());
            } else if (isRollover) {
                g2.setColor(button.getBackground().brighter());
            } else {
                g2.setColor(button.getBackground());
            }

            g2.fill(roundedRectangle);

            super.paint(g2, button);
            g2.dispose();
        }
    }
}