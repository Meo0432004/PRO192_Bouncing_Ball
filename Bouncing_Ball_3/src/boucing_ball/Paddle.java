/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boucing_ball;

import static boucing_ball.GamePlay.checkPause;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Paddle extends GameComponent {

    //khoi tao arrtribute
    private int speedPaddle = 6;
    private boolean leftKeyPressed = false;
    private boolean rightKeyPressed = false;

    //constructor cap nhat x,y,width,height Paddle
    public Paddle(int xPaddle, int yPaddle, int widthPaddle, int heightPaddle, JFrame frame) {
        super(xPaddle, yPaddle, widthPaddle, heightPaddle);
        createKey(frame);
    }

    /**
     * phuong thuc nhan nut truyen vao tu ban phim
     * @param frame frame cua game
     */
    public void createKey(JFrame frame) {
        //khoi tao KeyListener cho frame
        //khoi tao annonymous class KeyAdapter de thay doi KeyPressed va KeyReleased
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                    leftKeyPressed = true;
                } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                    rightKeyPressed = true;
                } else if (keyCode == KeyEvent.VK_P) {
                    if (!checkPause) {
                        GamePlay.timer.start();
                        checkPause = true;
                        createKey(frame);
                    } else {
                        GamePlay.timer.stop();
                        checkPause = false;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
                    leftKeyPressed = false;
                } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
                    rightKeyPressed = false;
                }
            }
        });
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    //cap nhat vi tri paddle sang trai
    public void moveLeft() {
        int newX = super.getX() - speedPaddle;
        super.setX(newX);
    }

    //cap nhat vi tri paddle sang phai
    public void moveRight() {
        int newX = super.getX() + speedPaddle;
        super.setX(newX);
    }

    //khoi tao paddle
    @Override
    public void createComponent(JPanel panel, Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    //cap nhat vi tri paddle
    @Override
    public void updateComponent(JPanel panel, JFrame frame) {
        // Xử lý di chuyển thanh ngang dựa trên biến leftKeyPressed và rightKeyPressed
        if (leftKeyPressed && super.getX() >= 0) {
            moveLeft();
        } else if (rightKeyPressed && super.getX() + super.getWidth() <= panel.getWidth()) {
            moveRight();
        }
    }
}
