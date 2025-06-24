package lightweight_low_level_game_bootstrap;

import static org.lwjgl.opengl.GL33C.*;

public final class Lwjgl3Application implements AutoCloseable {
    private final Lwjgl3Window window;
    private final Game game;

    public Lwjgl3Application(Lwjgl3Window window, Game game) {
        this.window = window;
        this.game = game;
    }

    public void run() {
        game.create();
        long previousTime = System.nanoTime();

        while (!window.shouldClose()) {
            float dt = (System.nanoTime() - previousTime) / 1_000_000_000f;
            previousTime = System.nanoTime();

            game.update(dt);

            glClear(GL_COLOR_BUFFER_BIT);
            game.render();

            window.swapBuffers();
            window.pollEvents();
        }
        game.dispose();
    }

    @Override public void close() {
        window.close();
    }
}
