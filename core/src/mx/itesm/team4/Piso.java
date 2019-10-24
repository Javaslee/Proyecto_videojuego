package mx.itesm.team4;
import com.badlogic.gdx.physics.box2d.Body;

public class Piso extends JuegoActor {

    public Piso(Body body) {
        super(body);
    }

    @Override
    public PisoUserData getUserData() {
        return (PisoUserData) enums;
    }
}
