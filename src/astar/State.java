package astar;

/**
 * Created by Roman on 26.06.2017.
 */
public class State {
    private int g;
    private int h;

    private State parent;

    public int getG(){
        return g;
    }

    public void setG(int g){
        this.g = g;
    }

    public void setH(int h){
        this.h = h;
    }

    public int getF(){
        return (g + h);
    }

    public void setParent(State parent){
        this.parent = parent;
    }

    public State getParent(){
        return parent;
    }

    public void clear() {
        g = 0;
        h = 0;
        parent = null;
    }
}