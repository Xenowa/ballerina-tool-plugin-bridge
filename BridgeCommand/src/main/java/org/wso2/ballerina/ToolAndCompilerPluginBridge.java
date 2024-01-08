package org.wso2.ballerina;

public abstract class ToolAndCompilerPluginBridge {
    private static String messageFromTool = null;

    public static String getMessageFromTool(){
        return messageFromTool;
    }
}