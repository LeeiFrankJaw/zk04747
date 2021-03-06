import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.FlowLayout;
// import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Ex6_4 {
    public static void main(String[] args) {
        ComboBoxDemo comboBoxDemo = new ComboBoxDemo();
    }
}


class ComboBoxDemo extends JFrame implements ActionListener {
    final static int WIDTH = 350;
    final static int HEIGHT = 150;
    String[] proList = {"play soccer", "play basketball", "play volleyball"};
    JComboBox<String> jcb = new JComboBox<>(proList);
    JTextField jtf = new JTextField(10);
    int prevIndex = -1;

    ComboBoxDemo() {
        jcb.addActionListener(this);
        jcb.setEditable(true);

        add(jcb);
        add(jtf);

        setSize(WIDTH, HEIGHT);
        getContentPane().setBackground(Color.BLUE);
        setLayout(new FlowLayout());
        // setLayout(new GridLayout(1, 2));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    ComboBoxDemo(String title) {
        this();
        setTitle(title);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jcb) {
            String item = (String) jcb.getSelectedItem();
            int index = jcb.getSelectedIndex();
            if (index == -1) {
                jcb.insertItemAt(item, prevIndex+1);
            } else {
                jtf.setText(item);
                prevIndex = index;
            }
        }
    }

}
