package edu.olezha.sandbox.collections;

/**
 * i = 1 .. N
 * parent(i) = ⌊i / 2⌋
 * leftChild(i) = 2i
 * rightChild(i) = 2i + 1
 */
public class ArrayBinaryHeap {

    private int[] heap = new int[8];
    private int size = 0;

    public int size() {
        return size;
    }

    public void add(int v) {
        if (size == heap.length) {
            int[] newHeap = new int[size * 2];
            System.arraycopy(heap, 0, newHeap, 0, size);
            heap = newHeap;
        }

        heap[size++] = v;
        heapifyUp(size);
    }

    public int get() {
        int v = heap[0];
        heap[0] = heap[--size];
        heapifyDown(1);
        return v;
    }

    public String toString() {
        if (size == 0)
            return "[]";

        StringBuilder sb = new StringBuilder().append('[');
        for (int i = 0; ; ) {
            sb.append(heap[i++]);
            if (i == size)
                return sb.append(']').toString();
            else
                sb.append(',').append(' ');
        }
    }

    private void heapifyUp(int position) {
        if (position > 1) {
            int parentPosition = position / 2;
            if (heap[parentPosition - 1] > heap[position - 1]) {
                int parent = heap[parentPosition - 1];
                heap[parentPosition - 1] = heap[position - 1];
                heap[position - 1] = parent;
                heapifyUp(parentPosition);
            }
        }
    }

    private void heapifyDown(int position) {
        int leftChildPosition = 2 * position;

        if (size < leftChildPosition)
            return;

        int candidatePosition;
        if (size > leftChildPosition) {
            int leftChild = heap[leftChildPosition - 1];
            int rightChild = heap[leftChildPosition];
            if (leftChild > rightChild)
                candidatePosition = leftChildPosition + 1;
            else
                candidatePosition = leftChildPosition;
        } else {
            candidatePosition = leftChildPosition;
        }

        if (heap[position - 1] > heap[candidatePosition - 1]) {
            int v = heap[position - 1];
            heap[position - 1] = heap[candidatePosition - 1];
            heap[candidatePosition - 1] = v;
            heapifyDown(candidatePosition);
        }
    }
}
