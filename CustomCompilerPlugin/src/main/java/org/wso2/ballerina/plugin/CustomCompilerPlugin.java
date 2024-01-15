package org.wso2.ballerina.plugin;

import io.ballerina.projects.plugins.CompilerPlugin;
import io.ballerina.projects.plugins.CompilerPluginContext;

public class CustomCompilerPlugin extends CompilerPlugin {
    // Runs during compilation
    @Override
    public void init(CompilerPluginContext pluginContext) {
        System.out.println("Custom Compiler Plugin sharing context engaged!");

        System.out.println("Service Loaded message: " + Bridge.getMessageFromTool());
    }
}
