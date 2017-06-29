package AI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import Mode.Node;
import Mode.Snake;

public class SnakeAI {

	public int  palyBFS(Snake s,Node f) {
		Queue<Node> queue = new LinkedList<Node>();
		Set<String> vis = new HashSet<String>();//记录访问过的节点
		Map<String , String > path = new HashMap<String,String >();//记录访问的路径
		Stack<String > stack = new Stack<String>();//蛇去吃的路径
		
		queue.add(s.getFirst());
		while(!queue.isEmpty()){
			Node n = queue.remove();
			if (n.getX() == f.getX() && n.getY() == f.getY()) {
//				如果搜索到了食物，开始解析路径。因为侍从后添加， 所以用栈倒过来找
				String state = f.toString();
				while(state != null && !state.equals(s.getFirst().toString())){
					stack.push(state);
					state = path.get(state);
				}
				
				String []str;
//				有时候食物和头挨着就会导致栈为空
				if (stack.isEmpty()) {
					str = state.split("-");
				}else str = stack.peek().split("-");
				
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				if (x > s.getFirst().getX() && y == s.getFirst().getY()) {
					return 6;
				}
				if (x < s.getFirst().getX() && y == s.getFirst().getY()) {
					return 4;
				}
				if (x == s.getFirst().getX() && y > s.getFirst().getY()) {
					return 2;
				}
				if (x == s.getFirst().getX() && y < s.getFirst().getY()) {
					return 8;
				}
			}
			
			Node up = new Node(n.getX(), n.getY() + Snake.size);
			Node right = new Node(n.getX() + Snake.size, n.getY());
			Node down = new Node(n.getX(), n.getY() - Snake.size);
			Node left = new Node(n.getX() - Snake.size, n.getY());
			if (!s.getMap().contains(up.toString()) && !vis.contains(up.toString()) 
					&& up.getX() <= Snake.map_size&& up.getX() >= 10 
					&& up.getY() <= Snake.map_size && up.getY() >= 10) {
				queue.add(up);
				vis.add(up.toString());
				path.put(up.toString(),n.toString());
			}
			if (!s.getMap().contains(right.toString()) && !vis.contains(right.toString())
					&& right.getX() <= Snake.map_size&& right.getX() >= 10 
					&& right.getY() <= Snake.map_size && right.getY() >= 10) {
				queue.add(right);
				vis.add(right.toString());
				path.put(right.toString(),n.toString());
			}
			if (!s.getMap().contains(down.toString()) && !vis.contains(down.toString()) 
					&& down.getX() <= Snake.map_size&& down.getX() >= 10 
					&& down.getY() <= Snake.map_size && down.getY() >= 10) {
				queue.add(down);
				vis.add(down.toString());
				path.put(down.toString(),n.toString());
			}
			if (!s.getMap().contains(left.toString()) && !vis.contains(left.toString()) 
					&& left.getX() <= Snake.map_size&& left.getX() >= 10 
					&& left.getY() <= Snake.map_size && left.getY() >= 10) {
				queue.add(left);
				vis.add(left.toString());
				path.put(left.toString(),n.toString());
			}
		}
		return -1;
	}	
}
