package org.wso2.ballerina.plugin;

import org.wso2.ballerina.ToolAndCompilerPluginBridge;

public class CustomCompilerPlugin implements ToolAndCompilerPluginBridge{
    // Engaged via tool
    @Override
    public void sendMessageFromTool(String messageFromTool) {
        System.out.println(messageFromTool);
    }
}
