package org.wso2.ballerina;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        ServiceLoader<Bridge> services = ServiceLoader.load(Bridge.class);
        String message = "Hello Ballerina";

        // Setting the messages to the loaded services
        for (Bridge service : services) {
            service.setMessage(message);
            System.out.println("Message set: " + service.getMessage());
        }

        // Retrieving the messages
        ServiceLoader<Bridge> services2 = ServiceLoader.load(Bridge.class);
        for (Bridge service : services2) {
            System.out.println("Message retrieved: " + service.getMessage());
        }
    }
}