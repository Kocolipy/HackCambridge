
package com.mygdx.game;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Learner {

    private static double bestPathAccuracy(Node current, AI ai, int tries, int correct){
        int score = 0;
        double confidence = 0;
        int numTimesLied = 0;
        double lyingconfidence = 0;
        for (int i =0;i < tries; i++) {
            ai.predict(current, current.getNeighbours());
            if (ai.recommendedPath.getId() == correct) {
                score++;
            }
            if (ai.lie){
                numTimesLied ++;
                lyingconfidence+=ai.confidence;
            } else {
                confidence += ai.confidence;
            }
        }
        //System.out.println("lying conf " + lyingconfidence/numTimesLied);
        //System.out.println("Truth conf " + confidence/(tries-numTimesLied));
        return ((double) score)/tries;
    }
    private static double[] mapEvaluate(Graph map, AI ai, int tries){
        double[] accuracies = new double[10];

        HashMap<Integer,Integer> correctPaths = new HashMap<Integer, Integer>();
        correctPaths.put(1,0);
        correctPaths.put(4,0);
        correctPaths.put(5,0);
        correctPaths.put(2,1);
        correctPaths.put(3,5);
        correctPaths.put(6,3);
        correctPaths.put(7,6);
        correctPaths.put(8,3);
        correctPaths.put(9,8);

        Deque<Node> queue = new LinkedList<Node>();
        queue.add(map.getNodes(0));
        while(!queue.isEmpty()){
            Node first = queue.remove();
            if (accuracies[first.getId()] == 0) {
                for (Node neighbour : first.getNeighbours()) {
                    queue.add(neighbour);
                }
                if (first.getId() == 0) {
                    accuracies[0] = 1;
                } else {
                    accuracies[first.getId()] = bestPathAccuracy(first, ai, tries, correctPaths.get(first.getId()));
                }
            }
        }
        return accuracies;
    }

    public static void main(String[] args){
        double[] variables = new double[]{1.0, 0.2, 0.05};

        AI ai = new AI(0.2, variables);
        Graph g;
        Node current;

        g = new Graph();
        g.run();
        double[] accuracies = mapEvaluate(g, ai, 10000);
        for (int i = 0;i < 10; i++){
            System.out.println("loc: " + i + ", " + accuracies[i]);
        }

/*
        for (int i=0;i<5;i++){
            g = new Graph();
            g.run();
            current = g.getNodes(4);
            System.out.println(bestPathAccuracy(current, ai, 10000, 0));
        }
*/
    }
}
