package mx.itesm.team4;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class JuegoActor extends Actor {
    protected Body body;
    protected UserData enums;
    protected Rectangle screenRectangle;



    public JuegoActor(Body body) {
        this.body = body;
        this.enums = (UserData) body.getUserData();
        screenRectangle = new Rectangle();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (body.getUserData() != null) {
            updateRectangle();
        } else {
            // This means the world destroyed the body (enemy or runner went out of bounds)
            remove();
        }

    }

    public abstract UserData getUserData();

    private void updateRectangle() {
        screenRectangle.x = transformToScreen(body.getPosition().x - enums.getWidth() / 2);
        screenRectangle.y = transformToScreen(body.getPosition().y - enums.getHeight() / 2);
        screenRectangle.width = transformToScreen(enums.getWidth());
        screenRectangle.height = transformToScreen(enums.getHeight());
    }

    protected float transformToScreen(float n) {
        return Inicio.WORLD_TO_SCREEN * n;
    }


}
