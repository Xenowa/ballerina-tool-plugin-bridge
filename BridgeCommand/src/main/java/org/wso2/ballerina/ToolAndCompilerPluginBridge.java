package org.wso2.ballerina;

public abstract class ToolAndCompilerPluginBridge {
    private static String messageFromTool = null;
    // void sendMessageFromTool(String messageFromTool);
    public static String getMessageFromTool(){
        return messageFromTool;
    }
}