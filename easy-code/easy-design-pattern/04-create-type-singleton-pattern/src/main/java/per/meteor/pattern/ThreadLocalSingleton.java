package per.meteor.pattern;

/**
 * 同线程单例，不同线程不同实例
 * 采用ThreadLocal需要记得remove,因为ThreadLocalMap的key是弱引用，不进行处理容易造成内存泄露
 * @author meteor
 * @date 2022-03-17 14:23
 */
public class ThreadLocalSingleton {

    private static final ThreadLocal<ThreadLocalSingleton> THREAD_LOCAL = ThreadLocal.withInitial(ThreadLocalSingleton::new);

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return THREAD_LOCAL.get();
    }

    public void undo() {
        THREAD_LOCAL.remove();
    }
}
