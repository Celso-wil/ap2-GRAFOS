Class Buscas {
	public void BuscaEmLargura()
	{

		Queue queue = new LinkedList();
		queue.add(this.rootNode);
		printNode(this.rootNode);
		rootNode.visited = true;
		while(!queue.isEmpty()) {
			Node node = (Node)queue.remove();
			Node child=null;
			while((child=getUnvisitedChildNode(node))!=null) {
				child.visited=true;
				printNode(child);
				queue.add(child);
			}
		}
	
		clearNodes();
	}

	public void BuscaEmProfundidade() {

		Stack stack = new Stack();
		stack.push(this.rootNode);
		rootNode.visited=true;
		printNode(rootNode);
		while(!stack.isEmpty()) {
			Node node = (Node)s.peek();
			Node child = getUnvisitedChildNode(n);
			if(child != null) {
				child.visited = true;
				printNode(child);
				s.push(child);
			}
			else {
				s.pop();
			}
		}
		// Clear visited property of nodes
		clearNodes();
	}
}


Class Node {
   Char data;
   Public Node(char c) {
      this.data=c;
   }
}