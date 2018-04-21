package com.svarttand.ld41.misc;

import java.util.ArrayList;
import java.util.LinkedList;

import com.svarttand.ld41.world.Tile;

public class PathFinding {
	
	public static LinkedList<Tile> calculateRoute(Tile start, Tile dest){
		LinkedList<Tile> movmentPath = new LinkedList<Tile>();
		
		ArrayList<Tile> closedSet = new ArrayList<Tile>();
		ArrayList<Tile> openSet = new ArrayList<Tile>();
		
		openSet.add(start);
		//start.setParent(null);
		
		while(openSet.size() > 0){
			Tile current = getWinner(openSet, dest);
			if (current.isSame(dest)) {
				while(current != null){
					movmentPath.add(current);
				}
				break;
			}
			openSet.remove(current);
			closedSet.add(current);
			
			for (int i = 0; i < current.getNeighbours().size(); i++) {
				if (current.getNeighbours().get(i).isPassable()) {
					boolean alreadyCounted = false;
					for (int j = 0; j < closedSet.size(); j++) {
						if (closedSet.get(j).isSame(current.getNeighbours().get(i))) {
							alreadyCounted = true;
							break;
						}
					}
					if (!alreadyCounted) {
						for (int j = 0; j < closedSet.size(); j++) {
							if (closedSet.get(j).isSame(current.getNeighbours().get(i))) {
								alreadyCounted = true;
								break;
							}
						}
						if (!alreadyCounted) {
							openSet.add(current.getNeighbours().get(i));
							current.getNeighbours().get(i).setParent(current);
						}
					}
				}else{
					closedSet.add(current.getNeighbours().get(i));
				}
				
			}
		}
		//movmentPath.removeLast();
		
		
		return movmentPath;
	}
	
	private static Tile getWinner(ArrayList<Tile> openSet, Tile dest) {
		Tile temp = openSet.get(0);
		for (int i = 0; i < openSet.size(); i++) {
			if (getFscore(temp, dest)> getFscore(openSet.get(i),dest)) {
				temp = openSet.get(i);
			}
		}
		return temp;
	}
	
	private static float getFscore(Tile temp, Tile dest) {
		int gScore = 0;
		float hScore = calculateDistance(dest, temp);
		while(temp.getParent() != null){
			//gScore += temp.getType().getMovmentCost();
			temp = temp.getParent();
		}
		return gScore + hScore;
	}
	
	private static float calculateDistance(Tile dest, Tile current){
		float x = Math.abs(current.getPosX() - dest.getPosX());
		float y = Math.abs(current.getPosY() - dest.getPosY());
		
		float distance = (float) Math.sqrt(Math.pow(x, 2)+ Math.pow(y, 2));
		return distance;
		
	}


}
