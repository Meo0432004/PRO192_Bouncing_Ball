/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boucing_ball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Le Nhat - CE181840
 */
public class Obstacle{

    //khoi tao arrtribute
    private int xObstacle;
    private int yObstacle;
    private int widthObstacle;
    private int heightObstacle;

    //constructor khoi tao Obstacle
    public Obstacle(int xObstacle, int yObstacle, int widthObstacle, int heightObstacle) {
        this.xObstacle = xObstacle;
        this.yObstacle = yObstacle;
        this.widthObstacle = widthObstacle;
        this.heightObstacle = heightObstacle;
    }

    //getters/setters
    public int getxObstacle() {
        return xObstacle;
    }

    public void setxObstacle(int xObstacle) {
        this.xObstacle = xObstacle;
    }

    public int getyObstacle() {
        return yObstacle;
    }

    public void setyObstacle(int yObstacle) {
        this.yObstacle = yObstacle;
    }

    public int getWidthObstacle() {
        return widthObstacle;
    }

    public void setWidthObstacle(int widthObstacle) {
        this.widthObstacle = widthObstacle;
    }

    public int getHeightObstacle() {
        return heightObstacle;
    }

    public void setHeightObstacle(int heightObstacle) {
        this.heightObstacle = heightObstacle;
    }

    //ve
    public void draw(Graphics g)
    {
        g.setColor(Color.white);
        g.fillRect(xObstacle, yObstacle, widthObstacle, heightObstacle);
    }
}
