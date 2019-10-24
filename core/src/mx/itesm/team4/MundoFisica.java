package mx.itesm.team4;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class MundoFisica {
    public static World createWorld() {
        return new World(Inicio.WORLD_GRAVITY, true);
    }


    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Inicio.PISO_X, Inicio.PISO_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Inicio.PISO_ANCHO / 2, Inicio.PISO_ALTO / 2);
        body.createFixture(shape, Inicio.PISO_DENSITY);
        body.setUserData(new PisoUserData());
        shape.dispose();
        return body;
    }

    public static Body createRunner(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Inicio.RUNNER_X, Inicio.RUNNER_Y));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Inicio.RUNNER_ANCHO / 2, Inicio.RUNNER_ALTO / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, Inicio.RUNNER_DENSITY);
        body.resetMassData();
        body.setUserData(new RunnerUserData());
        shape.dispose();
        return body;
    }
}
