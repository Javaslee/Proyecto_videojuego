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

class PantallaMenu implements Screen {
    private final Inicio inicio;
    //camara
    private OrthographicCamera camara;
    private Viewport vista;

    //batch
    private SpriteBatch batch;

    //fondo
    private Texture texturaFondo;


    //escena de menu (botones)
    private Stage escenaMenu;


    public PantallaMenu(Inicio inicio) {
        this.inicio=inicio;
    }

    @Override
    public void show() {
        configurarVista();
        cargarTexturas();
        crearMenu();
    }

    private void crearMenu() {
        escenaMenu=new Stage(vista);
        //boton Inicio Juego
        TextureRegionDrawable btnJugar=new TextureRegionDrawable(new TextureRegion(new Texture("button_juego.png")));
        TextureRegionDrawable btnJugarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("button_juego_press.png")));
        ImageButton btnInicioJuego= new ImageButton(btnJugar,btnJugarOprimido);
        btnInicioJuego.setPosition(inicio.ANCHO/2-btnInicioJuego.getWidth()/2,2*inicio.ALTO/3);
        //Siguientes Botones
        //Evento boton
        btnInicioJuego.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaNiveles(inicio));

            }
        });

        TextureRegionDrawable btnAcerDe=new TextureRegionDrawable(new TextureRegion(new Texture("button_acerca-de.png")));
        TextureRegionDrawable btnAcerDeOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("button_acerca-de_pressed.png")));
        ImageButton btnInicioAcerca= new ImageButton(btnAcerDe,btnAcerDeOprimido);
        btnInicioAcerca.setPosition(inicio.ANCHO/2-btnInicioAcerca.getWidth()/2,inicio.ALTO/2);

        btnInicioAcerca.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaAcercaDe(inicio));

            }
        });

        TextureRegionDrawable btnInst=new TextureRegionDrawable(new TextureRegion(new Texture("button_instrucciones.png")));
        TextureRegionDrawable btnInstOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("button_instrucciones-pressed.png")));
        ImageButton btnInicioInst= new ImageButton(btnInst,btnInstOprimido);
        btnInicioInst.setPosition(inicio.ANCHO/2-btnInicioAcerca.getWidth()/2,(inicio.ALTO/2)-150);

        btnInicioInst.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaInstrucciones(inicio));

            }
        });

        escenaMenu.addActor(btnInicioJuego);
        escenaMenu.addActor(btnInicioAcerca);
        escenaMenu.addActor(btnInicioInst);

        Gdx.input.setInputProcessor(escenaMenu);
    }

    private void cargarTexturas() {
        texturaFondo=new Texture("Fondo.png");
    }

    private void configurarVista() {
        camara=new OrthographicCamera();
        camara.position.set(inicio.ANCHO/2, inicio.ALTO/2, 0);
        camara.update();

        vista= new StretchViewport(inicio.ANCHO,inicio.ALTO, camara);

        batch= new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        //batch
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();

        escenaMenu.draw();
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
        dispose();
    }

    @Override
    public void dispose() {
        texturaFondo.dispose();//liberar memoria
    }
}
