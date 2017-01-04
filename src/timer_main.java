import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class timer_main extends JFrame {
    int i, z, stop;
    JButton button1;
    Timer timer;
    Clip clip_loop, clip_end;
    static timer_main frame;
    public timer_main(int stop) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        super("Timer");
        this.stop = stop;
        createGUI();
    }
    public void createGUI() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        button1 = new JButton();
        ActionListener actionListener = new TestActionListener();
        button1.addActionListener(actionListener);
        Color RED = new Color(255, 0,0);
        button1.setBounds(0, 0, 374, 272);
        button1.setFont(new Font("Arial", Font.PLAIN, 200));
        button1.setFocusPainted(false);



        panel.add(button1);


        button1.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'c'){
                    //button1.setText(String.valueOf(e.getKeyChar()));
                    second second = new second();

                    frame.setVisible(false); //you can't see me!
                    frame.dispose(); //Destroy the JFrame object

                    second.setBounds(600,300,200,120);
                    second.setVisible(true);

                }

            }

        });
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
                    clip_loop.stop();
                    AudioInputStream audioInputStream = null;
                    try {
                        audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("sounds/ding.wav"));
                        clip_end = AudioSystem.getClip();
                        clip_end.open(audioInputStream);
                        clip_end.start();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                    button1.setBackground(RED);

                }

            }
        };

        timer = new Timer(delay, taskPerformer);
        getContentPane().add(panel);
        setPreferredSize(new Dimension(380, 300));


    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new timer_main(60);
                    frame.setIconImage(ImageIO.read(this.getClass().getResource("img/timer-icon-7786.png")));
                } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
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
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("sounds/loop.wav"));
                        clip_loop = AudioSystem.getClip();
                        clip_loop.open(audioInputStream);
                        clip_loop.start();
                        clip_loop.loop((stop / 59) + 1);
                    }
                    timer.start();
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
                    e1.printStackTrace();
                }
            }
            else{
                timer.stop();
                z = 0;
                i = 0;
                clip_loop.stop();
                AudioInputStream audioInputStream = null;
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("sounds/ding.wav"));
                    clip_end = AudioSystem.getClip();
                    clip_end.open(audioInputStream);
                    clip_end.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                    e1.printStackTrace();
                }

                button1.setText("");
            }
        }
    }
}