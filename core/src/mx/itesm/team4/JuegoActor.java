package mx.itesm.team4;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class JuegoActor extends Actor {
    protected Body body;
    protected UserData enums;



    public JuegoActor(Body body) {
        this.body = body;
        this.enums = (UserData) body.getUserData();
    }

    public abstract UserData getUserData();

}
