package UI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import AI.SnakeAI;
import Mode.Node;
import Mode.Snake;

public class SnakePanel extends JPanel implements Runnable {

	Snake snake;
	SnakeAI ai;
	
	/**
	 * create the panel
	 */
	public SnakePanel() {
		// TODO Auto-generated constructor stub
		snake = new Snake();
		Node n = new Node(10,10);//蛇头的初始位置
		snake.getS().add(n);
		snake.setFirst(n);
		snake.setLast(n);
		snake.setTail(new Node(0,10));//last的前一个节点
		snake.setFood(new Node(80,80));//食物初始位置
		ai = new SnakeAI();
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.orange);
		g.drawRect(10, 10, Snake.map_size, Snake.map_size);//地图范围
		
		g.setColor(Color.white);
		paintSnake(g,snake);
		
		g.setColor(Color.WHITE);
		paintFood(g,snake.getFood());
		
		int dir = ai.palyBFS(snake, snake.getFood());//选择策略
		if (dir == -1) {
			System.out.println("gg");
		} else {
			snake.move(dir);
		}
	}
	
	/**
	 * @param g
	 * @param snake
	 * 画蛇
	 */
	private void paintSnake(Graphics g, Snake snake) {
		// TODO Auto-generated method stub
		for(Node n :snake.getS()){
			if (n.toString().equals(snake.getFirst().toString())) {
				g.setColor(Color.GREEN);//为了方便看蛇头为绿色
			}
			if (n.toString().equals(snake.getLast().toString()) && !snake.getFirst().toString().equals(snake.getLast().toString())) {
				g.setColor(Color.blue);//蛇尾蓝色
			}
			g.fillRect(n.getX(), n.getY(), Snake.size, Snake.size);
			g.setColor(Color.white);//蛇身为白色
		}
	}
	
	/**
	 * @param g
	 * @param food
	 * 画食物
	 */
	public void paintFood(Graphics g,Node food) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillOval(food.getX(), food.getY(), Snake.size, Snake.size);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(10);//延迟速度
				this.repaint();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
