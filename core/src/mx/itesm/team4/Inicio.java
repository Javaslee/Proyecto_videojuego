package mx.itesm.team4;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Inicio extends Game {
	public static final float ANCHO= 1280;
	public static final float ALTO= 720;
	public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

	public static final float PISO_X = 0;
	public static final float PISO_Y = 0;
	public static final float PISO_ANCHO = 50f;
	public static final float PISO_ALTO = 4f;
	public static final float PISO_DENSITY = 0f;

    public static final float RUNNER_DENSITY = 0.5f;
    public static final float RUNNER_HEIGHT = 2f;
    public static final float RUNNER_Y = PISO_Y + PISO_ALTO;
    public static final float RUNNER_X = 2f;
    public static final float RUNNER_WIDTH = 1f;

    @Override
	public void create () {
		setScreen(new PantallaTitulo(this));
	}
}
