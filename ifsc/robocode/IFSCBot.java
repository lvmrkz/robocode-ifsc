package ifsc.robocode;
import robocode.*;
import java.awt.Color;

// IFSCBot - a robot by (Luan, Ana B, Cauã, Sarah)

public class IFSCBot extends Robot {

    public void run() {

        setColors(Color.white, Color.yellow, Color.yellow);

        while(true) {

            turnGunRight(360);
            ahead(100);
            turnRight(45);
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        double angulo =
            getHeading() - getGunHeading() + e.getBearing();

        turnGunRight(angulo);

        double distancia = e.getDistance();

        if (getGunHeat() == 0) {

            if (distancia < 100) {
                fire(3);

            } else if (distancia < 300) {
                fire(2);

            } else {
                fire(1);
            }
        }

        if (distancia < 150) {
            back(50);
        } else {
            ahead(50);
        }

        turnRight(30);
    }

    public void onHitByBullet(HitByBulletEvent e) {

        turnRight(90);
        ahead(100);
    }

    public void onHitWall(HitWallEvent e) {

        back(100);
        turnRight(90);
    }
}