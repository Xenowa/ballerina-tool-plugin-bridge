package org.wso2.ballerina;

public abstract class ToolAndCompilerPluginBridge {
     public static String messageFromTool = null;
     public void sendMessageFromTool(String messageFromTool){
          if(ToolAndCompilerPluginBridge.messageFromTool == null){
               ToolAndCompilerPluginBridge.messageFromTool = messageFromTool;
               System.out.println("Message set from tool: " + messageFromTool);
          }
     };
}