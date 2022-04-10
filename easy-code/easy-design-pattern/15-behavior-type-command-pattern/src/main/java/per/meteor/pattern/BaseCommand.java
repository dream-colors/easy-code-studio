package per.meteor.pattern;

/**
 * @author meteor
 * @date 2022-03-17 16:49
 */
public interface BaseCommand {

    /**
     * 执行命令
     */
    void execute();

    /**
     * 撤销命令
     */
    void undo();
}
