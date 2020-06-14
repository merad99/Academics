import java.util.Iterator;

public class PrimeIterable implements Iterable<Integer> {
	private int max;

	public PrimeIterable(int max) {
		this.max = max;
	}

	public Iterator<Integer> iterator() {
		Iterator<Integer> itr = new PrimeIterator(max);
		return itr;
	}

	private class PrimeIterator implements Iterator<Integer> {
		private int max;
		private int p;

		public PrimeIterator(int max) {
			this.max = max;
			p = 2;
		}

		private int nextPrime() {
			while (true) {
				p++;
				if (p <= 3)
					return p;
				boolean divisible = false;
				for (int t = 2; !divisible && t <= Math.sqrt(p); t++) {
					if (p % t == 0)
						divisible = true;
				}
				if (!divisible)
					return p;
			}
		}

		// nothing to remove
		public void remove() {
			throw new UnsupportedOperationException();
		}

		public boolean hasNext() {
			return p < max;
		}

		public Integer next() {
			int prev = p;
			// compute one prime ahead to let hasNext check for end
			nextPrime();
			return (Integer) prev;
		}
	}

	public static void main(String[] args) {

		// generate an iterable and an iterator
		Iterable<Integer> primes = new PrimeIterable(1000);
		Iterator<Integer> primesIterator = primes.iterator();

		// use PrimeIterator
		System.out.print("\n Iterator ");
		int c = 0;
		while (primesIterator.hasNext()) {
			if ((c++ % 20) == 0)
				System.out.println();
			System.out.printf(" %5d", primesIterator.next());
		}

		System.out.print("\n Iterable \n");
		// or iterable
		c = 0;
		for (Integer p : primes) {
			if ((c++ % 20) == 0)
				System.out.println();
			System.out.printf(" %5d", p);
		}

		System.out.println("\n\nthat's all, folks ");
	}
}
