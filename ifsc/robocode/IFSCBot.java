package ifsc.robocode;
import robocode.*;
import java.awt.Color;
import robocode.util.Utils;

// IFSCBot - a robot by (Luan, Ana B, Cauã, Sarah)

public class IFSCBot extends AdvancedRobot {

    public void run() {

        setColors(Color.white, Color.yellow, Color.yellow);

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);

        while (true) {

            setTurnRadarRightRadians(Double.POSITIVE_INFINITY);

            setAhead(150 * moveDirection);

            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        double radarTurn =
            getHeadingRadians()
            + e.getBearingRadians()
            - getRadarHeadingRadians();

        setTurnRadarRightRadians(
            Utils.normalRelativeAngle(radarTurn) * 2
        );

        double absBearing =
            getHeadingRadians() + e.getBearingRadians();

        double gunTurn =
            Utils.normalRelativeAngle(
                absBearing - getGunHeadingRadians()
            );

        setTurnGunRightRadians(gunTurn);

        double distancia = e.getDistance();

        if (getGunHeat() == 0) {

            if (distancia < 120) {
                fire(3);

            } else if (distancia < 300) {
                fire(2);

            } else {
                fire(1);
            }
        }

        if (Math.random() > 0.92) {
            moveDirection *= -1;
        }

        setTurnRight(e.getBearing() + 90);

        if (distancia < 200) {
            setAhead(100 * moveDirection);
        } else {
            setAhead(150 * moveDirection);
        }
    }

    public void onHitByBullet(HitByBulletEvent e) {

        moveDirection *= -1;

        setAhead(150 * moveDirection);

        setTurnRight(60);
    }

    public void onHitWall(HitWallEvent e) {

        moveDirection *= -1;

        setBack(100);
        setTurnRight(90);
    }

    public void onHitRobot(HitRobotEvent e) {

        if (getGunHeat() == 0) {
            fire(3);
        }

        back(50);
    }
}

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