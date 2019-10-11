package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class PantallaCamara implements Screen {
    private final Inicio juego;
    private OrthographicCamera camera;
    private Viewport vista;
    //Fondo
    private Texture texturaFondo;

    private SpriteBatch batch;

    //Mover Fondo
    private float xTexturaFondo = 1;
    private float xTexturaFondoDos =2;


    public PantallaCamara(Inicio juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();

    }

    private void cargarTexturas() {
        texturaFondo = new Texture("Fondo Largo.png");
    }

    private void configurarVista() {
        camera = new OrthographicCamera();
        camera.position.set(juego.ANCHO/2,juego.ALTO/2,0);
        camera.update();

        vista =  new StretchViewport(juego.ANCHO, Inicio.ALTO, camera);
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {
        actualizarCamara();
        borrarPantalla();

        // Batch escala de acuerdo a la vista/camara
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texturaFondo,texturaFondo.getWidth()*(xTexturaFondo - 1),0);
        batch.draw(texturaFondo,texturaFondo.getWidth()*(xTexturaFondoDos - 1),0);

        batch.end();


    }

    private void actualizarCamara() {
        camera.position.x+= 2;
        camera.update();
        if(camera.position.x-juego.ANCHO/2>texturaFondo.getWidth()*xTexturaFondo){
            xTexturaFondo+=2;
        }
        if(camera.position.x-juego.ANCHO/2>texturaFondo.getWidth()*xTexturaFondoDos){
            xTexturaFondoDos+=2;
        }

    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width, height);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        texturaFondo.dispose();

    }
}
