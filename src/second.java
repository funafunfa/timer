import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static sun.misc.Version.println;

public class second extends JFrame{
    public second(){
        super("Settings");
        createGUI();
    }
    static JButton button;
    static JTextField textField;
    static JLabel label;
    public static second frame1;
    public void createGUI(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        button = new JButton("Select");
        textField = new JTextField();
        label = new JLabel("Время:");
        ActionListener actionListener = new TestActionListener();
        button.addActionListener(actionListener);
        button.setBounds(45, 58, 100, 20);
        panel.add(button);
        textField.setBounds(17, 35, 150, 20);
        panel.add(textField);
        label.setBounds(75, 10, 150, 20);
        panel.add(label);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(200, 120));
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame1 = new second();
                frame1.pack();
                //frame.requestFocus();
                //frame.setResizable(false);

                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);
            }
        });
    }
    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String text = textField.getText();
            int s = Integer.parseInt(text);


            getFrames()[getFrames().length-1].dispose();
            getFrames()[getFrames().length-1].setVisible(false);
            label.setText(String.valueOf(getFrames().length));
            //label.setText(s + "");
            //frame1.setVisible(false); //you can't see me!
            //frame1.dispose(); //Destroy the JFrame object
            timer_main timer_main= null;
            try {
                timer_main = new timer_main(s);
                timer_main.setBounds(600,300,390,311);
                timer_main.setVisible(true);
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e1) {
                e1.printStackTrace();
            }

        }
    }
}
