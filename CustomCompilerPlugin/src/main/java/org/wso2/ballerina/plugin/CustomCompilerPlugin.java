package org.wso2.ballerina.plugin;

import io.ballerina.projects.plugins.CompilerPlugin;
import io.ballerina.projects.plugins.CompilerPluginContext;
import org.wso2.ballerina.ToolAndCompilerPluginBridge;

public class CustomCompilerPlugin extends CompilerPlugin{


//    // Should be initialized before compiling
//    @Override
//    public void sendMessageFromTool(String messageFromTool) {
//
//    }

    // Runs during compilation
    @Override
    public void init(CompilerPluginContext pluginContext) {
        System.out.println("Custom Compiler Plugin sharing context engaged!");
        // System.out.println(messageFromTool);
        System.out.println(ToolAndCompilerPluginBridge.getMessageFromTool());
    }
}
