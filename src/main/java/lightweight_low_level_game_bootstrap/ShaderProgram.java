package lightweight_low_level_game_bootstrap;

import static org.lwjgl.opengl.GL33C.*;

public final class ShaderProgram implements AutoCloseable {
    private final int id;

    public ShaderProgram(String vs, String fs) {
        this.id = ShaderUtils.createProgram(vs, fs);
    }

    public void use() {
        glUseProgram(id);
    }

    public int getId() {
        return id;
    }

    @Override public void close() {
        glDeleteProgram(id);
    }
}
