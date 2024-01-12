package org.wso2.ballerina;

import io.ballerina.cli.BLauncherCmd;
import io.ballerina.projects.Module;
import io.ballerina.projects.Project;
import io.ballerina.projects.directory.ProjectLoader;
import io.ballerina.projects.util.ProjectConstants;
import picocli.CommandLine;

import java.io.File;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

@CommandLine.Command(name = "bridge", description = "Link with compiler plugins")
public class BridgeCommand implements BLauncherCmd {
    // =============================
    // Ballerina Launcher Attributes
    // =============================
    private final PrintStream outputStream;
    private final PrintStream errorStream;
    @CommandLine.Parameters(description = "Program arguments")
    private final List<String> argList = new ArrayList<>();
    private String projectPath;
    @CommandLine.Option(names = {"--help", "-h", "?"}, hidden = true)
    private boolean helpFlag;

    public BridgeCommand() {
        this.outputStream = System.out;
        this.errorStream = System.err;
    }

    public BridgeCommand(PrintStream outputStream) {
        this.outputStream = outputStream;
        this.errorStream = outputStream;
    }


    // =====================
    // bal help INFO Methods
    // =====================
    @Override
    public String getName() {
        return "bridge";
    }

    @Override
    public void printLongDesc(StringBuilder out) {
        out.append("Tool for linking Ballerina compiler plugins\n\n");
        out.append("bal bridge <ballerina-file>\n\n");
    }

    @Override
    public void printUsage(StringBuilder out) {
        out.append("Tool for linking Ballerina compiler plugins");
    }

    @Override
    public void setParentCmdParser(CommandLine parentCmdParser) {
    }

    // ====================
    // Main Program Methods
    // ====================
    // MAIN method
    @Override
    public void execute() {
        // if bal scan --help is passed
        if (helpFlag) {
            StringBuilder builder = new StringBuilder();
            builder.append("Tool for linking Ballerina compiler plugins\n\n");
            this.outputStream.println(builder);
            return;
        }

        // Simulate loading a project and engaging a compiler plugin
        String userPath = checkPath();

        // Terminate program if the path is invalid
        if (userPath == null) {
            return;
        }

        // Get access to the project API
        Project project = ProjectLoader.loadProject(Path.of(userPath));

        // Iterate through each module of the project
        project.currentPackage().moduleIds().forEach(moduleId -> {
            // Get access to the project modules
            Module module = project.currentPackage().module(moduleId);

            // Load the compiler plugin
            URLClassLoader externalJarClassLoader = getUrlClassLoader();

            try {
                // Read common interface implementations
                ServiceLoader<ToolAndCompilerPluginBridge> externalScannerJars = ServiceLoader.load(
                        ToolAndCompilerPluginBridge.class, externalJarClassLoader);

                // Iterate through the loaded interfaces
                String messageFromTool = "Sent from Ballerina Scan Tool";

                for (ToolAndCompilerPluginBridge externalScannerJar : externalScannerJars) {
                    // Call the interface method and pass a context
                    externalScannerJar.sendMessageFromTool(messageFromTool);
                }

                if (module.isDefaultModule()) {
                    // Compile the project and engage the plugin once
                    // If context has been passed correctly it will be displayed in the console
                    project.currentPackage().getCompilation();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

        outputStream.println("bridge successful!");
    }

    private URLClassLoader getUrlClassLoader() {
        URL jarUrl;

        try {
            jarUrl = new File("C:\\Users\\Tharana Wanigaratne\\.ballerina\\repositories\\central.ballerina.io\\bala\\tharana_wanigaratne\\custom_compiler_plugin\\0.1.0\\java17\\compiler-plugin\\libs\\CustomCompilerPlugin-1.0-all.jar")
                    .toURI()
                    .toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        URLClassLoader externalJarClassLoader = new URLClassLoader(new URL[]{jarUrl}, this.getClass().getClassLoader());
        return externalJarClassLoader;
    }

    public String checkPath() {
        // retrieve the user passed argument or the current working directory
        this.projectPath = argList.isEmpty() ? null : String.valueOf(Paths.get(argList.get(0)));
        String userFilePath = this.projectPath != null ? this.projectPath : System.getProperty("user.dir");

        // Check if the user provided path is a file or a directory
        File file = new File(userFilePath);
        if (file.exists()) {
            if (file.isFile()) {
                // Check if the file extension is '.bal'
                if (!userFilePath.endsWith(ProjectConstants.BLANG_SOURCE_EXT)) {
                    this.outputStream.println("Invalid file format received!\n File format should be of type '.bal'");
                    return null;
                } else {
                    // Perform check if the user has provided the file in "./balFileName.bal" format and if so remove
                    // the trailing slash
                    if (userFilePath.startsWith("./") || userFilePath.startsWith(".\\")) {
                        userFilePath = userFilePath.substring(2);
                    }

                    return userFilePath;
                }
            } else {
                // If it's a directory, validate it's a ballerina build project
                File ballerinaTomlFile = new File(userFilePath, ProjectConstants.BALLERINA_TOML);
                if (!ballerinaTomlFile.exists() || !ballerinaTomlFile.isFile()) {
                    this.outputStream.println("ballerina: Invalid Ballerina package directory: " +
                            userFilePath +
                            ", cannot find 'Ballerina.toml' file.");
                    return null;
                } else {
                    // Following is to mitigate the issue when "." is encountered in the scanning process
                    if (userFilePath.equals(".")) {
                        return Path.of(userFilePath)
                                .toAbsolutePath()
                                .getParent()
                                .toString();
                    }

                    return userFilePath;
                }
            }
        } else {
            this.outputStream.println("No such file or directory exists!\n Please check the file path and" +
                    "then re-run the command.");
            return null;
        }
    }
}