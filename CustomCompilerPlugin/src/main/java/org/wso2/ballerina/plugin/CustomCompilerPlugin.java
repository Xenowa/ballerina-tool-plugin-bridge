package org.wso2.ballerina.plugin;

import io.ballerina.projects.plugins.CompilerPlugin;
import io.ballerina.projects.plugins.CompilerPluginContext;
import org.wso2.ballerina.ToolAndCompilerPluginBridge;

public class CustomCompilerPlugin extends CompilerPlugin implements ToolAndCompilerPluginBridge {
    String messageFromTool = null;

    // Should be initialized before compiling
    @Override
    public void sendMessageFromTool(String messageFromTool) {
        this.messageFromTool = messageFromTool;
    }

    // Runs during compilation
    @Override
    public void init(CompilerPluginContext pluginContext) {
        System.out.println("Custom Compiler Plugin sharing context engaged!");
        System.out.println(messageFromTool);
    }
}
