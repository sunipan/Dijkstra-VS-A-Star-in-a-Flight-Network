## 320-Project
Our own implementation of our designed algorithm to find the best flight in a flight network based on flight price, wait, and travel times.

## To Run
In order to run the flight pathfinder in an IDE, you must first change ther relative directory paths in the Airline, Airport, and Flight class as it is specific to each IDE. After that everything should work as intended.
In order to run the flight pathfinder, first compile all files using the command (in Command Prompt terminal)

```
javac *.java
```

Then run one of the two files below, depending on which algorithm you wish to run.

If you want to run Dijkstra, run

```
java BestPath
```

If you want to run A*, run

```
java AStar
```
NOTE: There is a possibility of a file Not found exception as the relative file-path used in Airline, Airport, and Flight classes does not port to every IDE.

You'll then be able to select the size of graph you wish to run, and then select the start and end airports using IATA codes (ex: YEG, YVR, YLW)

Alternatively, you can run the Benchmark.java file by uncommenting the specific test case you wish to run, and then running the file using

```
java Benchmark
```
