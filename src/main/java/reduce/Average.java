package reduce;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

class Averager {
    private double sum;
    private long count;

    public Averager(double sum, long count) {
        this.sum = sum;
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public long getCount() {
        return count;
    }

    public Averager include(double d) {
        return new Averager(this.sum + d, this.count + 1);
    }

    public Averager merge(Averager other) {
        return new Averager(this.sum + other.sum, this.count + other.count);
    }
}

public class Average {
    public static void main(String[] args) {
        Averager avg = Stream.iterate(1.0, x -> x + 1)
                .limit(10)
                .reduce(new Averager(0, 0), (a, i) -> a.include(i), (a1, a2) -> a1.merge(a2));
        System.out.println("Average is " + avg.getSum() / avg.getCount());
    }
}
