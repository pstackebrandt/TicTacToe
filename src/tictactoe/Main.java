package tictactoe;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> argsMap = ArgsUtil.argsToMap(args);
        String mode = ModeUtil.getModeOrDefault(argsMap);
        // modes: test, lesson, user

        if(mode.equals("test")) {
            System.out.println("Start");
        }
        Game game = new Game();
        game.run(mode);

        if(mode.equals("test")) {
            System.out.println("End");
        }
    }
}
