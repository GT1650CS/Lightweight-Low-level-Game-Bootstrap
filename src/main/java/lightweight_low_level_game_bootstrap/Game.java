package lightweight_low_level_game_bootstrap;

public interface Game {
    void create();
    void update(float dt);
    void render();
    void dispose();
}
