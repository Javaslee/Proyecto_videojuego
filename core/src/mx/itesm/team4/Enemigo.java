package mx.itesm.team4;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

public class Enemigo extends JuegoActor{
    public Enemigo(Body body) {
        super(body);
    }

    @Override
    public EnemigoUserData getUserData() {
        return (EnemigoUserData) enums;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(getUserData().getLinearVelocity());
    }

}
