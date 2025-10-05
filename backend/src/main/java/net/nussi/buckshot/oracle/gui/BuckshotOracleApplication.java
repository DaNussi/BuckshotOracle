package net.nussi.buckshot.oracle.gui;

import imgui.ImGui;
import imgui.ImGuiIO;
import imgui.app.Application;
import imgui.app.Configuration;
import imgui.flag.ImGuiCond;
import imgui.flag.ImGuiConfigFlags;
import imgui.flag.ImGuiInputTextFlags;
import imgui.flag.ImGuiWindowFlags;
import org.lwjgl.glfw.GLFW;

import static imgui.app.Application.launch;

public class BuckshotOracleApplication extends Application {
    @Override
    protected void configure(final Configuration config) {
        config.setTitle("Example Application");
    }


    @Override
    protected void initImGui(final Configuration config) {
        super.initImGui(config);

        final ImGuiIO io = ImGui.getIO();
        io.setIniFilename(null);                                // We don't want to save .ini file
        //io.addConfigFlags(ImGuiConfigFlags.NavEnableKeyboard);  // Enable Keyboard Controls
        io.addConfigFlags(ImGuiConfigFlags.DockingEnable);      // Enable Docking
        //io.addConfigFlags(ImGuiConfigFlags.ViewportsEnable);    // Enable Multi-Viewport / Platform Windows
        io.setConfigViewportsNoTaskBarIcon(true);


        // This enables FreeType font renderer, which is disabled by default.
        io.getFonts().setFreeTypeRenderer(true);

        // Add default font for latin glyphs
        io.getFonts().addFontDefault();
    }


    @Override
    public void process() {
        ImGui.setNextWindowPos(0,0, ImGuiCond.FirstUseEver);
        ImGui.setNextWindowSize(ImGui.getIO().getDisplaySize(), ImGuiCond.FirstUseEver);
        if (ImGui.begin("Demo", null, ImGuiWindowFlags.NoTitleBar | ImGuiWindowFlags.NoMove | ImGuiWindowFlags.NoResize)) {
            ImGui.text("OS: [" + System.getProperty("os.name") + "] Arch: [" + System.getProperty("os.arch") + "]");

        }
        ImGui.end();

        if(ImGui.begin("Another Window") ) {
            ImGui.text("Hello from another window!");
            if (ImGui.button("Close Me"))
                ImGui.closeCurrentPopup();
        }
        ImGui.end();

    }


    public static void main(String[] args) {
        launch(new BuckshotOracleApplication());
        System.exit(0);
    }
}
