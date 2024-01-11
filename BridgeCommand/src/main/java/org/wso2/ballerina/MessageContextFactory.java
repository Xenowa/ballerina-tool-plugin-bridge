package org.wso2.ballerina;

public class MessageContextFactory {
    private static String messageFromTool;

    public static void init(String messageFromTool){
        if(MessageContextFactory.messageFromTool == null){
            MessageContextFactory.messageFromTool = messageFromTool;
            System.out.println("Message set in factory!");
        }
    }

    public static String getContext(){
        return  messageFromTool;
    }
}
