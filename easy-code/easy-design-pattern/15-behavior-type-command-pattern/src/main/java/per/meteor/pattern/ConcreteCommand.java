package per.meteor.pattern;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * @author meteor
 * @date 2022-03-17 16:50
 */
public class ConcreteCommand implements BaseCommand {

    private static final Logger LOGGER = Logger.getLogger(ConcreteCommand.class.getName());

    /** 命令接收者，即实际处理命令的对象 */
    private final CommandExecutor commandExecutor;
    /** 命令执行记录，用于命令撤销操作 */
    private final AtomicInteger count = new AtomicInteger();

    public ConcreteCommand(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void execute() {
        commandExecutor.doCommand(count);
    }

    @Override
    public void undo() {
        String notes = String.format("do undo command logic %s", count.decrementAndGet());
        LOGGER.info(notes);
    }
}
