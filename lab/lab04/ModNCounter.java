import java.io.*;
public class ModNCounter extends Counter {
	private int mod;
	public ModNCounter(int x) {
		mod = x;
	}
	@Override //overwrite original function from counter.
	public void increment() {
		count++;
		if (count == mod) count = 0;
	}
	public static void main(String[] args) {
		// ModNCounter modCounter = new ModNCounter(3);
		// modCounter.increment();
		// System.out.println(modCounter.value()); // prints 1
		// modCounter.reset();
		// modCounter.increment();
		// System.out.println(modCounter.value()); // still prints 1
		ModNCounter modCounter = new ModNCounter(3);
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value());
		modCounter.increment();
		modCounter.increment();
		System.out.println(modCounter.value()); // prints 1
	}
}
