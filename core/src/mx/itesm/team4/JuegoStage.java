package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class JuegoStage extends Stage implements ContactListener {

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private World world;
    private Piso piso;
    private Runner runner;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    private Vector3 touchPoint;

    public JuegoStage() {
        setUpWorld();
        setupCamera();
        setupTouchControlAreas();
        renderer = new Box2DDebugRenderer();
    }

    private void setUpWorld() {
        world = MundoFisica.createWorld();
        world.setContactListener(this);
        setUpPiso();
        setUpRunner();
        createEnemy();
    }



    private void setUpPiso() {
        piso = new Piso(MundoFisica.createGround(world));
        addActor(piso);
    }

    private void setUpRunner() {
        runner = new Runner(MundoFisica.createRunner(world));
        addActor(runner);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            update(body);
        }

        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        //TODO: Implement interpolation
    }

    private void update(Body body) {
        if (!BodyFisica.bodyInBounds(body)) {
            if (BodyFisica.bodyEsEnemigo(body) && !runner.isHit()) {
                createEnemy();
            }
            world.destroyBody(body);
        }
    }

    private void createEnemy() {
        Enemigo enemy = new Enemigo(MundoFisica.createEnemy(world));
        addActor(enemy);
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }
    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyFisica.bodyEsRunner(a) && BodyFisica.bodyEsPiso(b)) ||
                (BodyFisica.bodyEsPiso(a) && BodyFisica.bodyEsRunner(b))) {
            runner.aterriza();
        } else if ((BodyFisica.bodyEsRunner(a) && BodyFisica.bodyEsEnemigo(b)) ||
                (BodyFisica.bodyEsEnemigo(a) && BodyFisica.bodyEsRunner(b))) {
            runner.isHit();
        }

    }
    @Override
    public void endContact(Contact contact) {

    }
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
    public boolean touchDown(int x, int y, int pointer, int button) {

        //Coordenadas específicas
        translateScreenToWorldCoordinates(x, y);
        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            runner.salta();
        } else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
            runner.esquivar();
        }

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if (runner.estaEsquivando()) {
            runner.noEsquivar();
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    private boolean rightSideTouched(float x, float y) {

        return screenRightSide.contains(x, y);
    }

    private boolean leftSideTouched(float x, float y) {
        return screenLeftSide.contains(x, y);
    }

    //funcion para obtener las coordenadas actuales del mundo
    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }


}
