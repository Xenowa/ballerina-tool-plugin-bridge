# Ballerina Tool & Plugin linking

```mermaid
sequenceDiagram
    %% For defining states, participants need to be defined
    participant BridgeCommand
    participant ToolAndCompilerPluginBridge Abstract Class
    participant Project API
    participant CustomCompilerPlugin
    
    BridgeCommand ->> ToolAndCompilerPluginBridge Abstract Class: Set a object through service loader
    activate BridgeCommand
    activate ToolAndCompilerPluginBridge Abstract Class
    deactivate ToolAndCompilerPluginBridge Abstract Class
    BridgeCommand ->> Project API: Perform compilation
    note right of ToolAndCompilerPluginBridge Abstract Class: In the current implementation static state seems to get lost
    activate Project API
    Project API ->> CustomCompilerPlugin: Trigger init method
    activate CustomCompilerPlugin
    CustomCompilerPlugin ->> ToolAndCompilerPluginBridge Abstract Class: Get static object through Bridge class extending the abstract class
    activate ToolAndCompilerPluginBridge Abstract Class
    ToolAndCompilerPluginBridge Abstract Class ->> CustomCompilerPlugin: Receive object
    deactivate ToolAndCompilerPluginBridge Abstract Class
    deactivate Project API
    deactivate CustomCompilerPlugin
    deactivate BridgeCommand
```

Ballerina language has two main extension points:
- Ballerina Tools
- Ballerina Compiler Plugins

This project aims to link a compiler plugin to a tool when a tool specific command is executed

## Ballerina Tools

Allow developers to create custom Ballerina commands like:
```cmd
bal bridge
```

## Ballerina Compiler Plugins

Allow developers to extend features of the Ballerina compiler
```bal
import org/custom_compiler_plugin as _;

public function main(){
}
```

## Prerequisites

The following software should be installed locally

- [Java version: 17](https://adoptium.net/temurin/releases/?version=17)
- [Ballerina version: 2201.8.2](https://ballerina.io/downloads/archived/#swan-lake-archived-versions)

## Getting started

1. Create the custom compiler plugin by following the
   instructions [here](https://github.com/Xenowa/ballerina-tool-plugin-bridge/tree/main/CustomCompilerPlugin)

2. Create the bal bridge tool by following the
   instructions [here](https://github.com/Xenowa/ballerina-tool-plugin-bridge/tree/main/BridgeCommand)

3. Navigate to the test-bridge-command directory

4. Run "bal bridge" command