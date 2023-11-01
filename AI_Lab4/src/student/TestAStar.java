package student;

public class TestAStar {
	public static void main(String[] args) {
		Node s = new Node("S", 6);
		Node b = new Node("B", 4);
		Node a = new Node("A", 4);
		Node c = new Node("C", 4);
		Node d = new Node("D", 3.5);
		Node e = new Node("E", 1);
		Node f = new Node("F", 1);
		Node g = new Node("G", 0);
		
		s.addEdge(b, 3);
		s.addEdge(a, 2);
		a.addEdge(c, 3);
		b.addEdge(d, 3);
		b.addEdge(c, 1);
		c.addEdge(e, 3);
		c.addEdge(d, 1);
		d.addEdge(f, 2);
		f.addEdge(g, 1);
		e.addEdge(g, 2);
		
		//test part
//		IInformedSearchAlgo gbf = new GreedyBestFirstSearchAlgo();
//		Node res = gbf.execute(s, g.getLabel());
//		System.out.println(NodeUtils.printPath(res));
//		System.out.println(res.getG());
//		
//		Node res2 = gbf.execute(s, b.getLabel(), g.getLabel());
//		System.out.println(NodeUtils.printPath(res2));
//		System.out.println(res2.getG());
		
		IInformedSearchAlgo aStar = new AStarSearchAlgo();
//		Node res1 = aStar.execute(s, g.getLabel());
//		System.out.println(NodeUtils.printPath(res1));
//		System.out.println(res1.getG());
		
		Node res3 = aStar.execute(s, b.getLabel(), g.getLabel());
		System.out.println(NodeUtils.printPath(res3));
		System.out.println(res3.getG());
		
		System.out.println("==============");
		System.out.println("Admissible : " + ((AStarSearchAlgo) aStar).isAdmissibleH(s, g.getLabel()) + "\n");
		
	}
}
