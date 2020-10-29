import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import javax.swing.JCheckBoxMenuItem;
import java.util.function.Function;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.AbstractButton;

public class Ex6_5 implements ActionListener {
    static JButton jbt = new JButton("Open my Window of Sports & Entertainment");
    static LaunchFrame frame = new LaunchFrame();
    static MenuWindow window = new MenuWindow("Window of Sports & Entertainment",
                                              200, 100);
    boolean isOpen = false;

    public static void main(String[] args) {
        Ex6_5 listener = new Ex6_5();

        jbt.addActionListener(listener);
        frame.add(jbt);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbt) {
            if (!isOpen) {
                window.setVisible(true);
                isOpen = true;
                jbt.setText("Close my Window of Sports & Entertainment");
            } else {
                window.setVisible(false);
                isOpen = false;
                jbt.setText("Open my Window of Sports & Entertainment");
            }
        }
    }
}


class MenuWindow extends JFrame implements ItemListener, ActionListener {
    JTextField jtf = new JTextField();
    JMenuItem menuItemQuit = new JMenuItem("Quit");

    MenuWindow() {
        this("", 0, 0);
    }

    MenuWindow(String title) {
        this(title, 0, 0);
    }

    MenuWindow(int width, int height) {
        this("", width, height);
    }

    public MenuWindow(String title, int width, int height) {
        JMenu menuSports = new JMenu("Sports");
        JMenu menuEntertain = new JMenu("Entertainment");
        JMenu menuBall = new JMenu("Ball");
        JMenuBar menuBar = new JMenuBar();

        addItem(menuSports, "Running");
        addItem(menuSports, "Jump Rope");
        addItem(menuBall, "Baseball");
        addItem(menuBall, "Basketball");
        addItem(menuBall, "Football");
        addItem(menuBall, "Soccer");
        addItem(menuBall, "Volleyball");
        menuSports.add(menuBall);
        menuItemQuit.addActionListener(new Quit());
        menuItemQuit.setMnemonic(KeyEvent.VK_Q);
        menuItemQuit.setAccelerator(
            KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
        menuSports.add(menuItemQuit);

        JCheckBoxMenuItem sing = new JCheckBoxMenuItem("Singing");
        // // sing.addItemListener(this);
        // menuEntertain.add(sing);

        addItem(menuEntertain, "Singing", JCheckBoxMenuItem::new);
        addItem(menuEntertain, "Dancing", JCheckBoxMenuItem::new);
        addItem(menuEntertain, "Gaming", JCheckBoxMenuItem::new);

        menuBar.add(menuSports);
        menuBar.add(menuEntertain);

        add(jtf, BorderLayout.NORTH);

        setJMenuBar(menuBar);
        setTitle(title);
        setSize(width, height);
        setLocationRelativeTo(Ex6_5.frame);
    }

    private void addItem(JMenu menu, String menuName, Function<String, JMenuItem> constr) {
        JMenuItem menuItem = constr.apply(menuName);

        if (menuItem.getClass() == JMenuItem.class) {
            menuItem.addActionListener(this);
        } else {
            menuItem.addItemListener(this);
        }

        menu.add(menuItem);
    }

    private void addItem(JMenu menu, String menuName) {
        addItem(menu, menuName, JMenuItem::new);
    }

    public void actionPerformed(ActionEvent e) {
        String name = ((AbstractButton) e.getSource()).getText();
        jtf.setText(name + " is selected!");
    }


    // Implementation of java.awt.event.ItemListener

    /**
     * Describe <code>itemStateChanged</code> method here.
     *
     * @param itemEvent an <code>ItemEvent</code> value
     */
    public final void itemStateChanged(final ItemEvent itemEvent) {
        String name = ((AbstractButton) itemEvent.getSource()).getText();
        String prefix = (itemEvent.getStateChange() == ItemEvent.SELECTED)
                ? "" : "de";
        jtf.setText(name + " is " + prefix + "selected!");
    }

}


class Quit implements ActionListener {
    public void actionPerformed(ActionEvent e) {
            System.exit(0);
    }
}


class LaunchFrame extends JFrame {
    LaunchFrame() {
        this("");
    }

    LaunchFrame(String title) {
        setTitle(title);
        setSize(350, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
