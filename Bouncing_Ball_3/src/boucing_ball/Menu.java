/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boucing_ball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Nguyen Le Nhat - CE181840
 */
public class Menu extends JPanel {

    //khoi tao arrtribute
    private JFrame menuFrame = new JFrame("Menu");
    private JButton playButton = new JButton("Play");
    private JButton playAgainButton = new JButton("Again");
    private JButton rankPointButton = new JButton("Point");
    private JButton settingButton = new JButton("Setting");
    static JSlider settingSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 5);
    static JRadioButton yesPlayEndless = new JRadioButton("Yes");
    JButton menuExitButton = new JButton("Exit");
    boolean typeExit = false;
    GamePlay gameplay;
    static int highestPoint = 0;

    //khoi tao constructor
    public Menu() {
    }

    //khoi tao menu
    public void createMenu() {
        //menuFrame
        menuFrame.setBounds(200, 50, 1500, 700);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(null);
        //menuFrame.getContentPane().setBackground(Color.black);        

        //font chu
        Font largerFont = new Font("Helvetica", Font.PLAIN, 38);
        //khoi tao button
        playButton.setBounds(400, 450, 160, 40);
        playButton.setVisible(true);
        playButton.setFont(largerFont);
        playButton.setForeground(Color.black);
        playButton.setBackground(Color.white);
        UIButton(playButton);     
        menuFrame.add(playButton);

        settingButton.setBounds(600, 450, 160, 40);
        settingButton.setVisible(true);
        settingButton.setFont(largerFont);
        settingButton.setForeground(Color.black);
        settingButton.setBackground(Color.white);
        UIButton(settingButton);
        menuFrame.add(settingButton);
        

        rankPointButton.setBounds(800, 450, 160, 40);
        rankPointButton.setVisible(true);
        rankPointButton.setFont(largerFont);
        rankPointButton.setForeground(Color.black);
        rankPointButton.setBackground(Color.white);
        UIButton(rankPointButton);
        menuFrame.add(rankPointButton);

        menuExitButton.setBounds(1000, 450, 160, 40);
        menuExitButton.setVisible(true);
        menuExitButton.setFont(largerFont);
        menuExitButton.setForeground(Color.black);
        menuExitButton.setBackground(Color.white);
        UIButton(menuExitButton);
        menuFrame.add(menuExitButton);

        yesPlayEndless.setBounds(400, 570, 100, 50);
        yesPlayEndless.setVisible(true);
        yesPlayEndless.setSelected(true);
        yesPlayEndless.setOpaque(false);
        yesPlayEndless.setForeground(Color.white);
        yesPlayEndless.setFont(largerFont);
        menuFrame.add(yesPlayEndless);

        //khoi tao label
        JLabel showLabel = new JLabel("Endless mode");
        showLabel.setForeground(Color.white);
        showLabel.setBounds(400, 520, 380, 30);
        showLabel.setFont(largerFont);
        menuFrame.add(showLabel);
        
        //picture
        String imagePath = "bouncing_ball_game.jpg";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel pictureLabel = new JLabel(imageIcon);
        pictureLabel.setBounds(270, 100, 1000, 300);
        menuFrame.add(pictureLabel);

        String backgroundPath="background.jpg";
        imageIcon = new ImageIcon(backgroundPath);
        JLabel backgroundLabel = new JLabel(imageIcon);
        backgroundLabel.setBounds(0, 0, menuFrame.getWidth(), menuFrame.getHeight());
        backgroundLabel.setOpaque(false);
        menuFrame.add(backgroundLabel);

        ReadFile();

        menuButtonEvent();
        menuFrame.setVisible(true);
    }

    //menu choi lai
    public void playAgainMenu(GamePlay gameplay) {
        this.gameplay = gameplay;
        typeExit = true;
        menuFrame.setBounds(800, 500, 300, 300);
        menuFrame.setLayout(null);
        
        Font largerFont = new Font("Helvetica", Font.PLAIN, 22);

        JButton backButton = new JButton("Back");
        backButton.setBounds(85, 170, 100, 30);
        backButton.setVisible(true);
        backButton.setFont(largerFont);
        menuFrame.add(backButton);

        playAgainButton.setBounds(85, 30, 100, 30);
        playAgainButton.setVisible(true);
        playAgainButton.setFont(largerFont);
        menuFrame.add(playAgainButton);

        menuExitButton.setBounds(85, 100, 100, 30);
        menuExitButton.setVisible(true);
        menuExitButton.setFont(largerFont);
        menuFrame.add(menuExitButton);
        menuButtonEvent();

        menuFrame.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                gameplay.gamePlayFrame.dispose();
                GamePlay.checkWin = false;
                Menu menu = new Menu();
                menu.createMenu();
            }
        });
    }

    public void createSetting() {
        JFrame settingFrame = new JFrame("Setting");
        settingFrame.setBounds(750, 300, 550, 300);
        settingFrame.setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);
        backButton.setVisible(true);
        settingFrame.add(backButton);

        Font largerFont = new Font("Helvetica", Font.PLAIN, 18);
        JLabel settingLabel = new JLabel("Set the number of Obstacles:");
        settingLabel.setFont(largerFont);
        settingLabel.setBounds(100, 70, 260, 30);
        settingLabel.setVisible(true);
        settingFrame.add(settingLabel);

        settingSlider.setBounds(50, 100, 450, 60);
        settingSlider.setMajorTickSpacing(10);
        settingSlider.setMinorTickSpacing(1);
        settingSlider.setPaintTicks(true);
        settingSlider.setPaintLabels(true);
        settingFrame.add(settingSlider);

        JLabel numberOSettingLabel = new JLabel();
        numberOSettingLabel.setFont(largerFont);
        numberOSettingLabel.setBounds(370, 70, 50, 30);
        numberOSettingLabel.setVisible(true);
        numberOSettingLabel.setText("" + settingSlider.getValue());
        settingFrame.add(numberOSettingLabel);

        settingSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numberOSettingLabel.setText("" + settingSlider.getValue());
            }
        });
        backButton(backButton, settingFrame);
        settingFrame.setVisible(true);
    }

    public void createRankPoint() {
        JFrame rankPointFrame = new JFrame("Point");
        rankPointFrame.setBounds(750, 300, 550, 300);
        rankPointFrame.setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);
        backButton.setVisible(true);
        rankPointFrame.add(backButton);

        JLabel highestPointLabel = new JLabel();
        Font largerFont = new Font("Helvetica", Font.PLAIN, 18);
        JLabel rankPointLabel = new JLabel("Your highest Point");
        rankPointLabel.setBounds(200, 50, 200, 30);
        rankPointLabel.setFont(largerFont);
        rankPointLabel.setVisible(true);
        rankPointFrame.add(rankPointLabel);

        highestPointLabel.setBounds(230, 100, 100, 30);
        highestPointLabel.setVisible(true);
        highestPointLabel.setText("" + highestPoint);
        rankPointFrame.add(highestPointLabel);

        backButton(backButton, rankPointFrame);
        rankPointFrame.setVisible(true);
    }

    public void WriteFile() {
        String outputFile = "Point.txt";
        try {
            FileWriter fw = new FileWriter(outputFile);
            fw.write(Integer.toString(highestPoint));
            fw.close();
        } catch (Exception e) {
        }
    }

    public void ReadFile() {
        String inputFile = "Point.txt";
        try {
            int tmp;
            Scanner sc = new Scanner(new File(inputFile));
            highestPoint = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
        }
    }

    //phuong thuc nhan su kien cua button
    public void menuButtonEvent() {
        //tao su kien ActionListener cho playButton
        //annonymous class ActionListener tao va ghi de phuong thuc actionPerformed
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                GamePlay gameplay = new GamePlay();
                gameplay.createGamePlay();
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                gameplay.gamePlayFrame.dispose();
                gameplay.createGamePlay();
                GamePlay.checkWin = false;
            }
        });

        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSetting();
            }
        });

        rankPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRankPoint();
            }
        });

        menuExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
                if (typeExit) {
                    gameplay.gamePlayFrame.dispose();
                }
            }
        });
    }
    
    public void UIButton(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.yellow); // Thay đổi màu nền của nút khi con chuột đi vào
                //button.setForeground(Color.RED); // Thay đổi màu chữ của nút khi con chuột đi vào
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //button.setBackground(UIManager.getColor("Button.background")); // Khôi phục màu nền gốc của nút
                button.setForeground(Color.black); // Khôi phục màu chữ gốc của nút
                button.setBackground(Color.white);
            }
        });
    }

    public void backButton(JButton backButton, JFrame frame) {
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}
