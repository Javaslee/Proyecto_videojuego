package mx.itesm.team4;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

class PantallaSettings extends Pantalla {
    private final Inicio inicio;

    //diseño de la pantalla

    //fondo
    private Texture texturaFondo;
    //otros fondos

    public PantallaSettings(Inicio inicio) {
        this.inicio = inicio;
    }

    @Override
    public void show() {
        generarFondos();
        generarBotones();
    }

    private void generarBotones() {

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
