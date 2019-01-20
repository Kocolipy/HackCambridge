package com.mygdx.game;

import java.util.HashMap;


// import Graph class and Node class
public class GameController {
	int playerPosition;
	int exitPosition;
	Graph g;
	public AI bot1;;
	public AI bot2;;
	public AI bot3;;
	public AI bot4;
	Node recommendation1;
	Node recommendation2;
	Node recommendation3;
	Node recommendation4;
	double confidence1;
	double confidence2;
	double confidence3;
	double confidence4;
	
	public GameController() {
		this.exitPosition = 0;
		this.playerPosition = 9;
		
		// generate graph and map
		this.g = new Graph();
		g.run();
		
		this.bot1 = new AI(0.0);
		this.bot2 = new AI(0.2);
		this.bot3 = new AI(0.4);
		this.bot4 = new AI(0.6);
		
	}
	public boolean isExitNode() {
		if(playerPosition == exitPosition) {
			return true;
		}else {
			return false;
		}
	}
	public void update(Node chosenNode) {
		this.playerPosition = chosenNode.getId();
		if(isExitNode()) {
			// win the game
			return;
		}
		if(g.getNode(playerPosition).getNeighbours().size() == 1) {
			// deadend
			return;
		}

		bot1.predict(g.getNode(playerPosition), g.getNode(playerPosition).getNeighbours());
		recommendation1 = bot1.getRecommendedPath();
		confidence1 = bot1.getProbability();
		//recommendation1.getId();
		
		bot2.predict(g.getNode(playerPosition), g.getNode(playerPosition).getNeighbours());
		recommendation2 = bot2.getRecommendedPath();
		confidence2 = bot2.getProbability();
		
		bot3.predict(g.getNode(playerPosition), g.getNode(playerPosition).getNeighbours());
		recommendation3 = bot3.getRecommendedPath();
		confidence3 = bot3.getProbability();
		
		bot4.predict(g.getNode(playerPosition), g.getNode(playerPosition).getNeighbours());
		recommendation4 = bot4.getRecommendedPath();
		confidence4 = bot4.getProbability();
		
	}


	//AI.predict(...)
	//AI.getRecommendedPath(); returns node that is recommended
	//AI.getProbability(); confidence interval
}
