package per.meteor.pattern;

/**
 * @author meteor
 * @date 2022-03-17 17:47
 */
public class ConcreteIterator implements BaseIterator {

    private final String[] arrs;
    private int cursor;

    public ConcreteIterator(String[] arrs) {
        this.arrs = arrs;
    }

    @Override
    public void first() {
        cursor = 0;
    }

    @Override
    public void next() {
       if (++ cursor > arrs.length) {
           throw new IllegalArgumentException("iterator error");
       }
    }

    @Override
    public boolean hasNext() {
        return cursor <= arrs.length;
    }

    @Override
    public Object currentItem() {
        return arrs[cursor];
    }
}
