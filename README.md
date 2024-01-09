# Ballerina Tool & Plugin linking

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

1. Create the bal bridge tool by following the
   instructions [here](https://github.com/Xenowa/ballerina-tool-plugin-bridge/tree/main/BridgeCommand)

2. Create the custom compiler plugin by following the
   instructions [here](https://github.com/Xenowa/ballerina-tool-plugin-bridge/tree/main/CustomCompilerPlugin)

3. Navigate to the test-bridge-command directory

4. Run "bal bridge" command