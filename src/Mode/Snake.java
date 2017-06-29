package Mode;

import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JSpinner.DateEditor;

public class Snake {

	/**
	 *  蛇身大小
	 */
	public final static int size = 10;
	
	/**
	 *地图大小 
	 */
	public final static int map_size=150;
	
	/**
	 * 蛇头
	 */
	private Node first;
	
	/**
	 * 蛇的尾巴走的上一个节点
	 */
	private Node tail;
	
	/**
	 * 真正的蛇尾
	 */
	private Node last;
	
	/**
	 * 蛇的数据结构
	 */
	private ArrayList<Node> s = new ArrayList<Node>();
	
	/**
	 *地图上已有蛇的节点，蛇的string存储 
	 */
	private HashSet<String> map = new HashSet<String>();
	
	/**
	 * 方向
	 */
	private int dir;// 8 6 2 4  上 右 下 左

	/**
	 * 食物
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
	 * 把n添加到s中
	 */
	private void add_Node(Node n){
		s.add(0,n);
		
		first=s.get(0);
//		如果添加的节点不是食物，去掉尾巴
		if (!n.toString().equals(food.toString())) {
			tail=last;//记录尾巴
			s.remove(last);
			last=s.get(s.size()-1);
		}else {//如果是，随机食物
			food = RandomFood();
		}
	}

	/**
	 * 移动
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
	 * 可以设置方向的move
	 */
	public void move(int dir){
		this.dir=dir;
		move();
	}
	
	/**
	 * 判断方向能不能走
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
	 * 更新地图上访问过的位置
	 */
	private void updateMap(ArrayList<Node> s) {
		map.clear();//先移除旧的站位点
		for (Node n : s) {
			map.add(n.toString());
		}
	}

	/**
	 * @return n
	 * 随机食物
	 */
	private Node RandomFood() {
		while (true) {
			int x = 0,y;
			x = Snake.size*(int) (Math.random() * Snake.map_size/Snake.size)+10;
			y = Snake.size*(int) (Math.random() * Snake.map_size/Snake.size)+10;
			Node n = new Node(x,y);
//			食物不能出现在蛇身体
			if (!s.contains(n)) {
				return n;
			}
		}
	}
	
	/**
	 * string转node
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
	 * 蛇长
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
	 * 尾巴last的前一个节点
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

