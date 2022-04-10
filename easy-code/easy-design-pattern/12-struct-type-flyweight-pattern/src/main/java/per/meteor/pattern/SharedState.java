package per.meteor.pattern;

/**
 * @author meteor
 * @date 2022-03-16 22:04
 */
public class SharedState {
    private String name;
    private Integer age;

    public SharedState(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "SharedState{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
