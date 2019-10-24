package mx.itesm.team4;

import com.badlogic.gdx.math.Vector2;

public class RunnerUserData extends UserData{

    private Vector2 impulsoSalto;
    private final Vector2 runningPosition = new Vector2(Inicio.RUNNER_X, Inicio.RUNNER_Y);
    private final Vector2 dodgePosition = new Vector2(Inicio.RUNNER_ESQUIVAR_X, Inicio.RUNNER_ESQUIVAR_Y);


    public RunnerUserData(float width, float height) {
        super(width, height);
        impulsoSalto = Inicio.RUNNER_IMPULSO_SALTO;
        enumsType = Enums.RUNNER;
    }

    public Vector2 getImpulsoSalto() {
        return impulsoSalto;
    }

    public void setImpulsoSalto(Vector2 impulsoSalto) {
        this.impulsoSalto = impulsoSalto;
    }

    public float getDodgeAngle() {
        // In radians
        return (float) (-90f * (Math.PI / 180f));
    }

    public Vector2 getRunningPosition() {
        return runningPosition;
    }

    public Vector2 getDodgePosition() {
        return dodgePosition;
    }

    public float getHitAngularImpulse() {
        return Inicio.RUNNER_HIT_ANGULAR_IMPULSE;
    }
}
