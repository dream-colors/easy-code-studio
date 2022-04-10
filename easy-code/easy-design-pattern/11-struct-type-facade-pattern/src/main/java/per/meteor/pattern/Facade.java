package per.meteor.pattern;

/**
 * 外观角色
 * @author meteor
 * @date 2022-03-16 20:09
 */
public class Facade {

    private final FacadeMemberA memberA = new FacadeMemberA();
    private final FacadeMemberB memberB = new FacadeMemberB();

    public void method() {
        memberA.method();
        memberB.method();
    }
}
