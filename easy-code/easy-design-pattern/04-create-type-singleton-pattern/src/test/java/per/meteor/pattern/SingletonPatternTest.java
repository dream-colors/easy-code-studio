package per.meteor.pattern;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author meteor
 * @date 2022-03-17 14:20
 */
class SingletonPatternTest {

    @Test
    void singletonTest() {
        assertSame(HungerSingleton.getInstance(), HungerSingleton.getInstance());
        assertSame(LazySingleton.getInstance(), LazySingleton.getInstance());
        assertSame(LazyLockSingleton.getInstance(), LazyLockSingleton.getInstance());
        assertSame(DoubleCheckLockSingleton.getInstance(), DoubleCheckLockSingleton.getInstance());
        assertSame(AtomicReferenceSingleton.getInstance(), AtomicReferenceSingleton.getInstance());
        assertSame(StaticInnerClassSingleton.getInstance(), StaticInnerClassSingleton.getInstance());
        assertSame(EnumSingleton.getInstance(), EnumSingleton.getInstance());

        assertSame(ThreadLocalSingleton.getInstance(), ThreadLocalSingleton.getInstance());

        ThreadLocalSingleton instance = ThreadLocalSingleton.getInstance();

        new Thread(() -> {
            assertSame(instance, ThreadLocalSingleton.getInstance());
        }).start();
    }

}
