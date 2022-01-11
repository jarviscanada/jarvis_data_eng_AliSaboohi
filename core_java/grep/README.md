# Introduction
Designed and developed a java 8 maven structured application in order to recreate a simplified version of Linux grep command. The program takes a pattern, a root directory, and an output file as arguments and then, proceeds to recursively search the directory to find all the files and find the user-given pattern in all the lines. Furthermore, when lines are found the program writes the lines to an output file. The main objective of this project was to get comfortable with technologies such as maven, java, lambda, stream API and regular expressions. The program is written in two ways: one takes advantage of BufferedReader class and the other uses Lambda and stream API.

# Quick Start
`java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp .*Romeo.*Juliet.* ./data ./out/grep.txt`

- `ca.jrvs.apps.grep.JavaGrepImp`: Which class to use.
- `.*Romeo.*Juliet.*`: Regex pattern.
- `./data`: Root directory
- `./out/grep.txt`: Output file.

# Implemenation
## Pseudocode
```java
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```
The program is written using loops and streams which both utilize the same process method.

## Performance Issue
Since loading all the files inside a given root directory can become massive, the program makes use of the BufferedReader which limits memory usage and prevents loading everything at once in memory. Additionally, this can be done using stream API which processes large amount of data efficiently.

# Test
All the methods have been tested one by one using IntelliJ debugger and sample data. The listFiles() method specially has been tested given various directory structures.

# Deployment
An Image has been created off the program and is deployed on Docker Hub.

# Improvement
- Adding more functionality.
- Separate main method from the Implementation classes.
- Make it more user friendly.
