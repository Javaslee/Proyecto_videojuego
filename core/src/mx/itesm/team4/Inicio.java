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
	public static final float PISO_ALTO = 2f;
	public static final float PISO_DENSITY = 0f;

    public static final float RUNNER_DENSITY = 0.8f;
    public static final float RUNNER_ALTO = 2f;
    public static final float RUNNER_Y = PISO_Y+PISO_ALTO;
    public static final float RUNNER_X = 2;
    public static final float RUNNER_ANCHO = 1f;
    public static final Vector2 RUNNER_IMPULSO_SALTO = new Vector2(0, 13f);
	public static final float RUNNER_ESQUIVAR_X = 2f;
	public static final float RUNNER_ESQUIVAR_Y = 1.5f;
	public static final float ENEMY_X = 25f;
	public static final float RUNNING_SHORT_ENEMY_Y = 1.5f;
	public static final float ENEMY_DENSITY = RUNNER_DENSITY;
	public static final float RUNNING_LONG_ENEMY_Y = 2f;
	public static final float FLYING_ENEMY_Y = 3f;
	public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-10f, 0);
	public static final float RUNNER_HIT_ANGULAR_IMPULSE = 10f;

	@Override
	public void create () {
		setScreen(new PantallaTitulo(this));
	}
}
