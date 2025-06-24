package lightweight_low_level_game_bootstrap;

public class Launcher {
    public static void main(String[] args) {
        try (
                Platform platform = new Platform();
                Lwjgl3Window window = new Lwjgl3Window(1280, 720, "DemoGame", 1);
                Lwjgl3Application app = new Lwjgl3Application(window, new DemoGame())) {
                    app.run();
        }
    }
}
