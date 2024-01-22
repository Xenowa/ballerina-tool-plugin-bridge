package org.wso2.ballerina;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        serviceLoaderBasedApproach();
    }

    public static void serviceLoaderBasedApproach() {
        ServiceLoader<Bridge> services = ServiceLoader.load(Bridge.class);
        Reporter localReporter = new Reporter();

        // Setting the messages to the loaded services
        for (Bridge service : services) {
            service.init(localReporter);
        }

        // Retrieving the messages
        ServiceLoader<Bridge> services2 = ServiceLoader.load(Bridge.class);
        for (Bridge service : services2) {
            service.triggerReporter("Message sent from the tool!");
        }
    }
}