package util.ds;

import static org.junit.Assert.*;

import org.junit.Test;

import util.ds.DynamicPriorityQueue.QueueElement;

public class TestDynamicPriorityQueue {

	@Test
	public void test() {
		DynamicPriorityQueue<Integer> testQueue = new DynamicPriorityQueue<>();
		QueueElement<Integer> a = new QueueElement<>(10);
		QueueElement<Integer> b = new QueueElement<>(20);
		QueueElement<Integer> c = new QueueElement<>(30);
		QueueElement<Integer> d = new QueueElement<>(40);
		QueueElement<Integer> e = new QueueElement<>(50);
		testQueue.add(c);
		testQueue.add(b);
		testQueue.add(a);
		assertEquals(testQueue.peek().getKey(), Integer.valueOf(10));
		a.setKey(25);
		testQueue.notifyKeyChange(a);
		assertEquals(testQueue.peek().getKey(), Integer.valueOf(20));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(20));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(25));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(30));
		testQueue.add(c);
		testQueue.add(b);
		testQueue.add(a);
		testQueue.add(d);
		testQueue.add(e);
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(20));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(25));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(30));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(40));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(50));
		testQueue.add(a);
		testQueue.add(b);
		testQueue.add(c);
		testQueue.add(d);
		testQueue.add(e);
		b.setKey(60);
		b.setKey(65);
		c.setKey(10);
		a.setKey(10);
		// a = 10, b =65, c=10, d=40, e=50
		testQueue.notifyKeyChange(a);
		testQueue.notifyKeyChange(b);
		testQueue.notifyKeyChange(c);
		c.setKey(5);
		testQueue.notifyKeyChange(c);
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(5));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(10));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(40));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(50));
		assertEquals(testQueue.poll().getKey(), Integer.valueOf(65));
	}

}
