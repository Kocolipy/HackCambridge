
package com.mygdx.game;

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
        System.out.println("lying conf " + lyingconfidence/numTimesLied);
        System.out.println("Truth conf " + confidence/(tries-numTimesLied));
        return ((double) score)/tries;
    }
    /*
    private double[] mapEvaluate(Map map, AI ai, int tries){
        double[] accuracies = new double[10];

        Deque<Node> queue = new LinkedList<Node>();
        queue.add(map.current());
        while(!queue.isEmpty()){
            Node first = queue.remove();

        }

        if (current.getId() != 0){

        }



    }*/

    public static void main(String[] args){
        double[] variables = new double[]{1.0, 0.2, 0.05};

        AI ai = new AI(0.2, variables);
        Graph g;
        Node current;

        for (int i=0;i<5;i++){
            g = new Graph();
            g.run();
            current = g.getNodes().get(3);
            System.out.println(bestPathAccuracy(current, ai, 10000, 5));
        }
    }
}
