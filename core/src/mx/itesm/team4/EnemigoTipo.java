package mx.itesm.team4;

public enum EnemigoTipo {
    RUNNING_SMALL(1f, 1f, Inicio.ENEMY_X, Inicio.RUNNING_SHORT_ENEMY_Y, Inicio.ENEMY_DENSITY),
    RUNNING_WIDE(2f, 1f, Inicio.ENEMY_X, Inicio.RUNNING_SHORT_ENEMY_Y, Inicio.ENEMY_DENSITY),
    RUNNING_LONG(1f, 2f, Inicio.ENEMY_X, Inicio.RUNNING_LONG_ENEMY_Y, Inicio.ENEMY_DENSITY),
    RUNNING_BIG(2f, 2f, Inicio.ENEMY_X, Inicio.RUNNING_LONG_ENEMY_Y, Inicio.ENEMY_DENSITY),
    FLYING_SMALL(1f, 1f, Inicio.ENEMY_X, Inicio.FLYING_ENEMY_Y, Inicio.ENEMY_DENSITY),
    FLYING_WIDE(2f, 1f, Inicio.ENEMY_X, Inicio.FLYING_ENEMY_Y, Inicio.ENEMY_DENSITY);

    private float width;
    private float height;
    private float x;
    private float y;
    private float density;

    EnemigoTipo(float width, float height, float x, float y, float density) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.density = density;
    }


    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getDensity() {
        return density;
    }
}