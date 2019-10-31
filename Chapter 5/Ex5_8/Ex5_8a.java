import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Describe class Ex5_8a here.
 *
 *
 * Created: Tue Oct 29 17:18:15 2019
 *
 * @author <a href="mailto:leizh26@gmail.com">Lei Zhao</a>
 * @version 1.0
 */
public final class Ex5_8a extends JFrame implements ActionListener {

    static Dimension dim = new Dimension(400, 400);
    static Dimension vSpacerDim = new Dimension(400, 100);
    static JTextField text1 = new JTextField(10);
    static JTextField text2 = new JTextField(10);
    static String title = "Example 8 from Chapter";
    // private static Ex5_8a ex5_8a = new Ex5_8a();

    /**
     * Creates a new <code>Ex5_8a</code> instance.
     *
     */
    private Ex5_8a() {
        super(title);
        JPanel panel = new JPanel();
        JLabel vSpacerPS = new JLabel();
        JLabel vSpacerPE = new JLabel();
        vSpacerPS.setPreferredSize(vSpacerDim);
        vSpacerPE.setPreferredSize(vSpacerDim);
        text1.addActionListener(this);
        text2.addActionListener(this);
        ((FlowLayout) panel.getLayout()).setHgap(20);
        panel.add(text1);
        panel.add(text2);
        add(vSpacerPS, BorderLayout.PAGE_START);
        add(panel);
        add(vSpacerPE, BorderLayout.PAGE_END);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * Describe <code>main</code> method here.
     *
     * @param args a <code>String</code> value
     */
    public static void main(final String[] args) {
        new Ex5_8a().setVisible(true);
    }

    // Implementation of java.awt.event.ActionListener

    /**
     * Describe <code>actionPerformed</code> method here.
     *
     * @param actionEvent an <code>ActionEvent</code> value
     */
    public void actionPerformed(final ActionEvent actionEvent) {
        Object srcObj = actionEvent.getSource();

        if (! (srcObj instanceof JTextField)) {
            return;
        }

        JTextField src = (JTextField) srcObj;
        double num = Double.parseDouble(src.getText());

        if (src == text1) {
            text2.setText(String.format("%.7f", num * num));
        } else if (src == text2) {
            text1.setText(String.format("%.7f", Math.sqrt(num)));
        }
    }

}
