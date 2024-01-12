package org.wso2.ballerina.plugin;

import io.ballerina.projects.plugins.CompilerPlugin;
import io.ballerina.projects.plugins.CompilerPluginContext;

public class CustomCompilerPlugin extends CompilerPlugin{
    @Override
    public void init(CompilerPluginContext compilerPluginContext) {
        System.out.println("Service Loaded Message: " + Bridge.messageFromTool);
    }
}
