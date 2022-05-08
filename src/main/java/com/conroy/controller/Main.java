package com.conroy.controller;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

    ArrayList<String> dictionary;
    List<List<Character>> grid;
    TreeSet<String> matchedSet = new TreeSet<>(); // records all the words formed by 'getMatch' function,
                                                  // and these words match to the dictionary

    public static void main(String[] args) {

        ArrayList<String> dictionary = new ArrayList<>(); //record the element that needed, can just modify this for test
        dictionary.add("cat");
        dictionary.add("crater");
        dictionary.add("one");
        dictionary.add("tar");
        dictionary.add("tate");
        dictionary.add("car");
        dictionary.add("eat");
        dictionary.add("tan");

        List<List<Character>> grid = new ArrayList<>(); //record the grid content, can just modify this for test
        grid.add(Arrays.asList('C', 'A','T'));
        grid.add(Arrays.asList('R','R','E'));
        grid.add(Arrays.asList('Z', 'O', 'N'));

        //example of calling app
        Main main = new Main(dictionary, grid);
        main.callMatchesGetter(grid);
        main.showResult();
    }

    /**
     * Constructor
     * @param dictionary dictionary records the elements which must match
     * @param grid grid that consists of character elements
     */
    public Main(ArrayList<String> dictionary, List<List<Character>> grid){
        this.grid = grid;
        this.dictionary = dictionary;
    }


    /**
     * Call the match function for each cell to begin the word listing
     * @param grid
     */
    private void callMatchesGetter(List<List<Character>> grid){
        IntStream.range(0, grid.size()).forEach(y->{
            IntStream.range(0,grid.get(y).size()).forEach(x->{
                getMatch(grid,y,x,"","");
            });
        });
    }

    /**
     * Generate a Grid for demo
     * @return grid
     */
    private List<List<Character>> generateGrid() {
        List<List<Character>> grid = new ArrayList<>();
        grid.add(Arrays.asList('C', 'A','T'));
        grid.add(Arrays.asList('R','R','E'));
        grid.add(Arrays.asList('Z', 'O', 'N'));
        return grid;
    }

    /**
     * Print the result to show words listed by the function and they match to the dictionary
     */
    private void showResult(){
        System.out.println(matchedSet);
    }

    /**
     * Recursively look adjacent cell to form arbitrary words
     * @param grid Grid that consists of characters
     * @param y y-axis
     * @param x x-axis
     * @param joinedWord The arbitrary word formed by this function
     * @param prevPoints The points visited by the function
     */
    private void getMatch(List<List<Character>> grid, int y, int x, String joinedWord, String prevPoints) {
        if (x < 0 || y < 0 || y >= grid.size() || x >= grid.get(y).size()) // stop recursive when out of grid range
            return;
        if (prevPoints.matches(".*#"+x+","+y+"#.*") ) //stop recursive if reach visited points
            return;

        joinedWord = (joinedWord + grid.get(y).get(x)).toLowerCase();

        if(dictionary.contains(joinedWord)){
            matchedSet.add(joinedWord);
        }
        String currentVisitPoints = "#"+prevPoints + "#" + x+","+y + "#"; // this string records visited points
        getMatch(grid, y - 0, x - 1, joinedWord, currentVisitPoints);
        getMatch(grid, y + 1, x + 0, joinedWord, currentVisitPoints);
        getMatch(grid, y - 1, x - 0, joinedWord, currentVisitPoints);
        getMatch(grid, y + 0, x + 1, joinedWord, currentVisitPoints);
    }
}
