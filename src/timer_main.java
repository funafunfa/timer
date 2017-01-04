import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;


public class timer_main extends JFrame {
    int i, z, stop;
    JButton button1;
    Timer timer;
    Clip clip;
    public timer_main(int stop) {
        super("Timer");
        this.stop = stop;
        createGUI();
    }
    public void createGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        button1 = new JButton();
        ActionListener actionListener = new TestActionListener();
        button1.addActionListener(actionListener);
        Color RED = new Color(255, 0,0);
        button1.setBounds(0, 0, 294, 272);
        button1.setFont(new Font("Arial", Font.PLAIN, 200));
        button1.setFocusPainted(false);
        panel.add(button1);

        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                i++;
                button1.setText(i + "");
                if(i == stop){
                    timer.stop();
                    button1.setText("");
                    i = 0;
                    z = 0;
                    button1.setBackground(RED);

                }

            }
        };

        timer = new Timer(delay, taskPerformer);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(300, 300));


    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                timer_main frame = new timer_main(60);
                try {
                    frame.setIconImage(ImageIO.read(new File("img/timer-icon-7786.png")));
                }
                catch (IOException exc) {
                    exc.printStackTrace();
                }
                frame.pack();
                frame.requestFocus();
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }



    public class TestActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            z++;
            Color WHITE = new Color(255,255,255);
            if (z / 2 == 0) {
                button1.setBackground(WHITE);
                try {
                    {
                        String soundName = "img/181148__keweldog__timer-with-ding.wav";
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                        clip = AudioSystem.getClip();
                        clip.open(audioInputStream);
                        clip.start();
                    }
                    timer.start();
                } catch (LineUnavailableException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                }
            }
            else{
                timer.stop();
                z = 0;
                i = 0;
                clip.stop();
                button1.setText("");
            }
        }
    }
}