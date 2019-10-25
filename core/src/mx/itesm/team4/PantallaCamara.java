package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
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

class PantallaCamara extends Pantalla{
    private final Inicio juego;
    private OrthographicCamera camera;
    private Viewport vista;
    //Fondo
    private Texture texturaFondo;
    private Texture pausa;

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

    private float Dx=5;

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
        TextureRegionDrawable btnRegresar=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Return_Boton_00.png")));
        TextureRegionDrawable btnRegresarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Return_push_Boton_00.png")));
        ImageButton btnRegreso= new ImageButton(btnRegresar,btnRegresarOprimido);
        btnRegreso.setPosition(ANCHO/2-200,ALTO/2-70);

        //Siguientes Botones
        //boton play
        TextureRegionDrawable btnJugarD=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Play_Boton_00.png")));
        TextureRegionDrawable btnJugarPress=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Play_Push_Boton_00.png")));
        ImageButton btnJugar=new ImageButton(btnJugarD,btnJugarPress);
        btnJugar.setPosition(ANCHO/2,ALTO/2-70);

        //Evento boton regreso
        btnRegreso.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                juego.setScreen(new PantallaNiveles(juego));
            }
        });

        btnJugar.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                estadoJuego= EstadoJuego.JugandoNivel;
                Gdx.input.setInputProcessor(botonesHud);
            }
        });


        botonesPausa.addActor(btnRegreso);
        botonesPausa.addActor(btnJugar);


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
        btnPause.setPosition(camera.position.x-camera.position.x, ALTO-btnPausa.getMinHeight());
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
        texturaFondo = new Texture("Fondo Largo.png");
        pausa=new Texture("Imagenes_Final/Pantallas/Pantalla_Pausa_00.png");
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
        if (estadoJuego==EstadoJuego.Pausa){
            batch.draw(pausa,camera.position.x-pausa.getWidth()+ANCHO/3,camera.position.y-pausa.getHeight()+ALTO/2);
        }
        personaje.draw(batch);

        batch.end();

        if(estadoJuego==estadoJuego.JugandoNivel) {
            botonesHud.draw();
            botonesHud.getActors().get(0).setPosition(botonesHud.getActors().get(0).getX() + Dx, botonesHud.getActors().get(0).getY());
            botonesPausa.getActors().get(0).setPosition(botonesPausa.getActors().get(0).getX() + Dx, botonesPausa.getActors().get(0).getY());
            botonesPausa.getActors().get(1).setPosition(botonesPausa.getActors().get(1).getX() + Dx, botonesPausa.getActors().get(1).getY());
        }
        else{
            botonesPausa.draw();
        }
    }

    private void actualizarPersonaje(float delta) {
        personaje.mover(Dx);
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

}
