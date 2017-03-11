package util.ds;

import java.util.PriorityQueue;

public class DynamicPriorityQueue<K extends Comparable<K>> {

	private PriorityQueue<QueueElement<K>> queue;

	public boolean add(QueueElement<K> e) {
		return offer(e);
	}

	public QueueElement<K> peek() {
		QueueElement<K> e = queue.peek();
		while (e != null && e.stale) {
			queue.poll();
			e = queue.peek();
		}
		return e;
	}

	public QueueElement<K> poll() {
		QueueElement<K> e = queue.poll();
		while (e != null && e.stale) {
			e = queue.poll();
		}
		return e;
	}

	public void rePrioritize(QueueElement<K> e) {
		e.stale = true;
		if (e.activeClone != null) {
			e.activeClone.stale = true;
		}
		e.activeClone = new QueueElement<K>(e.key, e.data);
		offer(e.activeClone);
		e.activeClone.addedToQueue = true;
	}

	public void remove(QueueElement<K> e) {
		e.stale = true;
		if (e.activeClone != null)
			e.activeClone.stale = true;
	}

	public void clear() {
		queue.clear();
	}

	public boolean offer(QueueElement<K> e) {
		if (!e.addedToQueue) {
			e.addedToQueue = true;
			return queue.add(e);
		}
		return false;
	}

	public static final class QueueElement<K extends Comparable<K>> implements Comparable<QueueElement<K>> {
		private K key;
		private K staticKey;
		private Object data;
		private boolean stale;
		private boolean addedToQueue;
		private QueueElement<K> activeClone;

		public QueueElement(K key) {
			super();
			this.key = key;
			this.staticKey = key;
		}

		public QueueElement(K key, Object data) {
			super();
			this.key = key;
			this.staticKey = key;
			this.data = data;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
			if (activeClone != null) {
				activeClone.setData(data);
			}
		}

		@Override
		public int compareTo(QueueElement<K> o) {
			return staticKey.compareTo(o.staticKey);
		}

	}
}
