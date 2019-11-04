package edu.olezha.sandbox.multithreading.concurrent.puzzle;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentPuzzleSolver<P, M> {

    private final Puzzle<P, M> puzzle;
    private final ExecutorService executorService;
    private final ConcurrentMap<P, Boolean> seen;
    final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();
    private final AtomicInteger taskCount = new AtomicInteger(0);

    ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService executorService, ConcurrentMap<P, Boolean> seen) {
        this.puzzle = puzzle;
        this.executorService = executorService;
        this.seen = seen;
    }

    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            executorService.execute(newTask(p, null, null));
            // block until solution found
            Node<P, M> solnNode = solution.getValue();
            return (solnNode == null) ? null : solnNode.asMoveList();
        } finally {
            executorService.shutdown();
        }
    }

    protected Runnable newTask(P p, M m, Node<P, M> n) {
        return new SolverTask(p, m, n);
    }

    class SolverTask extends Node<P, M> implements Runnable {

        SolverTask(P position, M move, Node<P, M> previous) {
            super(position, move, previous);
            taskCount.incrementAndGet();
        }

        public void run() {
            try {
                if (solution.isSet()
                        || seen.putIfAbsent(position, true) != null)
                    return; // already solved or seen this position
                if (puzzle.isGoal(position))
                    solution.setValue(this);
                else
                    for (M m : puzzle.legalMoves(position))
                        executorService.execute(newTask(puzzle.move(position, m), m, this));
            } finally {
                if (taskCount.decrementAndGet() == 0)
                    solution.setValue(null);
            }
        }
    }
}
