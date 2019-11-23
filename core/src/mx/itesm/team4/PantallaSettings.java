package mx.itesm.team4;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

class PantallaSettings extends Pantalla {
    private final Inicio inicio;

    //diseño de la pantalla

    //fondo
    private Texture texturaFondo;
    //otros fondos

    //Mute/On Mute
    private boolean mute=false;
    //stage
    private Stage botones;

    public PantallaSettings(Inicio inicio) {
        this.inicio = inicio;
    }

    @Override
    public void show() {
        generarFondos();
        generarBotones();
    }

    private void generarBotones() {
        TextureRegionDrawable botonMuteOff=new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pantallas/Musica_Boton_00.png")));
        TextureRegionDrawable btnNMuteOn= new TextureRegionDrawable(new TextureRegion(new Texture("Imagenes_Final/Pantallas/Mute_Boton_00.png")));
        ImageButton btnMute= new ImageButton(botonMuteOff,btnNMuteOn);
        btnMute.setPosition(1000,ALTO/2);

        btnMute.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //INSTRUCCIONES
                if(mute){
                    inicio.getManager()
                }
                else{
                    inicio.getManager().s
                }
            }
        });
    }

    private void generarFondos() {
        texturaFondo=new Texture("Imagenes_Final/Pantallas/Settings_Pantalla_00.png");

    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        //batch escala de acuerdo a la vista
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,0,0);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
