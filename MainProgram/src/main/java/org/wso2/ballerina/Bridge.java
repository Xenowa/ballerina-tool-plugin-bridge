package org.wso2.ballerina;

public class Bridge {
    private static final Bridge INSTANCE = new Bridge();
    private Reporter reporter = null;

    public void triggerReporter(String messageFromTool) {
        INSTANCE.reporter.reportIssue(messageFromTool);
    }

    public void init(Reporter reporter) {
        if (INSTANCE.reporter == null) {
            INSTANCE.reporter = reporter;
        }
    }

    public String getExtendedClassName() {
        return null;
    }
}
