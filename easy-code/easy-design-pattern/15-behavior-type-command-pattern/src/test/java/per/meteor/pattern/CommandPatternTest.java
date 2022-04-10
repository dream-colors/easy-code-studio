package per.meteor.pattern;

import org.junit.jupiter.api.Test;

/**
 * @author meteor
 * @date 2022-03-17 17:10
 */
class CommandPatternTest {

    @Test
    void test() {
        // 新建命令及执行者
        CommandExecutor commandExecutor = new CommandExecutor();
        ConcreteCommand concreteCommand = new ConcreteCommand(commandExecutor);
        // 创建命令队列，并添加命令
        CommandQueue commandQueue = new CommandQueue();
        commandQueue.add(concreteCommand);
        commandQueue.add(concreteCommand);
        // 创建命令调用者,并执行命令
        CommandInvoker commandInvoker = new CommandInvoker(commandQueue);
        commandInvoker.callAll();
        concreteCommand.undo();
    }

}
