package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static mx.itesm.team4.Inicio.ALTO;

class Pantalla2d extends Pantalla{
    private final Inicio juego;
    private OrthographicCamera camera;
    private Viewport vista;
    //Fondo
    private Texture texturaFondo;

    private SpriteBatch batch;
    //Escena HUD
    private Stage botonesHud;
    private Stage botonesPausa;
    //Mover Fondo
    private float xTexturaFondo = 1;
    private float xTexturaFondoDos =2;

    //Personaje
    private Personaje personaje;
    private EstadoJuego estadoJuego = EstadoJuego.JugandoNivel;

    private JuegoStage Pantalla2d;


    public Pantalla2d(Inicio juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearHud();
        //crearPersonaje();
        crearElementosMundo();

    }

    private void crearElementosMundo() {
        Pantalla2d = new JuegoStage();
    }

    private void crearHud() {

    }


    private void cargarTexturas() {
        texturaFondo = new Texture("Fondo Largo.png");
    }

    private void configurarVista() {
        camera = new OrthographicCamera();
        camera.position.set(juego.ANCHO/2, ALTO/2,0);
        camera.update();

        vista =  new StretchViewport(juego.ANCHO, ALTO, camera);
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta) {

        borrarPantalla();

        // Batch escala de acuerdo a la vista/camara
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        Pantalla2d.draw();
        Pantalla2d.act();


        batch.end();

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
        dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();

    }

}

