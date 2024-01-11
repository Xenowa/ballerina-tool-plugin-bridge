package org.wso2.ballerina.plugin;

import io.ballerina.projects.plugins.CompilerPlugin;
import io.ballerina.projects.plugins.CompilerPluginContext;
import org.wso2.ballerina.ToolAndCompilerPluginBridge;

public class CustomCompilerPlugin extends CompilerPlugin implements ToolAndCompilerPluginBridge {
    @Override
    public void init(CompilerPluginContext compilerPluginContext) {
        System.out.println("Service Loaded Message: " + messageFromTool);
    }
}
