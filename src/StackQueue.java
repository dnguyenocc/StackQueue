public class StackQueue {
    private int[] elems;
    private int capacity, stackTop, queueHead, queueTail;
    // Queue element indices: [0, boundary)
    // Stack element indices: [boundary, capacity)
    private int boundary;
    private int queueSize = 0;
    static final int DEFAULT_CAPACITY = 100;
    public StackQueue() {
        this(DEFAULT_CAPACITY);
    }

    public StackQueue(int capacity) {
        this.capacity = capacity;
        this.elems = new int[this.capacity];
        this.boundary = this.capacity / 2; // Initialize the boundary at the center of the array
        this.queueSize = 0;
        this.stackTop = this.capacity;
        this.queueHead = 0;
        this.queueTail = 0;
    }
    private int stackCapacity() {
        return this.capacity - this.boundary;
    }

    private int stackSize() {
        return this.capacity - this.stackTop;
    }

    private int queueSize() {
        return queueSize;
    }

    private int queueCapacity() {
        return this.boundary;
    }

    private void setElement(int index, int num) {
        assert index >= 0 && index < this.capacity;
        this.elems[index] = num;
    }

    private int getElement(int index) {
        assert index >= 0 && index < this.capacity;
        return this.elems[index];
    }

    public boolean stackPush(int num) {
        if (stackSize() == stackCapacity()) {
            if (queueSize() < queueCapacity()) {
                reArrannge(queueSize() + (queueCapacity() - queueSize()) / 2);
            }
        }
        if (stackSize() < stackCapacity()) {
            stackTop--;
            setElement(stackTop, num);
            return true;
        } else {
          return false;
        }
    }

    public int stackPop() {
        assert stackSize() > 0;
        int num = getElement(stackTop);
        stackTop++;
        return num;
    }

    public boolean enqueue(int num) {
        if (queueSize() == queueCapacity()) {
            if (stackSize() < stackCapacity()) {
                reArrannge(queueSize() + Math.max((stackCapacity() - stackSize()) / 2, 1));
            }
        }
        if (queueSize() < queueCapacity()) {
            setElement(queueTail, num);
            queueSize++;
            queueTail = (queueTail + 1) % queueCapacity();
            return true;
        } else{
            return false;
        }
    }

    public int dequeue() {
        assert queueSize() > 0;
        int num = getElement(queueHead);
        queueHead = (queueHead + 1) % queueCapacity();
        queueSize--;
        return num;
    }

    private void reArrannge(int newBoundary) {
        if (newBoundary != boundary && newBoundary >= 0 && newBoundary <= capacity) {
            if (this.queueHead != 0 || this.queueTail != queueSize()) {
                int[] temp = new int[queueSize()];
                int queueCapacity = queueCapacity();
                for (int i = 0; i < queueSize(); i++) {
                    temp[i] = getElement((this.queueHead + i) % queueCapacity);
                }
                for (int i = 0; i < queueSize(); i++) {
                    setElement(i, temp[i]);
                }
                this.queueHead = 0;
                this.queueTail = queueSize();
            }
            this.boundary = newBoundary;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Array elements: ");
        for (int i = 0; i < capacity; i++) {
            sb.append(getElement(i));
            sb.append(" ");
        }
        sb.append("\nQueue capacity: " + queueCapacity());
        sb.append("\nQueue size: " + queueSize());
        sb.append("\nQueue elements: ");
        for (int i = 0; i < queueSize(); i++) {
            sb.append(getElement((queueHead + i) % queueCapacity()));
            sb.append(" ");
        }
        sb.append("\nStack capacity: " + stackCapacity());
        sb.append("\nStack size: " + stackSize());
        sb.append("\nStack elements: ");
        for (int i = 0; i < stackSize(); i++) {
            sb.append(getElement(capacity - 1 - i));
            sb.append(" ");
        }
        return sb.toString();
    }
}
