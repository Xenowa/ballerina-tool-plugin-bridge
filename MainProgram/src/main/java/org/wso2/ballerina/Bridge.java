package org.wso2.ballerina;

public abstract class Bridge {
    private static Reporter reporter = null;

    public void triggerReporter(String messageFromTool) {
        reporter.reportIssue(messageFromTool);
    }

    public void init(Reporter reporter) {
        this.reporter = reporter;
    }

    public abstract String getExtendedClassName();
}
