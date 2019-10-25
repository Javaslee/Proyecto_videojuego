package mx.itesm.team4;

import com.badlogic.gdx.physics.box2d.Body;

public class Runner extends JuegoActor {

    private boolean esquivando;
    private boolean saltando;
    private boolean chocar;


    public Runner(Body body) {
        super(body);
    }

    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) enums;
    }

    public void salta() {

        if (!saltando || esquivando) {
            body.applyLinearImpulse(getUserData().getImpulsoSalto(), body.getWorldCenter(), true);
            saltando = true;
        }

    }

    public void aterriza() {
        saltando = false;
    }

    public void esquivar() {
        if (!saltando) {
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            esquivando = true;
        }
    }

    public void noEsquivar() {
        esquivando = false;
        body.setTransform(getUserData().getRunningPosition(), 0f);
    }

    public boolean estaEsquivando() {
        return esquivando;
    }

    public void hit() {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        chocar = true;
    }

    public boolean isHit() {
        return chocar;
    }
}
