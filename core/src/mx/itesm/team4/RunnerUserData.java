package mx.itesm.team4;

import com.badlogic.gdx.math.Vector2;

public class RunnerUserData extends UserData{

    private Vector2 impulsoSalto;

    public RunnerUserData() {
        super();
        impulsoSalto = Inicio.RUNNER_IMPULSO_SALTO;
        enumsType = Enums.RUNNER;
    }

    public Vector2 getImpulsoSalto() {
        return impulsoSalto;
    }

    public void setImpulsoSalto(Vector2 impulsoSalto) {
        this.impulsoSalto = impulsoSalto;
    }
}
