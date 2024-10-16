/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boucing_ball;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Nguyen Le Nhat - CE181840
 */
public class GamePlay {

    //khoi tao attribute
    JFrame gamePlayFrame = new JFrame("Game");
    JButton backButton = new JButton("Back");
    JButton pauseButton = new JButton("Pause");

    JPanel panel;
    static Timer timer;
    static boolean checkPause = true;
    static boolean checkWin = false;
    //khoi tao bien ball, paddle, Obstacle
    private GameComponent ball;
    private GameComponent paddle;
    private GameComponent Obstacle;

    //constructor
    public GamePlay() {
    }

    public void createGamePlay() {
        //khoi tao gamePlayFrame
        gamePlayFrame.setBounds(200, 50, 1500, 1000);
        gamePlayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePlayFrame.setLayout(null);
        
        gamePlayFrame.requestFocusInWindow();

        Font largerFont = new Font("Helvetica", Font.PLAIN, 23);
        //khoi tao nut Back
        

        //khoi tao thong so cho paddle, ball, Obstacle
        paddle = new Paddle(gamePlayFrame.getWidth() / 2, gamePlayFrame.getHeight() - 80, 160, 30, gamePlayFrame);
        ball = new Ball(gamePlayFrame.getWidth() / 2, gamePlayFrame.getHeight() - 160, 40, 40, paddle);
        Obstacle = new ObstacleManagement(gamePlayFrame, ball);
        //khoi tao panel, tao anonymous class
        panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                //goi super.paintComponent de xoa di buc tranh cu, va dam bao cap nhat do hoa dien tra trong 1 luong
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon("background.jpg");
                Image background = backgroundIcon.getImage();
                g.drawImage(background, 0, 0, gamePlayFrame.getWidth(), gamePlayFrame.getHeight(), this);

                ball.createComponent(panel, g);
                paddle.createComponent(panel, g);
                Obstacle.createComponent(panel, g);
            }
        };
        panel.setFocusable(true);
        panel.requestFocus();
        panel.setBounds(0, 0, gamePlayFrame.getWidth(), gamePlayFrame.getHeight()+80);
        
        panel.setVisible(true);
        gamePlayFrame.add(panel);
        
        backButton.setBounds(0, 0, 160, 30);
        backButton.setVisible(true);
        backButton.setFont(largerFont);
        backButton.setForeground(Color.black);
        backButton.setBackground(Color.white);
        panel.add(backButton);

        //khoi tao nut Pause
        pauseButton.setBounds(gamePlayFrame.getWidth() - 160, 0, 160, 30);
        pauseButton.setVisible(true);
        pauseButton.setFont(largerFont);
        pauseButton.setForeground(Color.black);
        pauseButton.setBackground(Color.white);
        panel.add(pauseButton);

        Menu menu = new Menu();
        //khoi tao timer
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paddle.updateComponent(panel, gamePlayFrame);
                ball.updateComponent(panel, gamePlayFrame);
                Obstacle.updateComponent(panel, gamePlayFrame);
                if (checkWin == true) {
                    JOptionPane.showMessageDialog(gamePlayFrame, "Winning!");
                    int tmp = Menu.settingSlider.getValue();
                    if (tmp > Menu.highestPoint) {
                        Menu.highestPoint = tmp;
                        menu.WriteFile();
                    }
                    playAgainMenu(menu);
                }
                if (!(Menu.yesPlayEndless.isSelected())) {
                    if (ball.getY() + ball.getHeight() >= panel.getHeight() - 100) {
                        JOptionPane.showMessageDialog(gamePlayFrame, "Lose!! Loser!!");
                        playAgainMenu(menu);
                    }
                }
                panel.repaint();
            }
        });
        timer.start();
        buttonEvent();
        gamePlayFrame.setVisible(true);
    }

    //phuong thuc choi lai
    public void playAgainMenu(Menu menu) {
        menu.playAgainMenu(this);
        timer.stop();
    }

    //phuong thuc xu li su kien Button
    public void buttonEvent() {
        //xu li backButton
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePlayFrame.dispose();
                timer.stop();
                Menu menu = new Menu();
                menu.createMenu();
            }
        });

        //xu li pauseButton
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkPause) {
                    timer.start();
                    checkPause = true;
                    //khi nhan tiep tuc, phai tao lai su kien cho paddle
                    ((Paddle) paddle).createKey(gamePlayFrame);
                } else {
                    timer.stop();
                    checkPause = false;
                }
            }
        });

    }

}
