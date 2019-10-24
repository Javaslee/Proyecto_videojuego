package mx.itesm.team4;


import com.badlogic.gdx.math.Vector2;

public class EnemigoUserData extends UserData{
    private Vector2 linearVelocity;

    public EnemigoUserData(float width, float height) {
        super(width, height);
        enumsType = Enums.ENEMIGO;
        linearVelocity = Inicio.ENEMY_LINEAR_VELOCITY;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }
}

