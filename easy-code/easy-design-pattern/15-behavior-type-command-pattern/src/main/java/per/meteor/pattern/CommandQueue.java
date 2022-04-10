package per.meteor.pattern;

import java.util.LinkedList;

/**
 * 命令队列，用于执行一串命令
 * @author meteor
 * @date 2022-03-17 16:54
 */
public class CommandQueue {

    private static final LinkedList<BaseCommand> COMMAND_QUEUE = new LinkedList<>();

    public void add(BaseCommand command) {
        COMMAND_QUEUE.add(command);
    }

    public void remove(BaseCommand command) {
        COMMAND_QUEUE.remove(command);
    }

    public void execute() {
        COMMAND_QUEUE.forEach(BaseCommand::execute);
    }
}
