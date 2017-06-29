package astar;

import java.util.List;

/**
 * Created by Roman on 26.06.2017.
 */
public interface State1<S extends State> {
    List<S> getNeigh(S parent);
    int getWeight(S fr, S to);
}