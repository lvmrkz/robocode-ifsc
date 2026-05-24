package ifsc.robocode;
import robocode.*;
import java.awt.Color;

// IFSCBot - a robot by (Luan, Ana B, Cauã, Sarah)

public class IFSCBot extends Robot {

    
    int kills = 0;
    boolean bossMode = false;

    public void run() {

        setColors(Color.white, Color.yellow, Color.yellow);

        while(true) {

            ahead(150);

            turnRight(90);

            turnGunRight(360);

            back(100);

            turnLeft(90);

            
            if(bossMode) {

                turnGunRight(720);

                ahead(250);
            }
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {

        turnGunRight(e.getBearing());

        double distancia = e.getDistance();

        if (getGunHeat() == 0) {

            if (distancia < 100) {

                fire(bossMode ? 3 : 2);

            } else if (distancia < 300) {

                fire(bossMode ? 2.5 : 1.5);

            } else {

                fire(1);
            }
        }

        
        if (Math.random() > 0.5) {

            turnRight(30);

        } else {

            turnLeft(30);
        }

        ahead(50);
    }

    public void onHitByBullet(HitByBulletEvent e) {

        turnRight(45);

        back(20);
    }

    public void onHitWall(HitWallEvent e) {

        back(100);

        if (e.getBearing() > 0) {

            turnLeft(90);

        } else {

            turnRight(90);
        }

        ahead(120);
    }

    
    public void onRobotDeath(RobotDeathEvent e) {

        kills++;

        out.println("Kills: " + kills);

       
        if(kills >= 3 && !bossMode) {

            bossMode = true;

            setColors(Color.black, Color.red, Color.red);

            out.println("=== BOSS MODE ATIVADO ===");
        }
    }
}