package org.wso2.ballerina;

import java.lang.reflect.InvocationTargetException;

public abstract class ToolAndCompilerPluginBridge {
    private static volatile String messageFromTool = null; // volatile makes sure each thread gets most up-to-date value

    // Method initiated by Ballerina Scan Tool
    public static synchronized void sendMessageFromTool(String messageFromTool) {
        if (ToolAndCompilerPluginBridge.messageFromTool == null) {
            ToolAndCompilerPluginBridge.messageFromTool = messageFromTool;
        }
    }

    // Method to be used by compiler plugins
    public static synchronized String getMessageFromTool() {
        if (messageFromTool == null) {
            // Retrieve URLClassLoader passed by tool from compiler plugins
            ClassLoader toolURLClassLoader = Thread.currentThread().getContextClassLoader();

            try {
                // Reflection is used since the class has been loaded by URLClassloader created during compilation
                messageFromTool = (String) toolURLClassLoader
                        .loadClass("org.wso2.ballerina.ToolAndCompilerPluginBridge")
                        .getMethod("getClassLoadedMessage")
                        .invoke(null);
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                System.out.println("Please run 'bal bridge' to engage custom rule compiler plugins!");
                return messageFromTool;
            }
        }

        return messageFromTool;
    }

    public static synchronized String getClassLoadedMessage() {
        return messageFromTool;
    }
}