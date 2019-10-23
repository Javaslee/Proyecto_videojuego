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

public class PantallaNiv1 extends Pantalla {

    private final Inicio inicio;
    //fondo
    private Texture texturaFondo;
    //escena Hud()
    private Stage botonesHud;
    //escena pausa
    private Stage botonesPausa;
    //personaje
    private Personaje personaje;

    //pistola
    private Pistola pistola;
    //enemigo
    private Enemigo enemigo;
    //moneda
    private Coin moneda;
    //moto
    private Moto moto;

    //personaje timers animación
    private float timerPaso=0f;
    private float MAX_PASO=0.4f;

    float sourceX = 0;

    //Elementos de box2d

    private JuegoStage PantallaNiv1;

    //estados nuevos


    public PantallaNiv1(Inicio inicio) {
        this.inicio=inicio;
        estadoJuego=estadoJuego.JugandoNivel;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearHud();
        crearPersonaje();
        crearPistola();
        crearEnemigo();
        crearMoneda();
        crearMoto();
        crearPausa();
        crearElementosMundo();
    }

    private void crearElementosMundo() {
        PantallaNiv1 = new JuegoStage();
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
                estadoJuego=EstadoJuego.JugandoNivel;
                Gdx.input.setInputProcessor(botonesHud);
            }
        });

        botonesPausa.addActor(btnRegreso);
    }

    private void crearMoto() {
        Texture texturaMoto=new Texture("moto.png");
        moto=new Moto(texturaMoto, 30,ALTO/2-29);
    }

    private void crearMoneda() {
        Texture texturaCoin=new Texture("Imagenes_Final/Moneda_00.png");
        moneda=new Coin(texturaCoin,ANCHO/5-30,ALTO/2-29);
    }

    private void crearEnemigo() {
        Texture texturaEne=new Texture("Robot.png");
        enemigo=new Enemigo(texturaEne,ANCHO/2-30,ALTO/2-29);
    }

    private void crearPistola() {
        Texture texturaPis=new Texture("pistola.png");
        pistola=new Pistola(texturaPis,ANCHO/3,ALTO/2-29);
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
                if(estadoJuego==estadoJuego.JugandoNivel)
                    estadoJuego=EstadoJuego.Pausa;
                    Gdx.input.setInputProcessor(botonesPausa);
            }
        });
        botonesHud.addActor(btnPause);
        //botones izquierda-derecha
        //Listeners
        botonesHud.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                personaje.saltar(10);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        Gdx.input.setInputProcessor(botonesHud);
    }

    private void cargarTexturas() {
        texturaFondo=new Texture("Fondo Largo.png");
    }

    private void configurarVista() {
        camara=new OrthographicCamera();
        camara.position.set(ANCHO/2, ALTO/2, 0);
        camara.update();

        vista= new StretchViewport(ANCHO,ALTO, camara);

        batch= new SpriteBatch();
    }

    @Override
    public void render(float delta) {

        //actualizar personaje
        if(estadoJuego==estadoJuego.JugandoNivel) {
            atualizarPersonaje(delta);
        }
        borrarPantalla();
        //batch
        personaje.draw(batch);
        //pistola.draw(batch);
        //enemigo.draw(batch);
        //moneda.draw(batch);
        //
        // moto.draw(batch);

        batch.end();
        if(estadoJuego==estadoJuego.JugandoNivel) {
            botonesHud.draw();
        }
        else{
            botonesPausa.draw();
        }
        PantallaNiv1.draw();
        PantallaNiv1.act();

    }

    private void atualizarPersonaje(float delta) {
        timerPaso+=delta;
        personaje.mover(2);
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
        texturaFondo.dispose();//liberar memoria
    }
}
