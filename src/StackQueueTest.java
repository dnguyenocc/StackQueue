import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackQueueTest {
    @Test
    void BasicStackTest() {
       StackQueue stackQueue = new StackQueue();
        for (int i = 0; i < StackQueue.DEFAULT_CAPACITY; i++) {
            stackQueue.stackPush(i);
        }
        for (int i = StackQueue.DEFAULT_CAPACITY - 1; i >= 0; i--) {
            assertEquals(stackQueue.stackPop(), i);
        }
    }

    @Test
    void BasicQueueTest() {
        StackQueue stackQueue = new StackQueue();
        for (int i = 0; i < StackQueue.DEFAULT_CAPACITY; i++) {
            stackQueue.enqueue(i);
        }
        for (int i = 0; i < StackQueue.DEFAULT_CAPACITY; i++) {
            assertEquals(stackQueue.dequeue(), i);
        }
    }

    @Test
    void StackOverflowed() {
        int capacity = 8;
        StackQueue stackQueue = new StackQueue(capacity);
        for (int i = 0; i < capacity; i++) {
            assertTrue(stackQueue.stackPush(i));
        }
        assertFalse(stackQueue.stackPush(1));
    }

    @Test
    void StackPushPopPush() {
        int capacity = 8 ;
        StackQueue stackQueue = new StackQueue(capacity);
        for (int i = 0; i < capacity; i++) {
            assertTrue(stackQueue.stackPush(i));
        }
        for (int i = 0; i < capacity; i++) {
            stackQueue.stackPop();
        }
        for (int i = 0; i < capacity; i++) {
            assertTrue(stackQueue.stackPush(i));
        }
        for (int i = 0; i < capacity; i++) {
            assertEquals(stackQueue.stackPop(), capacity - 1 - i);
        }
    }

    @Test
    void QueuePushPopPush() {
        int capacity = 8 ;
        StackQueue stackQueue = new StackQueue(capacity);
        for (int i = 0; i < capacity; i++) {
            assertTrue(stackQueue.enqueue(i));
        }
        for (int i = 0; i < capacity; i++) {
            stackQueue.dequeue();
        }
        for (int i = 0; i < capacity; i++) {
            assertTrue(stackQueue.enqueue(i));
        }
        for (int i = 0; i < capacity; i++) {
            assertEquals(stackQueue.dequeue(), i);
        }
    }

    @Test
    void StackThenQueue() {
        int capacity = 1000;
        StackQueue stackQueue = new StackQueue(capacity);
        for (int i = 0; i < capacity; i++) {
            assertTrue(stackQueue.stackPush(i));
        }
        for (int i = 0; i < capacity; i++) {
            stackQueue.stackPop();
            stackQueue.enqueue(i);
        }
        for (int i = 0; i < capacity; i++) {
            assertEquals(stackQueue.dequeue(), i);
        }
    }

    @Test
    void QueueThenStack() {
        int capacity = 1000;
        StackQueue stackQueue = new StackQueue(capacity);
        for (int i = 0; i < capacity; i++) {
            assertTrue(stackQueue.enqueue(i));
        }

        for (int i = 0; i < capacity / 2; i++) {
            stackQueue.dequeue();
            stackQueue.stackPush(i);
        }
        for (int i = 0; i < capacity / 2; i++) {
            assertEquals(capacity / 2 - 1 - i, stackQueue.stackPop());
            stackQueue.enqueue(i);
        }
        for (int i = capacity/2; i < capacity; i++) {
            assertEquals(stackQueue.dequeue(), i);
        }
    }
}
