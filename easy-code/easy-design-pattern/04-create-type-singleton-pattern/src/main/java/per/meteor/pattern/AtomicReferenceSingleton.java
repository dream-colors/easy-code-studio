package per.meteor.pattern;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS(线程安全)
 * 采用cas模式实现，大并发下，线程容易忙等
 * @author meteor
 * @date 2022-03-17 14:03
 */
public class AtomicReferenceSingleton {

    private static final AtomicReference<AtomicReferenceSingleton> ATOMIC_REFERENCE = new AtomicReference<>();

    private AtomicReferenceSingleton() {}

    public static AtomicReferenceSingleton getInstance() {
        AtomicReferenceSingleton instance = ATOMIC_REFERENCE.get();
        if (instance == null) {
            ATOMIC_REFERENCE.compareAndSet(null, new AtomicReferenceSingleton());
            return ATOMIC_REFERENCE.get();
        }
        return instance;
    }

}
