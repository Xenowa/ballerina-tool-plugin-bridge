package org.wso2.ballerina.plugin;

import io.ballerina.projects.plugins.CompilerPluginContext;
import org.wso2.ballerina.ToolAndCompilerPluginBridge;

public class CustomCompilerPlugin extends ToolAndCompilerPluginBridge {
    // Runs during compilation
    @Override
    public void init(CompilerPluginContext pluginContext) {
        System.out.println("Service Loaded message: " + getMessageFromTool());
    }

    @Override
    public String customRulesCompilerPluginName() {
        return "CustomCompilerPlugin";
    }
}
