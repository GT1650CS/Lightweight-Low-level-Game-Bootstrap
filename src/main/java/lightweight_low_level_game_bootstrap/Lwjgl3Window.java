package lightweight_low_level_game_bootstrap;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;

public final class Lwjgl3Window implements AutoCloseable {
    private long handle;

    public Lwjgl3Window(int width, int height, String title, int swapInterval) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        handle = glfwCreateWindow(width, height, title, 0, 0);
        if (handle == 0) {
            throw new RuntimeException("Couldn't create GLFW window");
        }

        glfwSetKeyCallback(handle, (win, key, scancode, action, modifiers) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(win, true);
            }
        });

        glfwMakeContextCurrent(handle);
        GL.createCapabilities();
        glfwSwapInterval(swapInterval);
        glfwShowWindow(handle);
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(handle);
    }

    public void swapBuffers() {
        glfwSwapBuffers(handle);
    }

    public void pollEvents() {
        glfwPollEvents();
    }

    @Override public void close() {
        if (handle != 0) {
            glfwDestroyWindow(handle);
            handle = 0;
        }
    }
}
