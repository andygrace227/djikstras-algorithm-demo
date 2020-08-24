package mapping_algorithm_segments;

public class Segment {
	//Segments represent edges/roads.
	//Segments have more data than this in production navigation systems, such as lanes, distance, coordinates, one-way direction, or traffic data
	//But these basics get the job done
	//You have an ID, a node, and another node, and a cost value.
	//You have methods to create, change, and read these values
	private int id;
	private int node1id;
	private int node2id;
	private double cost;
	
	public Segment(int id, int node1id, int node2id, double cost) {
		super();
		this.node1id = node1id;
		this.node2id = node2id;
		this.cost = cost;
		this.id = id;
	}
	
	public int getNode1id() {
		return node1id;
	}
	public void setNode1id(int node1id) {
		this.node1id = node1id;
	}
	public int getNode2id() {
		return node2id;
	}
	public void setNode2id(int node2id) {
		this.node2id = node2id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public int getOtherNode(int knownId) {
		if(knownId == node1id) {
			return node2id;
		}
		else {
			return node1id;
		}
		
	}
	
	
	
	public String toString() {
		return node1id +"--" + cost + "--" + node2id;
	}
	
}
