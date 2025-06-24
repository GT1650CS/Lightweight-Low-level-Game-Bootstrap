package lightweight_low_level_game_bootstrap;

import static org.lwjgl.opengl.GL33C.*;

public final class ShaderUtils {
    public static int compileShader(int type, String source) {
        int shader = glCreateShader(type);
        glShaderSource(shader, source);
        glCompileShader(shader);
        if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
            String log = glGetShaderInfoLog(shader);
            glDeleteShader(shader);
            throw new RuntimeException("Shader compilation error:\n" + log);
        }
        return shader;
    }

    public static int createProgram(String vertexSrc, String fragmentSrc) {
        int vs = compileShader(GL_VERTEX_SHADER, vertexSrc);
        int fs = compileShader(GL_FRAGMENT_SHADER, fragmentSrc);
        int program = glCreateProgram();
        glAttachShader(program, vs);
        glAttachShader(program, fs);
        glLinkProgram(program);
        if (glGetProgrami(program, GL_LINK_STATUS) == GL_FALSE) {
            String log = glGetProgramInfoLog(program);
            glDeleteProgram(program);
            glDeleteShader(vs);
            glDeleteShader(fs);
            throw new RuntimeException("Program link error:\n" + log);
        }
        glDeleteShader(vs);
        glDeleteShader(fs);
        return program;
    }
}
