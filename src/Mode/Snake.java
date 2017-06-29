package Mode;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JSpinner.DateEditor;

public class Snake {

	/**
	 *  �����С
	 */
	public final static int size = 10;
	
	/**
	 *��ͼ��С 
	 */
	public final static int map_size=150;
	
	/**
	 * ��ͷ
	 */
	private Node first;
	
	/**
	 * �ߵ�β���ߵ���һ���ڵ�
	 */
	private Node tail;
	
	/**
	 * ��������β
	 */
	private Node last;
	
	/**
	 * �ߵ����ݽṹ
	 */
	private ArrayList<Node> s = new ArrayList<Node>();
	
	/**
	 *��ͼ�������ߵĽڵ㣬�ߵ�string�洢 
	 */
	private HashSet<String> map = new HashSet<String>();
	
	/**
	 * ����
	 */
	private int dir;// 8 6 2 4  �� �� �� ��

	/**
	 * ʳ��
	 */
	private Node food;
	
	public Snake() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Snake(Node first, Node tail, Node last, Node food) {
		super();
		this.first = first;
		this.tail = tail;
		this.last = last;
		this.food = food;
	}
	
	/**
	 * @param n
	 * ��n��ӵ�s��
	 */
	private void add_Node(Node n){
		s.add(0,n);
		
		first=s.get(0);
//		�����ӵĽڵ㲻��ʳ�ȥ��β��
		if (!n.toString().equals(food.toString())) {
			tail=last;//��¼β��
			s.remove(last);
			last=s.get(s.size()-1);
		}else {//����ǣ����ʳ��
			food = RandomFood();
		}
	}

	/**
	 * �ƶ�
	 */
	public void move(){
		if (dir == 8) {
			Node n = new Node(first.getX(),first.getY()-10);
			add_Node(n);
		}
		if (dir == 6) {
			Node n = new Node(first.getX()+10,first.getY());
			add_Node(n);
		}
		if (dir == 2) {
			Node n = new Node(first.getX(),first.getY()+10);
			add_Node(n);
		}
		if (dir == 4) {
			Node n = new Node(first.getX()-10,first.getY());
			add_Node(n);
		}
		updateMap(s);
	}
	
	
	/**
	 * @param dir
	 * �������÷����move
	 */
	public void move(int dir){
		this.dir=dir;
		move();
	}
	
	/**
	 * �жϷ����ܲ�����
	 * @param dir
	 * @return 
	 */
	public boolean canMove(int dir) {
		if (dir == 2) {
			int X = first.getX();
			int Y = first.getY()+10 ;
			if (Y>Snake.map_size || map.contains(X+"-"+Y)) {
				return false;
			}else return true;
		}
		if (dir == 6) {
			int X = first.getX()+10;
			int Y = first.getY();
			if (X >Snake.map_size || map.contains(X+"-"+Y)) {
				return false;
			}else return true;
		}
		if (dir == 8) {
			int X = first.getX();
			int Y = first.getY()-10 ;
			if (Y<10  || map.contains(X+"-"+Y)) {
				return false;
			}else return true;
		}
		if (dir == 4) {
			int X = first.getX()-10;
			int Y = first.getY();
			if (X<10 || map.contains(X+"-"+Y)) {
				return false;
			}else return true;
		}
		return false;
	}
	
	/**
	 * @param s
	 * ���µ�ͼ�Ϸ��ʹ���λ��
	 */
	private void updateMap(ArrayList<Node> s) {
		map.clear();//���Ƴ��ɵ�վλ��
		for (Node n : s) {
			map.add(n.toString());
		}
	}

	/**
	 * @return n
	 * ���ʳ��
	 */
	private Node RandomFood() {
		while (true) {
			int x = 0,y;
			x = Snake.size*(int) (Math.random() * Snake.map_size/Snake.size)+10;
			y = Snake.size*(int) (Math.random() * Snake.map_size/Snake.size)+10;
			Node n = new Node(x,y);
//			ʳ�ﲻ�ܳ�����������
			if (!s.contains(n)) {
				return n;
			}
		}
	}
	
	/**
	 * stringתnode
	 * @param string
	 * @return
	 */
	public Node StringToNode(String string) {
		String []str = string.split("-");
		int x = Integer.parseInt(str[0]);
		int y = Integer.parseInt(str[1]);
		return new Node(x,y);
	}

	/**
	 * @return s.size
	 * �߳�
	 */
	public int getLen(){
		return s.size();
	}
	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	/**
	 * @return
	 * β��last��ǰһ���ڵ�
	 */
	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public Node getLast() {
		return last;
	}

	public void setLast(Node last) {
		this.last = last;
	}

	public ArrayList<Node> getS() {
		return s;
	}

	public void setS(ArrayList<Node> s) {
		this.s = s;
	}

	public HashSet<String> getMap() {
		return map;
	}

	public void setMap(HashSet<String> map) {
		this.map = map;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public Node getFood() {
		return food;
	}

	public void setFood(Node food) {
		this.food = food;
	}

	public static int getSize() {
		return size;
	}

	public static int getMapSize() {
		return map_size;
	}
	
	
	
	
}

