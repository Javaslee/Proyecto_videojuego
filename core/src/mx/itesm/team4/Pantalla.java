package mx.itesm.team4;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Pantalla implements Screen {
    // Atributos disponibles en todas las clases del proyecto
    public static final float ANCHO = 1280;
    public static final float ALTO = 720;
    // Atributos disponibles solo en las subclases
    // Todas las pantallas tienen una cámara y una vista
    protected OrthographicCamera camara;
    protected Viewport vista;
    // Todas las pantallas dibujan algo :)
    protected SpriteBatch batch;

    public Pantalla() {
        // Crea la cámara con las dimensiones del mundo
        camara = new OrthographicCamera(ANCHO, ALTO);
        // En el centro de la pantalla
        camara.position.set(ANCHO / 2, ALTO / 2, 0);
        camara.update();


        // La vista que escala los elementos gráficos
        vista = new StretchViewport(ANCHO, ALTO, camara);
        // El objeto que administra los trazos gráficos
        batch = new SpriteBatch();

    }
    // Borra la pantalla con fondo negro
    protected void borrarPantalla() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
    // Borra la pantalla con el color RGB (r,g,b)
    protected void borrarPantalla(float r, float g, float b) {
        Gdx.gl.glClearColor(r,g,b,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
