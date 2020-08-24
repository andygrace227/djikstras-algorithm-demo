package mapping_algorithm_segments;

import java.util.HashMap;
import java.util.Map;

//This class is where the magic happens.

public class NodeSegmentGraph {
	
	//We store segments and nodes in maps (not road maps)
	//Maps store many values, each with a name.
	//When you do <mapname>.get(name), you get that value back.
	private Map<Integer, Segment> segmentMap = new HashMap<>();
	private Map<Integer, Node> nodeMap = new HashMap<>();
	
	//We want to build the graph from scratch.
	public NodeSegmentGraph() {
		
	}
	
	//Add a node.
	public void addNode(int id) {
		nodeMap.put(id, new Node(id));
	}

	//Add segment
	public void addSegment(int node1, int node2, double cost) {
		int id = segmentMap.size();
		
		//If there's already a segment with the same points, only change the cost.
		//Note you need to check if you got the order reversed...
		for(int segid : segmentMap.keySet()) {
			int segNode1 = segmentMap.get(segid).getNode1id();
			int segNode2 = segmentMap.get(segid).getNode2id();
			
			if(node1 == segNode1 && node2 == segNode2) {
				//If there's already one in here, just change the cost and return nothing
				segmentMap.get(segid).setCost(cost);
			}
			else if(node2 == segNode1 && node1 == segNode2) {
				//If there's already one in here, just change the cost and return nothing
				segmentMap.get(segid).setCost(cost);
			}
			else {
				continue;
			}
		}
		//If nothing's in there, add it back
		segmentMap.put(id, new Segment(id, node1, node2, cost));
		nodeMap.get(node1).addSegment(id);
		nodeMap.get(node2).addSegment(id);
	}
	
	public void changeSegment(int node1, int node2, double cost) {
		//Because map.put overrides data, this just passes data to addsegment
		addSegment(node1, node2, cost);
	}
	
	public Map<Integer, VisitingNode> getDistancesFromPoint(int a){
		//RECURSION BASICS
		//RECURSION: Using previous output values to generate new ones.
		//Example: fibbonacci numbers
		//f(0) = 1, f(1) = 1, f(n) = f(n-2) + f(n-1)
		//This is a little more complicated than Fibbonacci numbers...
		//We start off with a Visiting node made from node a.
		//We also make an array to store the costs from that node.
		Map<Integer, VisitingNode> distanceFromPoint = new HashMap<>();
		distanceFromPoint.put(a, new VisitingNode(nodeMap.get(a), 0, a + ""));
		
		//Next we recursviely build the map. Go down to RecursiveMapBuilder
		distanceFromPoint = RecursiveMapBuilder(a, distanceFromPoint);
		
		//So what just happened?
		//This:
		//Start -> Improve Neighbor -> Improve Neighbor -> Improve Neighbor ->... -> no more improvements
		// Complete CostMap <-pass back data <- pass back data <- pass back data <- pass back data <-+
		
		return distanceFromPoint;
	
	}
	
	public Map<Integer, VisitingNode> RecursiveMapBuilder(int currentID, Map<Integer, VisitingNode> distanceFromPoint){
		
		//Get the current node
		VisitingNode currentNode = distanceFromPoint.get(currentID);
		
		//Loop through its neighbors
		for(int segmentId : currentNode.getSegments()) {
		    
			//Precalculate any improvements to the cost
			
			double cost = segmentMap.get(segmentId).getCost();
			int neighborNodeId = segmentMap.get(segmentId).getOtherNode(currentID);
			double potentialCost = currentNode.getCost() + cost;
			
			//If we've already got an output value...
			if(distanceFromPoint.containsKey(neighborNodeId)) {
				//See if we can save any costs
				if(potentialCost < distanceFromPoint.get(neighborNodeId).getCost()) {
					
					//We can! Set the path through the current node to getting to the neighbor node!
					distanceFromPoint.get(neighborNodeId).setCost(potentialCost);
					distanceFromPoint.get(neighborNodeId).setPath(currentNode.getPath() + "-" + neighborNodeId);
				}
				else {
					//We can't save any costs on getting to this node, leave him alone
					continue;
				}
			}
			else {
				//We don't have a VisitingNode representing that node in the graph yet. Save the node to our little map of visited nodes!
				distanceFromPoint.put(neighborNodeId, new VisitingNode(nodeMap.get(neighborNodeId), potentialCost, currentNode.getPath() + "-" + neighborNodeId ));
			}
			

			//Now visit the neighbors, and do the same thing.
			//This will keep doing that until we can't improve costs anymore.
			//But all those costs are wasted unless we save them. So, we pass the current known map of nodes to the 
			//neighbors, tell it to improve costs for its neighbors (and so on), and then we take all those improvements
			//back, and merge them into one giant database of improvements
			distanceFromPoint = mergeMaps(distanceFromPoint, RecursiveMapBuilder(neighborNodeId, distanceFromPoint));

		}
		//Return the improved database. Go back to the function above
		return distanceFromPoint;
	}
	
	public static Map<Integer, VisitingNode> mergeMaps(Map<Integer, VisitingNode> main, Map<Integer, VisitingNode> side){
		
		//A quick and dirty function for putting improvements back into our map.
		for(int key : side.keySet()){
			if(main.containsKey(key)) {
				//Compare the two costs
				if(main.get(key).getCost() > side.get(key).getCost()) {
					main.replace(key, side.get(key));
				}
				else {
					continue;
				}
			}
			else {
				main.put(key, side.get(key));
			}
		}
		
		return main;
	}
	
	public String getDirectionsFromTo(int A, int B) {
		//All you got to do to get directions is find the visitingnode you want and get its path and time
		//This is just formatting code
		String output = "Directions from node "+ A +" to node " + B +":\n";
		output += getDistancesFromPoint(A).get(B).getPath() + "\n";
		output += "Distance: " + getDistancesFromPoint(A).get(B).getCost() + "\n";
		return output;
		
	}
	
}
