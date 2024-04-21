package application;



import java.util.ArrayList;

public class piortyqueue {
    private ArrayList<TableNode> queue;

    public piortyqueue() {
        this.queue = new ArrayList<>();
    }

    public void add(TableNode node) {
        queue.add(node);
        queue.sort((n1, n2) -> Double.compare(n1.getDistance(), n2.getDistance()));
    }

    public TableNode poll() {
        if (!queue.isEmpty()) {
            return queue.remove(0); // Remove and return the first element
        }
        return null; // Return null if the queue is empty
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
