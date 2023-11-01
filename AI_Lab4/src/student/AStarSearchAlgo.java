package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStarSearchAlgo implements IInformedSearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByHnGn());
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(goal))
				return current;
			else {
				explored.add(current);
				List<Edge> children = current.getChildren();
				for (Edge e : children) {
					Node child = e.getEnd();
					if (!frontier.contains(child) && !explored.contains(child)) {
						child.setParent(current);
						child.setG(current.getG() + e.getWeight());
						frontier.add(child);
					} else if (frontier.contains(child) && child.getG() > current.getG() + e.getWeight()) {
						frontier.remove(child);
						child.setParent(current);
						child.setG(current.getG() + e.getWeight());
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
		PriorityQueue<Node> frontier = new PriorityQueue<>(new NodeComparatorByHnGn());
		frontier.add(root);
		List<Node> explored = new ArrayList<>();
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();
			if (current.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				explored.clear();
				current.setParent(null);
				current.setG(0);
			}

			if (current.getLabel().equals(goal) && started == true)
				return current;
			else {
				explored.add(current);
				List<Edge> children = current.getChildren();
				for (Edge e : children) {
					Node child = e.getEnd();
					if (!frontier.contains(child) && !explored.contains(child)) {
						child.setParent(current);
						child.setG(current.getG() + e.getWeight());
						frontier.add(child);
					} else if (frontier.contains(child) && child.getG() > current.getG() + e.getWeight()) {
						frontier.remove(child);
						child.setParent(current);
						child.setG(current.getG() + e.getWeight());
						frontier.add(child);
					}
				}
			}

		}
		return null;
	}

	public boolean isAdmissibleH(Node root, String goal) {
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<Node>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node current = frontier.poll();

			double h_ = execute(current, goal).getG() - current.getG();
			System.out.println("h: " + current.getLabel() + current.getH());
			System.out.println("h*: " + current.getLabel() + h_);

			if ((!(current.getH() >= 0)) || (!(current.getH() <= h_))) {
				System.out.println("NOT_ADMISSIBLE_H");
				return false;
			} else {
				for (Node node : current.getChildrenNodes()) {
					if ((!(frontier.contains(node))) && (!(explored.contains(node)))) {
						frontier.add(node);

					}
				}
			}
		}
		System.out.println("IS ADMISSIBLE_H");
		return true;
	}

	class NodeComparatorByHnGn implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			Double h1a = o1.getH() + o1.getG();
			Double h2a = o2.getH() + o2.getG();
			int result = h1a.compareTo(h2a);
			if (result == 0)
				return o1.getLabel().compareTo(o2.getLabel());
			else
				return result;
		}
	}

}
