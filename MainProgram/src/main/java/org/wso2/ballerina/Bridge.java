package org.wso2.ballerina;

public abstract class Bridge {
    private static Reporter reporter = null;

    public void triggerReporter(String messageFromTool) {
        reporter.reportIssue(messageFromTool);
    }

    public void init(Reporter reporter) {
        if (Bridge.reporter == null) {
            Bridge.reporter = reporter;
        }
    }

    public abstract String getExtendedClassName();
}
