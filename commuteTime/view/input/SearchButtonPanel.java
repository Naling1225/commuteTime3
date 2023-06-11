package commuteTime.view.input;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SearchButtonPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	JButton searchButton;

    public JButton getSearchButton() {
        return searchButton;
    }

    public SearchButtonPanel() {
        setBorder(new EmptyBorder(20, 0, 30, 0));
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.CENTER));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int buttonWidth = (int) screenSize.getWidth() - 100; // Subtracting the padding

        searchButton = new JButton("검색하기");
        searchButton.setFont(new Font("NotoSans", Font.BOLD, 18));
        searchButton.setPreferredSize(new Dimension(450, 50));
        searchButton.setBackground(Color.decode("#EDEDED"));
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        searchButton.setUI(new RoundedButtonUI());

        add(searchButton);
    }

    private static class RoundedButtonUI extends BasicButtonUI {
        private static final int BORDER_RADIUS = 50;

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

            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, BORDER_RADIUS, BORDER_RADIUS);

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

