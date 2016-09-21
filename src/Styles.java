import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class Styles {
	public static void configureJLabel(JLabel label, boolean isVisible) {
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Helvetica", Font.BOLD, 24));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setVisible(isVisible);
	}
	
	public static void configureJButton(JButton button, boolean isVisible) {
		button.setFocusPainted(false);// avoids this ugly inner border
		button.setOpaque(true);
		button.setVisible(isVisible);
		button.setBorder(new LineBorder(Color.WHITE, 2, false));
		button.setForeground(Color.WHITE);
		button.setBackground(Globals.BACKGROUND_COLOR_PURPLE);
		button.setPreferredSize(new Dimension(100, 50));
		button.setFont(new Font("Helvetica", Font.PLAIN, 22));
		button.setMargin(new Insets(0, 20, 0, 20));
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				super.mouseEntered(e);
				button.setBackground(Globals.BUTTON_HOVER_COLOR_PURPLE);
			}
			public void mouseExited(MouseEvent e) {
				super.mouseExited(e);
				button.setBackground(Globals.BACKGROUND_COLOR_PURPLE);
			}
			
		});
	}
}
