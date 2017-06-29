package astar;

/**
 * Created by Roman on 26.06.2017.
 */
import java.util.List;


public class Finding<S extends State> {

    private int openSize;

    private int closedSize;

    private List<S> list;

    public void setOpenSize(int size){
        this.openSize = size;
    }

    public void setClosedSize(int size){
        this.closedSize = size;
    }

    public void setList(List<S> list){
        this.list = list;
    }

    public List<S> getList(){
        return list;
    }
}