
package boucing_ball;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Le Nhat - CE181840
 */
public abstract class GameComponent {
    //khoi tao attribute
    private int x;
    private int y;
    private int width;
    private int height;

    //constructor
    public GameComponent() {
    }
    
    //constructor khoi tao
    public GameComponent(int x, int y, int width, int height) {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    //setters/getters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    //phuong thuc truu tuong createComponent
    public abstract void createComponent(JPanel panel,Graphics g);
    
//phuong thuc truu tuong updateComponent
    public abstract void updateComponent(JPanel panel, JFrame frame);
}
