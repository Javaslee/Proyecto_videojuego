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

class PantallaCamara extends Pantalla{
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

    private JuegoStage PantallaCamara;


    public PantallaCamara(Inicio juego) {
        this.juego = juego;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearHud();
        crearPersonaje();
        crearPausa();
        crearElementosMundo();


        //Gdx.input.setInputProcessor(new ProcesadorEntrada());
    }

    private void crearElementosMundo() {
        PantallaCamara = new JuegoStage();
    }

    private void crearPausa() {
        botonesPausa= new Stage(vista);
        //boton Regreso Juego
        TextureRegionDrawable btnRegresar=new TextureRegionDrawable(new TextureRegion(new Texture("button_regresar.png")));
        TextureRegionDrawable btnRegresarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("button_regresar_pressed.png")));
        ImageButton btnRegreso= new ImageButton(btnRegresar,btnRegresarOprimido);
        btnRegreso.setPosition(0,ALTO-btnRegresar.getMinHeight());

        //Siguientes Botones

        //Evento boton regreso
        btnRegreso.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                estadoJuego= EstadoJuego.Jugando;
                Gdx.input.setInputProcessor(botonesHud);
            }
        });
        botonesPausa.addActor(btnRegreso);
    }

    private void crearPersonaje() {
        Texture texturaIzAd= new Texture("Imagenes_Final/Personaje_01.png");

        //Texture texturaDeAd= new Texture("Personaje_002.png");
        personaje=new Personaje(texturaIzAd,0,ALTO/2-29);
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
        if(estadoJuego==estadoJuego.JugandoNivel) {
            actualizarPersonaje(delta);
        }
        actualizarCamara();
        borrarPantalla();

        // Batch escala de acuerdo a la vista/camara
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texturaFondo,texturaFondo.getWidth()*(xTexturaFondo - 1),0);
        batch.draw(texturaFondo,texturaFondo.getWidth()*(xTexturaFondoDos - 1),0);

        personaje.draw(batch);
        PantallaCamara.draw();
        PantallaCamara.act();

        batch.end();
        if(estadoJuego==estadoJuego.JugandoNivel) {
            botonesHud.draw();
        }
        else{
            botonesPausa.draw();
        }

    }

    private void actualizarPersonaje(float delta) {
        personaje.mover(1);
    }

    private void actualizarCamara() {
        camera.position.x=personaje.getSprite().getX()+3.7f*personaje.getSprite().getWidth();
        camera.update();
        if(camera.position.x-juego.ANCHO/2>texturaFondo.getWidth()*xTexturaFondo){
            xTexturaFondo+=2;
        }
        if(camera.position.x-juego.ANCHO/2>texturaFondo.getWidth()*xTexturaFondoDos){
            xTexturaFondoDos+=2;
        }

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
