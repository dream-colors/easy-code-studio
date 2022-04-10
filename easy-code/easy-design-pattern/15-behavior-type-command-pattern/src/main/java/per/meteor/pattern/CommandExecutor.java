package per.meteor.pattern;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * 命令接收者，实际执行命令逻辑的对象
 * @author meteor
 * @date 2022-03-17 16:51
 */
public class CommandExecutor {

    private static final Logger LOGGER = Logger.getLogger(CommandExecutor.class.getName());

    public void doCommand(AtomicInteger count) {
        String notes = String.format("do command logic %s", count.incrementAndGet());
        LOGGER.info(notes);
    }
}
