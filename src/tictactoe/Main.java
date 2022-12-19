package tictactoe;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> argsMap = ArgsUtil.argsToMap(args);
        String mode = ModeUtil.getModeOrDefault(argsMap);

        // modes:
        // test (e.g. print additional logs)
        // lesson (meets the requirements of JetBrains Academy project (part 5 of 5)
        //    https://hyperskill.org/projects/48/stages/259/implement)
        // myGame (my specific version)

        if(mode.equals("test")) {
            System.out.println("Start");
        }
        Game game = new Game(mode);
        game.run();

        if(mode.equals("test")) {
            System.out.println("End");
        }
    }
}
