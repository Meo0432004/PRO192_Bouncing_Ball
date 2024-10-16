/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boucing_ball;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Le Nhat - CE181840
 */
public class Ball extends GameComponent {

    //khoi tao arrtribute
    private int speedBall = -6;
    private int speedBallX = speedBall;
    private int speedBallY = speedBall;
    private GameComponent paddle;

    //constructor khoi tao x,y,width,height Ball
    public Ball(int xBall, int yBall, int widthBall, int heightBall, GameComponent paddle) {
        super(xBall, yBall, widthBall, heightBall);
        this.paddle = paddle;
    }

    //ve Ball
    @Override
    public void createComponent(JPanel panel, Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(super.getX(), super.getY(), super.getWidth(), super.getHeight());
    }

    //update vi tri Ball
    @Override
    public void updateComponent(JPanel panel, JFrame frame) {

        //neu no bi bay ra khoi man hinh thi cap nhat lai vi tri
        if (super.getX() <= -1) {
            super.setX(5);
            speedBallX = 5;
        } else if (super.getX() + super.getWidth() >= panel.getWidth() + 10) {
            super.setX(panel.getWidth() - 100);
            speedBall = -5;
        }

        if (super.getY() <= -1) {
            super.setY(5);
            speedBallY = 5;
        } else if (super.getY() + super.getHeight() >= panel.getHeight() + 10) {
            super.setY(panel.getHeight() - 160);
            speedBall = -5;
        }

        //cap nhat vi tri Ball theo speed
        super.setX(super.getX() + speedBallX);
        super.setY(super.getY() + speedBallY);

        //neu no va cham vao man hinh thi thay doi huong chuyen dong
        if (super.getX() <= 0 || super.getX() + super.getWidth() >= panel.getWidth()) {
            speedBallX = -speedBallX;
        } else if (super.getY() <= 0 || super.getY() + super.getHeight() + 80 >= panel.getHeight()) {
            speedBallY = -speedBallY;
        }        

        //neu no va cham vao Paddle thi thay doi huong chuyen dong
        if (super.getY() >= paddle.getY() - paddle.getHeight() && super.getX() >= paddle.getX() && super.getX() <= paddle.getX() + paddle.getWidth()) {
            speedBallY = -speedBallY;
        }
    }

    //updateBall theo Obstacle
    public void updateComponent(ObstacleManagement obstacleManager) {
        //duyet qua het Obstacle
        for (Obstacle obstacle : obstacleManager.listObstacle) {
            //neu Ball va cham Obstacle
            if ((super.getX() + super.getWidth() >= obstacle.getxObstacle() && super.getX() <= obstacle.getxObstacle() + obstacle.getWidthObstacle()
                    && super.getY() + super.getHeight() >= obstacle.getyObstacle() && super.getY() + super.getHeight() <= obstacle.getyObstacle() + obstacle.getHeightObstacle())
                    || (super.getX() <= obstacle.getxObstacle() + obstacle.getWidthObstacle() && super.getX() + super.getWidth() >= obstacle.getxObstacle()
                    && super.getY() <= obstacle.getyObstacle() + obstacle.getHeightObstacle() && super.getY() + super.getHeight() >= obstacle.getyObstacle())) {
                //thay doi huong qua Ball
                speedBallY = -speedBallY;
                //remove Obstacle bi va cham
                obstacleManager.listObstacle.remove(obstacle);
                break;
            }
        }
    }
}
