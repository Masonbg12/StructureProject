// PRACTICE WITH LINKED LISTS

// LinkedList class
public class LinkedList {
	// data fields
	Node head;
	int size;
	
	// default constructor
	public LinkedList() {
		this.size = 0;
		head = null;
	}
	
	// method returns the first letter of the String
	String getFirstLetter(String name) {
		return ("" + name.charAt(0)).toUpperCase();
	}
	
	// add method adds a new node to the linked list
	void add(String name) {
		// case where the linked list is empty
		if (head == null) {
			Node newNode = new Node(name, null);
			head = new Node(getFirstLetter(name), newNode);
			size += 2;
		}
		// case where there are more elements
		else {
			// moving the previousNode and currentNode to enclose where the new name and possible letter are going to be added
			Node currNode = head;
			Node prevNode = head;
			for(int x = 0; x < size; x++) {
				// comparing the first letters by alphabetical order
				if(currNode.data.length() == 1 && getFirstLetter(name).toLowerCase().compareTo(currNode.data.toLowerCase()) <= 0)
					break;
				
				if (currNode.next == null)
					break;
				else
					prevNode = currNode;
					currNode = currNode.next;
			}
			
			// case where we are adding node(s) in the middle of the linked list
			if (currNode.next != null) {
				// case where a letter needs to be added with the name
				if (find(getFirstLetter(name)) == -1) {
					
					// adding the letter and name to the head of the linked list
					if (currNode == head) {
						Node newNode1 = new Node(name, currNode);
						Node newNode2 = new Node(getFirstLetter(name).toUpperCase(), newNode1);
						head = newNode2;
					}
					else {
						// adding letter and name in middle of list
						Node newNode = new Node(name, currNode);
						prevNode.next = new Node(getFirstLetter(name).toUpperCase(), newNode);
					}
					size += 2;
				}
				else {
					// case where we are adding just the name in the middle of the list
					prevNode = currNode;
					currNode = currNode.next;
					// finding its alphabetical place
					while(currNode != null && currNode.data.toLowerCase().compareTo(name.toLowerCase()) <= 0) {
						prevNode = currNode;
						currNode = currNode.next;
					}
					// adding the node
					Node newNode = new Node(name, currNode);
					prevNode.next = newNode;
					size++;
				}
			}
			else {
				// case where we are adding to the end of the list
				Node newNode = new Node(name, null);
				currNode.next = new Node(getFirstLetter(name).toUpperCase(), newNode);
				size += 2;
			}
		}
	}
	
	// remove method removes a singular name from the list
	void remove(String name) {
		int index = find(getFirstLetter(name).toUpperCase()) + 1;
		Node prevNode = head;
		Node currNode = head;
		
		// finding the first letter the name belongs to
		for (int x = 0; x < index; x++) {
			prevNode = currNode;
			currNode = currNode.next;
		}
		
		// checking to see if name is the only one listed for the letter and calling removeLetter if so
		if(currNode.next == null || ((currNode.next).data.compareTo(getFirstLetter(name)) == 0 && getFirstLetter((currNode.next.next).data).compareTo(getFirstLetter(name)) != 0))
			removeLetter(getFirstLetter(name).toUpperCase());
		else {
			// else, removing specific name from list
			while(currNode != null && currNode.data.compareTo(name) != 0) {
				prevNode = currNode;
				currNode = currNode.next;
			}
			prevNode.next = currNode.next;
			size--;
		}
	}
	
	// removeLetter removes the entire letter and the name from the list
	void removeLetter(String letter) {
		int index = find(letter);
		
		// making sure the letter exists
		if(index == -1)
			return;
		
		Node prevNode = head;
		Node currNode = head;
		
		// finding the letter in the list and placing pointers around it
		for(int x = 0; x < index; x++) {
			prevNode = currNode;
			currNode = currNode.next;
		}
		
		// removing each node
		while(currNode != null && getFirstLetter(currNode.data).toLowerCase().compareTo(letter.toLowerCase()) == 0) {
			prevNode.next = currNode.next;
			currNode = currNode.next;
			size--;
		}
		
		// case where the first letter in the list is being removed and only the letter remains
		if (head.data.compareTo(letter) == 0)
			head = head.next;
	}
	
	// find searches the list for the characters index
	int find(String characters) {
		Node currNode = head;
		
		// linear search until found then returns index
		for(int x = 0; x < size; x++) {
			if (currNode.data.equals(characters))
				return x;
			currNode = currNode.next;
		}
		
		return -1;
	}
	
	// toString returns a stringbuilder with the entire list
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Node currNode = head;
		
		for(int x = 0; x < size; x++) {
			if (currNode.data.length() == 1)
				sb.append(currNode.data);
			else {
				sb.append("     " + currNode.data);
			}
			sb.append("\n");
			currNode = currNode.next;
		}
		
		return new String(sb);
	}
	
	// main method
	public static void main(String[] args) {
		LinkedList LL = new LinkedList();
		
		LL.add("Bob");
		LL.add("Dan");
		LL.add("Ben");
		LL.add("Abby");
		LL.add("abby");
		LL.add("Draven");
		LL.add("Candice");
		LL.add("zaydn");
		LL.add("Aaron");
		LL.add("Cathleen");
		
		System.out.println(LL);
		
		LL.removeLetter("C");
		
		System.out.println(LL);
		
		LL.remove("Aaron");
		
		System.out.println(LL);
		
		LL.remove("zaydn");
		
		System.out.println(LL);
	}
}

// Node class
class Node {
	// data fields
	String data;
	Node next;
	
	// overloaded constructor
	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}
}
