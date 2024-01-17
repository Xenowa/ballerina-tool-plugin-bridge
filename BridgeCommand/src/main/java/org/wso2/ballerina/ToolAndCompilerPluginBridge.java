package org.wso2.ballerina;

import io.ballerina.projects.plugins.CompilerPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ServiceLoader;

public abstract class ToolAndCompilerPluginBridge extends CompilerPlugin {
    private String messageFromTool = null;


    public String getClassLoadedMessage() {
        return messageFromTool;
    }

    // Method initiated by Ballerina Scan Tool
    public void sendMessageFromTool(String messageFromTool) {
        System.out.println("Message set from tool: " + messageFromTool);
        if (this.messageFromTool == null) {
            this.messageFromTool = messageFromTool;
        }
    }

    // Method to be used by compiler plugins
    public String getMessageFromTool() {
        if (messageFromTool == null) {
            // Retrieving URLClassLoader used by the tool using the Main Thread
            ClassLoader toolURLCLassLoader = Thread.currentThread().getContextClassLoader();
            try {
                Class<?> serviceInterface = toolURLCLassLoader.loadClass("org.wso2.ballerina.ToolAndCompilerPluginBridge");
                ServiceLoader<?> services = ServiceLoader.load(
                        serviceInterface,
                        toolURLCLassLoader);

                for (Object service : services) {
                    String toolLoadedCompilerPluginName = (String) service.getClass()
                            .getMethod("customRulesCompilerPluginName").invoke(service);

                    String compilerPluginName = customRulesCompilerPluginName();

                    if (toolLoadedCompilerPluginName.equals(compilerPluginName)) {
                        messageFromTool = (String) service.getClass()
                                .getMethod("getClassLoadedMessage").invoke(service);
                    }
                }
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     NoSuchMethodException e) {
                System.out.println("Please run 'bal bridge' to engage custom rule compiler plugins!");
                return messageFromTool;
            }
        }

        return messageFromTool;
    }

    public abstract String customRulesCompilerPluginName();
}