import java.applet.Applet;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Container;

public class Ex6_3 extends Applet {
    MyFrameEx6_3 frame = new MyFrameEx6_3("List Demo");
}


class MyFrameEx6_3 extends JFrame implements ListSelectionListener {
    String[] newsGB = {"Daily Telegraph", "The Financial Times",
                       "The Guardian", "The Times"};
    String[] newsUS = {"New York Times", "USA Today",
                       "Wall Street Journal", "Washington Post"};
    JList jlst1 = new JList<String>(newsGB);
    JList jlst2 = new JList<String>(newsUS);
    // If the number of rows defined here is larger than that set by
    // setVisibleRowCount, then this number will override others due
    // to the use of grid layout.  Hence we use zero here.
    JTextArea jta = new JTextArea(0, 20);
    // Both lists and text components are scrolling-savvy.
    JScrollPane jscrp1 = new JScrollPane(jlst1);
    JScrollPane jscrp2 = new JScrollPane(jlst2);
    JScrollPane jscrp3 = new JScrollPane(jta);

    MyFrameEx6_3() {
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.BLUE);
        contentPane.setLayout(new GridLayout(2, 2));

        jlst1.setVisibleRowCount(3); // Take effect
        jlst1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jlst1.addListSelectionListener(this);
        jlst2.setVisibleRowCount(2); // No effect
        jlst2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jlst2.addListSelectionListener(this);

        add(jscrp1);
        add(jscrp2);
        add(jscrp3);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    MyFrameEx6_3(String title) {
        this();
        setTitle(title);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == jlst1) {
            jta.setText(null);
            Object listValue = ((JList) e.getSource()).getSelectedValue();
            String selectedName = listValue.toString();
            for (int i = 0; i < newsGB.length; i++) {
                if (newsGB[i].equals(selectedName)) {
                    jta.append(selectedName + ": seleted\n");
                }
            }
        } else if (e.getSource() == jlst2) {
            jta.setText(null);
            int[] tempList = jlst2.getSelectedIndices();
            for (int i = 0; i < tempList.length; i++) {
                jta.append(newsUS[tempList[i]] + ": selected\n");
            }
        }
    }
}
