package mx.itesm.team4;

public abstract class UserData {
    protected Enums enumsType;
    protected float width;
    protected float height;

    public UserData(){

    }

    public UserData(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Enums getEnumsType(){
        return enumsType;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
