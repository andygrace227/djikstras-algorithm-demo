package mapping_algorithm_segments;

import java.util.ArrayList;
import java.util.List;

public class Node {
	//This class models a node/road intersection.
	//It has an ID and a list of segments/edges.
	//Nodes and segments will be stored as arraylists in other classes.
	private int id;
	
	private List<Integer> segments;
	//Constructors allow nodes to be created
	public Node(int id, List<Integer> segments) {
		super();
		this.id = id;
		this.segments = segments;
	}

	public Node(int id) {
		this.id = id;
		this.segments= new ArrayList<>();
	}
	
	//These methods allow you to check/set/read data in a controlled fashion
	public int getid() {
		return id;
	}
	
	public List<Integer> getSegments() {
		return segments;
	}

	public void setSegments(List<Integer> segments) {
		this.segments = segments;
	}

	
	public void addSegment(int segmentID) {
		this.segments.add(segmentID);
	}

}
