package per.meteor.pattern;

/**
 * 命令调用者，对外提供的命令执行接口。通过传递命令进行命令调用
 * @author meteor
 * @date 2022-03-17 16:53
 */
public class CommandInvoker {

    private final CommandQueue commandQueue;

    public CommandInvoker(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    public void call(BaseCommand command) {
        command.execute();
    }

    public void callAll() {
        commandQueue.execute();
    }

    public void undo(BaseCommand command) {
        command.undo();
        // 撤销后移除命令
        commandQueue.remove(command);
    }
}
