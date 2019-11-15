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

        //Este inputprocessor no dejaba que se mostrara el piso y el rectángulo, falta agregarle sprites :)
        //Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void crearElementosMundo() {
        Pantalla2d = new JuegoStage();
    }




    private void crearHud() {
        botonesHud =new Stage(vista);
        TextureRegionDrawable btnPausa=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pausa_Boton_00.png")));
        ImageButton btnPause= new ImageButton(btnPausa);
        btnPause.setPosition(0, ALTO-btnPausa.getMinHeight());
        //evento boton pausa

        btnPause.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                super.clicked(event,x,y);
                if(estadoJuego==estadoJuego.JugandoNivel){
                    estadoJuego=estadoJuego.Pausa;
                    Gdx.input.setInputProcessor(botonesPausa);
                }
            }
        });
        botonesHud.addActor(btnPause);
      /*  botonesHud.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                personaje.saltar(10);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        Gdx.input.setInputProcessor(botonesHud);*/
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

    private class ProcesadorEntrada implements InputProcessor {
        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            personaje.saltar(50);
            return true;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            personaje.saltar(-50);

            return true;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
