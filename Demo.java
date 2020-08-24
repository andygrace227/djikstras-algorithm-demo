package mapping_algorithm_segments;

public class Demo {
	public static void main(String[] args) {
		//Testing our classes with this graph:
		//		
		//		
		//		1---3-5---------7
		//		|  /             (3)
		//		2-4------------------6
		//		
		//We should get 1-7 Distance of 13
		
		NodeSegmentGraph graph = new NodeSegmentGraph();
		
		//make the nodes
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);
		graph.addNode(7);
		
		//make the segments
		graph.addSegment(1, 3, 3.0);
		graph.addSegment(1, 2, 1);
		graph.addSegment(2, 4, 1);
		graph.addSegment(4, 3, 1);
		graph.addSegment(3, 5, 1);
		graph.addSegment(5, 7, 9);
		graph.addSegment(4, 6, 13);
		graph.addSegment(6, 7, 3);
		
		
		//test how far away things are from one, and the quickest way to get there
		
		
		System.out.println(graph.getDirectionsFromTo(1, 7));
		
		//Now with this graph:
		//        8 \ \ \
		//       /       \ \ \ \
		//		1---3-5---------7
		//		|  /             \\\
		//		2-4------------------6
		//		
		//we should get 8 as a 1-7 distance
		
		graph.addNode(8);
		graph.addSegment(1, 8, 1);
		graph.addSegment(8, 7, 7);
		
		
		System.out.println(graph.getDirectionsFromTo(1, 7));
		
		//Now with this graph:
		//      8  ----(32)-----|
		//      |               |
		//		1---3-5---------7
		//		|  /            |
		//		2-4-6----(1)----|
		//		
		//we should get 8 as a 1-7 distance
		//	
		
		graph.changeSegment(4, 6, 1);
		graph.changeSegment(8, 7, 32);		
		System.out.println(graph.getDirectionsFromTo(1, 7));
		
		
		//Clear the graph.
		
		graph = new NodeSegmentGraph();
		graph.addNode(1);
		graph.addNode(2);
		graph.addNode(3);
		graph.addNode(4);
		graph.addNode(5);
		graph.addNode(6);
		graph.addNode(7);
		graph.addNode(8);
		graph.addNode(9);
		graph.addNode(10);
		graph.addNode(11);
		graph.addNode(12);
		graph.addNode(13);
		graph.addNode(14);
		graph.addNode(15);
		graph.addNode(16);
		
		//This time, we're gonna make this graph mean something
		//
		//Home = node 1
		//Grandma's house = node 16
		//This is a rough map of the roads and time it takes to travel them between my Grandma's house and mine
		//		  9 .58
		//		  +---+
		//		  |   | .5                 11    14        4                15
		//		  |  10--------------------+-----+--------------------------+
		//		  |   |         6          |  6  |                          |
		//		  |   |                    |     |                        4 |
		//		  |   |                    +-+   |                          |
		//		  |1  | 3                 8+-+   |                         1++
		//		  |   |                    |     |                          |
		//		  |   |                    |     |                        2 |
		//		  |   |              16    |12   |13                        |     6
		//		6 +---+--------------+--------------------------------------+---------------+ 3
		//		  |.5 7       2         2     1          2                  2               |
		//		  |                                                                         |
		//		  |                                                                       4 |
		//		  | 2                                                                       |
		//		  |                                  15                                     |
		//		5 +-------------------------------------------------------------------------+ 4
		//
		//We should get 7 minutes, which is right 
		
		graph.addSegment(1, 2, 2);
		graph.addSegment(2,13,2);
		graph.addSegment(13, 12, 1);
		graph.addSegment(12, 16, 2);
		graph.addSegment(16, 7, 2);
		graph.addSegment(7, 6, .5);
		graph.addSegment(6, 5, 2);
		graph.addSegment(5, 4, 15);
		graph.addSegment(4, 3, 4);
		graph.addSegment(3, 2, 6);
		graph.addSegment(15, 1, 4);
		graph.addSegment(15, 14, 4);
		graph.addSegment(14, 13, 4);
		graph.addSegment(14, 11, 6);
		graph.addSegment(12, 11, 8);
		graph.addSegment(10, 11, 6);
		graph.addSegment(10, 7, 3);
		graph.addSegment(10, 8, .5);
		graph.addSegment(8, 9, .25);
		graph.addSegment(9, 6, 1);
		
		
		System.out.println(graph.getDirectionsFromTo(1,16));
		
		//Now say the butcher is at node 10.
		//The quickest way would be 1-2-13-12-16-7-6-9-8-10
		
		System.out.println(graph.getDirectionsFromTo(1,10));
		
		//Go nuts with this code!!!
		
	}
}
