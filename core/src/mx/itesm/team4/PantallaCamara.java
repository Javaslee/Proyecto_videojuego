package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
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

class PantallaCamara implements Screen {
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
    private EstadoJuego estadoJuego = EstadoJuego.Jugando;


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
                estadoJuego= EstadoJuego.Pausa;
                Gdx.input.setInputProcessor(botonesPausa);
            }
        });
        botonesHud.addActor(btnPause);
        //botones izquierda-derecha
        //Listeners
        Gdx.input.setInputProcessor(botonesHud);
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
        actualizarCamara();
        borrarPantalla();

        // Batch escala de acuerdo a la vista/camara
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texturaFondo,texturaFondo.getWidth()*(xTexturaFondo - 1),0);
        batch.draw(texturaFondo,texturaFondo.getWidth()*(xTexturaFondoDos - 1),0);
        personaje.draw(batch);

        batch.end();


    }

    private void actualizarCamara() {
        //camera.position.x+= 2;
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