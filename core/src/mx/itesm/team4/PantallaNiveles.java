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

class PantallaNiveles implements Screen {
    private final Inicio inicio;

    //camara
    private OrthographicCamera camara;
    private Viewport vista;

    //batch
    private SpriteBatch batch;

    //texturaFondo
    private Texture texturaFondo;

    //escena de juego
    private Stage escenaNiv;

    public PantallaNiveles(Inicio inicio) {
        this.inicio = inicio;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearMenu();



    }

    private void configurarVista() {
        camara=new OrthographicCamera();
        camara.position.set(inicio.ANCHO/2, inicio.ALTO/2, 0);
        camara.update();

        vista= new StretchViewport(inicio.ANCHO,inicio.ALTO, camara);

        batch= new SpriteBatch();
    }

    private void cargarTexturas() {
        texturaFondo =new Texture("Fondo.png");
    }

    private void crearMenu() {
        escenaNiv = new Stage(vista);

        //Boton regresar
        TextureRegionDrawable btnRegresar=new TextureRegionDrawable(new TextureRegion(new Texture("button_regresar.png")));
        TextureRegionDrawable btnRegresarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("button_regresar_pressed.png")));
        ImageButton btnRegreso= new ImageButton(btnRegresar,btnRegresarOprimido);
        btnRegreso.setPosition(0,inicio.ALTO-btnRegresar.getMinHeight());

        //Evento boton
        btnRegreso.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaMenu(inicio));
            }
        });

        //Boton nivel 1
        TextureRegionDrawable botonNivel1=new TextureRegionDrawable(new TextureRegion(new Texture("nivel1.png")));
        TextureRegionDrawable btnNivel1Press= new TextureRegionDrawable(new TextureRegion(new Texture("nivel1_press.png")));
        ImageButton btnNivel1= new ImageButton(botonNivel1,btnNivel1Press);
        btnNivel1.setPosition(100,Inicio.ALTO/2);

        //Evento boton nivel 1
        btnNivel1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaNiv1(inicio));
            }
        });

        //Boton nivel 2
        TextureRegionDrawable botonNivel2=new TextureRegionDrawable(new TextureRegion(new Texture("nivel2.png")));
        TextureRegionDrawable btnNivel2Press= new TextureRegionDrawable(new TextureRegion(new Texture("nivel2_press.png")));
        ImageButton btnNivel2= new ImageButton(botonNivel2,btnNivel2Press);
        btnNivel2.setPosition(600,Inicio.ALTO/2);

        //Evento boton nivel 2
        btnNivel2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaMenu(inicio));
            }
        });

        //Boton nivel 3
        TextureRegionDrawable botonNivel3=new TextureRegionDrawable(new TextureRegion(new Texture("nivel3.png")));
        TextureRegionDrawable btnNivel3Press= new TextureRegionDrawable(new TextureRegion(new Texture("nivel3_press.png")));
        ImageButton btnNivel3= new ImageButton(botonNivel3,btnNivel3Press);
        btnNivel3.setPosition(1000,Inicio.ALTO/2);

        //Evento nivel 3
        btnNivel3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaMenu(inicio));
            }
        });

        //Boton nivel carrera
        TextureRegionDrawable botonNivelCarrera=new TextureRegionDrawable(new TextureRegion(new Texture("carrera.png")));
        TextureRegionDrawable btnNivelCarreraPress= new TextureRegionDrawable(new TextureRegion(new Texture("carrera_press.png")));
        ImageButton btnNivelCarrera= new ImageButton(botonNivelCarrera,btnNivelCarreraPress);
        btnNivelCarrera.setPosition(Inicio.ANCHO/2-btnNivelCarrera.getMinWidth()/2,200);

        //Evento nivel carrera
        btnNivelCarrera.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaMenu(inicio));
            }
        });

        escenaNiv.addActor(btnRegreso);
        escenaNiv.addActor(btnNivel1);
        escenaNiv.addActor(btnNivel2);
        escenaNiv.addActor(btnNivel3);
        escenaNiv.addActor(btnNivelCarrera);

        Gdx.input.setInputProcessor(escenaNiv);
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        //batch escala de acuerdo a la vista
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();

        escenaNiv.draw();

    }

    private void borrarPantalla() {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        vista.update(width,height);
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
