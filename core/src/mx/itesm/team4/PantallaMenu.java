package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

class PantallaMenu extends Pantalla {
    private final Inicio inicio;
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
        TextureRegionDrawable btnJugar=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pantallas de lose o Victory/Play_Boton_00.png")));
        TextureRegionDrawable btnJugarOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Play_Push_Boton_00.png")));
        ImageButton btnInicioJuego= new ImageButton(btnJugar,btnJugarOprimido);
        btnInicioJuego.setPosition(50,ALTO/2);
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

        //boton acerca de
        TextureRegionDrawable btnAcerDe=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/infromacion_bnt_00.png")));
        TextureRegionDrawable btnAcerDeOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/infromacion_push_Bnt.png")));
        ImageButton btnInicioAcerca= new ImageButton(btnAcerDe,btnAcerDeOprimido);
        btnInicioAcerca.setPosition(300,ALTO/2);

        btnInicioAcerca.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaAcercaDe(inicio));

            }
        });

        //instrucciones boton
        TextureRegionDrawable btnInst=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pantallas/acercade_00.png")));
        TextureRegionDrawable btnInstOprimido= new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pantallas/Acercade_push.png")));
        ImageButton btnInicioInst= new ImageButton(btnInst,btnInstOprimido);
        btnInicioInst.setPosition(700,ALTO/2);

        btnInicioInst.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                inicio.setScreen(new PantallaInstrucciones(inicio));

            }
        });

        //boton settings
        TextureRegionDrawable btnMuteOFF=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pantallas/Musica_Boton_00.png")));
        TextureRegionDrawable btnMuteOn= new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pantallas/Mute_Boton_00.png")));
        TextureRegionDrawable btnMutePress= new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pantallas/Musica_Boton_Push_00.png")));
        ImageButton btnMute= new ImageButton(btnMuteOFF,btnMutePress,btnMuteOn);
        btnMute.setPosition(1000,ALTO/2);

        btnMute.addListener(new ClickListener() {
                                      @Override
                                      public void clicked(InputEvent event, float x, float y) {
                                          super.clicked(event, x, y);
                                          if(inicio.isPlaying()){
                                              inicio.stopMusic();
                                          }
                                          else{
                                              inicio.playMusic();
                                          }
                                      }
                                  });

        escenaMenu.addActor(btnInicioJuego);
        escenaMenu.addActor(btnInicioAcerca);
        escenaMenu.addActor(btnInicioInst);
        escenaMenu.addActor(btnMute);

        Gdx.input.setInputProcessor(escenaMenu);
    }


    private void cargarTexturas() {
        texturaFondo=new Texture("Fondo.png");
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
        borrarPantalla();
        //batch
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();

        escenaMenu.draw();
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
