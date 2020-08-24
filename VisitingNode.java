package mapping_algorithm_segments;

public class VisitingNode extends Node{
	//When you are calculating costs from a point, you need additional data like the cost to that point and the path to get there
	//This class is an extension of the node class, and adds the total cost to getting there from a point, and a path.
	
	private double cost = 0;
	
	private String path = "";
	
	public VisitingNode(Node node) {
		super(node.getid(), node.getSegments());
		
	}

	public VisitingNode(Node node, double cost, String path) {
		super(node.getid(), node.getSegments());
		this.cost = cost;
		this.path = path;
	}
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String toString() {
		return "Node ID: " + super.getid() + "\nCost: " + cost + "\nPath:" + path + "\n";
	}
}
