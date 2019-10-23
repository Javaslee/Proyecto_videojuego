package mx.itesm.team4;

import com.badlogic.gdx.physics.box2d.Body;

public class Runner extends JuegoActor {

    private boolean saltando;

    public Runner(Body body) {
        super(body);
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) enums;
    }

    public void salta() {

        if (!saltando) {
            body.applyLinearImpulse(getUserData().getImpulsoSalto(), body.getWorldCenter(), true);
            saltando = true;
        }

    }

    public void aterriza() {
        saltando = false;
    }
}
