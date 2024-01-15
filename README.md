# Ballerina Tool & Plugin linking

```mermaid
sequenceDiagram
    participant Main Thread
    participant BridgeCommand
    participant ToolAndCompilerPluginBridge1
    participant Project API
    participant ToolAndCompilerPluginBridge2
    participant CustomCompilerPlugin
    activate Main Thread
    Main Thread ->> Main Thread: Load CustomToolClassLoader
    Main Thread ->> BridgeCommand: Start tool
    activate BridgeCommand
    BridgeCommand ->> ToolAndCompilerPluginBridge1: Set a object through Ballerina tool URLClassLoader
    activate ToolAndCompilerPluginBridge1
    deactivate ToolAndCompilerPluginBridge1
    BridgeCommand ->> Main Thread: Set Ballerina Tool URLClassLoader
    BridgeCommand ->> Project API: Perform package compilation
    activate Project API
    Project API ->> CustomCompilerPlugin: Trigger init method through Compiler plugin URLClassLoader
    activate CustomCompilerPlugin
    CustomCompilerPlugin ->> ToolAndCompilerPluginBridge2: Trigger get message method
    activate ToolAndCompilerPluginBridge2
    ToolAndCompilerPluginBridge2 ->> Main Thread: Get Tool URLClassLoader
    Main Thread ->> ToolAndCompilerPluginBridge1: Trigger get class loaded message method
    activate ToolAndCompilerPluginBridge1
    ToolAndCompilerPluginBridge1 ->> ToolAndCompilerPluginBridge2: Send object
    ToolAndCompilerPluginBridge2 ->> CustomCompilerPlugin: Send object
    deactivate ToolAndCompilerPluginBridge1
    deactivate ToolAndCompilerPluginBridge2
    deactivate CustomCompilerPlugin
    deactivate Project API
    deactivate BridgeCommand
    deactivate Main Thread
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
- [Ballerina version: 2201.8.4](https://ballerina.io/downloads/archived/#swan-lake-archived-versions)

## Getting started

1. Create the custom compiler plugin by following the
   instructions [here](https://github.com/Xenowa/ballerina-tool-plugin-bridge/tree/main/CustomCompilerPlugin)

2. Create the bal bridge tool by following the
   instructions [here](https://github.com/Xenowa/ballerina-tool-plugin-bridge/tree/main/BridgeCommand)

3. Navigate to the test-bridge-command directory

4. Run "bal bridge" command