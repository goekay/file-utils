# file-utils

file-utils is a collection of simple command line tools.

## Requirements

Java 8

## Usage

    Usage: file-utils [options]
      Options:
      * -d, --dir
           The directories to traverse
      * -o, --operation
           Specifies the operation to execute. 'list' will print all the files in
           the given directories. 'diff' will compare and diff the files in the given
           _two_ directories. In the latter case, the number must be exactly two.
           Default: list
           Possible Values: [list, diff]
        -r, --report
           Specifies how the results should be reported at the end. 'file' should be
           used if there will be a lot to print, since 'console' cannot cope with that
           much data output.
           Default: console
           Possible Values: [console, file]


Examples:

    # java -jar file-utils-X.X.X.jar -o list -d "D:\backup\pictures\2015" -r file
    # java -jar file-utils-X.X.X.jar -o diff -d "D:\backup\pictures\2015" "C:\pictures\2015"

