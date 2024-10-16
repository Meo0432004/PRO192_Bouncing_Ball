/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boucing_ball;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Le Nhat - CE181840
 */
public class ObstacleManagement extends GameComponent {

    //khoi tao arrtribute
    ArrayList<Obstacle> listObstacle = new ArrayList<>();
    private GameComponent ball;

    //constructor khoi tao Obstacle
    public ObstacleManagement(JFrame frame, GameComponent ball) {

        //vong lap de khoi tao tung Obstacle
        for (int i = 0; i < Menu.settingSlider.getValue(); i++) {
            //so lan thu khoi tao
            int attempt = 0;
            //thong so Obstacle
            int xObstacle, yObstacle, widthObstacle, heightObstacle;
            //vong lap khoi tao va xet su trung lap
            do {
                widthObstacle = 150;
                heightObstacle = 10;
                xObstacle = 100 + (int) (Math.random() * (frame.getWidth() - widthObstacle - 50));
                yObstacle = 100 + (int) (Math.random() * (frame.getHeight() - heightObstacle - 600));
                attempt++;
           } while (isOverlap(xObstacle, yObstacle, widthObstacle, heightObstacle) && attempt < 100);
            listObstacle.add(new Obstacle(xObstacle, yObstacle, widthObstacle, heightObstacle));
        }
        
        this.ball = ball;
    }

    //xet su trung lap
    public boolean isOverlap(int xObstacle, int yObstacle, int widthObstacle, int heightObstacle) {
        if (listObstacle.isEmpty()) {
            return false;
        }
        for (Obstacle o : listObstacle) {
            if (xObstacle <= o.getxObstacle() + o.getWidthObstacle() && xObstacle + widthObstacle >= o.getxObstacle()
                    && yObstacle <= o.getyObstacle() + o.getHeightObstacle() && yObstacle + heightObstacle >= o.getyObstacle()) {
                return true;
            }
        }
        return false;
    }

    //khoi tao Obstacle
    @Override
    public void createComponent(JPanel panel, Graphics g) {
        for (Obstacle o : listObstacle) {
            o.draw(g);
        }
    }

    //cap nhat lai Obstacle
    @Override
    public void updateComponent(JPanel panel, JFrame frame) {
        //down-casting de xet xem Ball co va cham voi Obstacle khong
        ((Ball) ball).updateComponent(this);
        //neu khong con Obstacle thi win
            if(listObstacle.isEmpty()){
                GamePlay.checkWin=true;
            }
    }
}
