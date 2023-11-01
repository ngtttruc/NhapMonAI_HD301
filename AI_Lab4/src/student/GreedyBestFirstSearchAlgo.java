package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyBestFirstSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByHn());
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(goal)) return current;
			else {
				explored.add(current);
				List<Edge> children = current.getChildren();
				for(Edge e : children) {
					Node child = e.getEnd();
					if(!frontier.contains(child) && !explored.contains(child)) {
						child.setParent(current);
						child.setG(current.getG()+e.getWeight());
						frontier.add(child);
					}
				}
			}
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		boolean started = false;
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByHn());
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while(!frontier.isEmpty()) {
			Node current = frontier.poll();
			if(current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				current.setParent(null);
				current.setG(0);	
			}
			if(current.getLabel().equals(goal) && started == true) return current;
			else {
				explored.add(current);
				List<Edge> children = current.getChildren();
				for(Edge e: children) {
					Node child = e.getEnd();
					if(!frontier.contains(child)&&!explored.contains(child)) {
						frontier.add(child);
						child.setParent(current);
						child.setG(current.getG()+e.getWeight());
					}
				}
			}
		}
		return null;
	}

	class NodeComparatorByHn implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			Double h1 = o1.getH();
			Double h2 = o2.getH();
			int result = h1.compareTo(h2);
			if (result == 0)
				return o1.getLabel().compareTo(o2.getLabel());
			else
				return result;
		}
	}

}
