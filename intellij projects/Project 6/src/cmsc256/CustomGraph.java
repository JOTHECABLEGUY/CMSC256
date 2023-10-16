package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Jordan Dube
 * Carol Fung CMSC256 Project 6
 * 12/5/2020
 *
 * A class to represent a Graph data structure given a File
 * @param <V> the type parameter for the class that represents the Vertex
 */

public class CustomGraph<V> extends UnweightedGraph<V> {
  
  public CustomGraph() {
    super();
  }
  
  public CustomGraph(int[][] edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  public CustomGraph(List<Edge> edges, int numberOfVertices) {
    super(edges, numberOfVertices);
  }
  
  public CustomGraph(List<V> vertices, List<Edge> edges) {
    super(vertices, edges);
  }
  
  public CustomGraph(V[] vertices, int[][] edges) {
    super(vertices, edges);
  }
  
  /**
   * a method to determine whether the graph is connected
   * @return boolean true if graph is connected
   */
  public boolean isConnected() {
    return (this.vertices.size() == this.dfs(0).getNumberOfVerticesFound());
  }
  
  /**
   * Reads the input file to extract the graph data
   *
   * @param fileName The String name of the file
   * @return CustomGraph of data from the File
   * @throws FileNotFoundException, NumberFormatException
   * @throws NumberFormatException  if any lines are missing data
   */
  public CustomGraph<Integer> readFile(String fileName) throws FileNotFoundException, NumberFormatException {
    // TODO: complete method
    //creates file and scanner objects to retrieve the data
    try{
      File f = new File(fileName);
      Scanner s = new Scanner(f);
      //count variable to count the number of lines
      int count = 0;
      //creates a list to store the edges
      ArrayList<Edge> edges = new ArrayList<>();
    int numberOfVertices = 0;
    //iterates through the file and reads the data into the splitter
    while (s.hasNextLine()) {
      String line = s.nextLine();
      count++;
      //stores lines in an array
      String[] tokens = (line.split(" "));
      //if the current line is the first line, then the number of vertices is the number stored on the line
      if (count == 1) {
        try {
          numberOfVertices = Integer.parseInt(tokens[0]);
        } catch (NumberFormatException e) {
          throw new NumberFormatException();
        }
      }
      //if it is not the first line, takes the resulting split array and creates edge objects out of the ints, creating the edges array
      else {
        for (int i = 1; i < tokens.length; i++) {
          try {
            Edge edge = new Edge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            edges.add(edge);
          } catch (NumberFormatException e){
            throw new NumberFormatException();
          }
        }
      }
    }
    //resets the scanner
    s.reset();
    //returns a new customGraph object with the data from the file
    return new CustomGraph<>(edges, numberOfVertices);
    } catch (FileNotFoundException e){
      throw new FileNotFoundException();
    }
  }
  
  /**
   * a method to return a list of lists of Integer vertices that are connected
   * @return list of lists of Integer vertices that are connected
   */
  public List<List<Integer>> listConnectedComponents() {
    // TODO: complete method
    //creates 2 lists to hold the vertices and list of vertices
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    //adds vertices to the list of vertices as integers
    for (int i = 0; i < vertices.size(); i++) {
      temp.add((Integer) vertices.get(i));
    }
    //moves the connected components in to the list of lists and removes them from the temp
    while (temp.size() > 0) {
      result.add(dfs(temp.get(0)).getSearchOrder());
      temp.removeAll(dfs(temp.get(0)).getSearchOrder());
    }
    return result;
  }
  
  /**
   * a method to find the shortest path from the origin to the destination vertices
   * @param origin starting vertex (root of the SearchTree)
   * @param destination end of the desired path
   * @return a List of vertices that gives the shortest path from the start vertex to the finish
   */
  public List<Integer> getShortestPath(int origin, int destination) {
    // TODO: complete method
    // creates an ArrayList to store the path
    List<Integer> shortPath;
    //creates a tree of the graph to extract a path
    SearchTree tree = this.bfs(origin);
    //checks if there is a path to the destination
    if (tree.getPath(destination) == null)
      return null;
    else {
      //gets a path of vertices and casts it to Integer
      List<V> temp = bfs(destination).getPath(origin);
      shortPath = (List<Integer>) temp;
      return shortPath;
    }
  }
  
  /**
   * a method to determine if the graph has a cycle
   * @return true if the current graph has a cycle
   */
  public boolean hasCycle() {
    // TODO: complete method
    //initializes and populates an ArrayList with all vertices as Integer Objects
    List<Integer> allVertices = (List<Integer>) this.getVertices();
    //instantiates and populates the parents array with to avoid collisions in future iterations
    int[] parents = new int[this.vertices.size()];
    Arrays.fill(parents, -1);
    boolean[] isVisited = new boolean[vertices.size()];
    //checks the paths of all vertices to determine if there is a path present
    for (int j = 0; j < vertices.size(); j++) {
      //calls parameterized hasCycle method
      if (hasCycle(j, parents, allVertices, isVisited))
        return true;
    }
    return false;
  }
  
  /**
   * a method to determine if the graph has a cycle
   * @param u a starting point to begin a round of paths
   * @param parent an array of ints to keep track of the individual paths
   * @param allVertices a list of all vertices to be filtered through with each iteration
   * @param isVisited a boolean array to track which vertices have been visited
   * @return boolean true if the graph has a cycle
   */
  private boolean hasCycle(int u, int[] parent, List<Integer> allVertices, boolean[] isVisited) {
    // TODO: complete method
    //with each iteration, removes the vertex because it has been visited
    allVertices.remove((Integer) u);
    //changes the value in the array to true after the value has been visited
    isVisited[u] = true;
    //goes through each edge and if the vertices haven't been visited, updates it to reiterate with the new vertex as the center
    for (Edge e : neighbors.get(u)) {
      if (!isVisited[e.v]) {
        parent[e.v] = u;
        //recursively calls hasCycle
        return hasCycle(e.v, parent, allVertices, isVisited);
      } else if (isVisited[e.v] && parent[u] != e.v) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * a method to find a cycle starting with a given vertex
   * @param startingVertex the value of the first Integer vertex that starts the cycle
   * @return List<Integer> of vertices that list the vertices that make up a cycle
   */
  public List<Integer> findCycle(int startingVertex) {
    // TODO: complete method
    boolean[] isVisited = new boolean[this.vertices.size()];
    int[] parents = new int[this.vertices.size()];
    //2 ArrayLists to store all the vertices
    List<Integer> first = (List<Integer>) this.getVertices();
    List<Integer> second = (List<Integer>) this.getVertices();
    //checks if there is a cycle
    if (!hasCycle(startingVertex, parents, first, isVisited))
      return null;
    //removes all items in second list after hasCycle is called
    for (int j = 0; j < first.size(); j++) {
      second.remove(first.get(j));
    }
    //adds first element in cycle to close the loop
    second.add(startingVertex);
    return second;
  }
}
