# 320-Project
Our own implementation of our designed algorithm to find the best flight in a flight network based on flight price, wait, and travel times.

# To Run
In order to run the flight pathfinder, first compile all files using the command

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

You'll then be able to select the size of graph you wish to run, and then select the start and end airports using IATA codes (YEG, YVR, YLW)

Alternatively, you can run the Benchmark.java file by uncommenting the specific test case you wish to run, and then running the file using

```
java Benchmark
```
