package lightweight_low_level_game_bootstrap;

import static org.lwjgl.opengl.GL33C.*;

public class DemoGame implements Game {
    private int vao, vbo;
    private ShaderProgram shader;

    @Override public void create() {
        float[] vertices = {
                0f,  0.5f, 0f,
                -0.5f,-0.5f, 0f,
                0.5f,-0.5f, 0f
        };

        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0L);
        glEnableVertexAttribArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

        String vs = "#version 330 core\n" +
                "layout(location=0) in vec3 position;\n" +
                "void main() {\n" +
                "    gl_Position = vec4(position,1.0);\n" +
                "}";
        String fs = "#version 330 core\n" +
                "out vec4 colour;\n" +
                "void main() {\n" +
                "    colour = vec4(1.0,0.5,0.2,1.0);\n" +
                "}";
        shader = new ShaderProgram(vs, fs);
    }

    @Override public void update(float dt) {
        System.out.println("Delta time:" + dt);
    }

    @Override public void render() {
        glClear(GL_COLOR_BUFFER_BIT);
        shader.use();
        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLES, 0, 3);
        glBindVertexArray(0);
        glUseProgram(0);
    }

    @Override public void dispose() {
        shader.close();
        glDisableVertexAttribArray(0);
        glDeleteBuffers(vbo);
        glDeleteVertexArrays(vao);
    }
}
