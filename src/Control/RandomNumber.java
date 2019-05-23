package Control;

public class RandomNumber implements RandomGenerator {

	@Override
	public int nextInt(int n) {
		return (int) (Math.random() * (n + 1));
	}

}
