package superiterable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

//    public void forEvery(Consumer<E> op) {
//        for (E s : self) {
//            op.accept(s);
//        }
//        System.out.println("-----------------------");
//    }

    public <F> SuperIterable<F> map(Function<E, F>op) {
        List<F> results = new ArrayList<>();
        self.forEach(x -> results.add(op.apply(x)));
        return new SuperIterable<>(results);
    }

    public <F> SuperIterable<F> flatMap(Function<E, SuperIterable<F>>op) {
        List<F> results = new ArrayList<>();
        self.forEach(x -> op.apply(x).forEach(y -> results.add(y)));
        return new SuperIterable<>(results);
    }

    public SuperIterable<E> filter(Predicate<E> crit) {
        List<E> results = new ArrayList<>();

        for (E s : self) {
            if (crit.test(s)) {
                results.add(s);
            }
        }
        return new SuperIterable(results);
    }
    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }
}
