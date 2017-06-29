package astar;

/**
 * Created by Roman on 26.06.2017.
 */
import java.util.LinkedList;
import java.util.List;
public class Astar<S extends State, S1 extends State1<S>> {

    private S1 state1;

    public Astar(S1 state1){
        this.state1 = state1;
    }

    public Finding<S> search(S fr, S to){
        List<S> open = new LinkedList<>();
        List<S> closed = new LinkedList<>();
        fr.clear();
        to.clear();
        open.add(fr);
        fr.setG(0);
        fr.setH(state1.getWeight(fr, to));
        while (!open.isEmpty()) {
            S current = null;
            int minF = Integer.MAX_VALUE;
            for (S state : open) {
                if (state.getF() < minF) {
                    minF = state.getF();
                    current = state;
                }
            }
            if (current.equals(to)) {
                return buildPath(current, open.size(), closed.size());
            }
            open.remove(current);
            closed.add(current);

            List<S> neighList = state1.getNeigh(current);
            for (S neighbour : neighList) {
                if (closed.contains(neighbour)) {
                    continue;
                }
                int estimatedG = current.getG() + 1;
                if (neighbour.getParent() != null) {
                    if (neighbour.getG() > estimatedG) {
                        neighbour.setParent(current);
                        neighbour.setG(estimatedG);
                        neighbour.setH(state1.getWeight(neighbour, to));
                    }
                } else {
                    neighbour.setParent(current);
                    neighbour.setG(estimatedG);
                    neighbour.setH(state1.getWeight(neighbour, to));
                    open.add(neighbour);
                }
            }
        }
        return null;
    }

    private Finding<S> buildPath(S solution, int openSize, int closedSize) {
        Finding<S> result = new Finding<S>();
        LinkedList<S> stateList = new LinkedList<>();
        State stacked = solution;
        while (stacked != null) {
            stateList.addFirst((S)stacked);
            stacked = stacked.getParent();
        }
        result.setOpenSize(openSize);
        result.setClosedSize(closedSize);
        result.setList(stateList);
        return result;
    }
}