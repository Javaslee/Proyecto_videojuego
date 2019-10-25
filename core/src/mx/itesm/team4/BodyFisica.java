package mx.itesm.team4;

import com.badlogic.gdx.physics.box2d.Body;


public class BodyFisica {
    public static boolean bodyEsRunner(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getEnumsType() == Enums.RUNNER;
    }

    public static boolean bodyEsPiso(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getEnumsType() == Enums.PISO;
    }

    public static boolean bodyInBounds(Body body) {
        UserData userData = (UserData) body.getUserData();

        switch (userData.getEnumsType()) {
            case RUNNER:
            case ENEMIGO:
                return body.getPosition().x + userData.getWidth() / 2 > 0;
        }

        return true;
    }

    public static boolean bodyEsEnemigo(Body body) {
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getEnumsType() == Enums.ENEMIGO;
    }

}
