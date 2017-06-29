package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class StartGame {
	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					StartGame window = new StartGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	public StartGame() {
		// TODO Auto-generated constructor stub
		initialize();
	}
	private void initialize() {
		// TODO Auto-generated method stub
		frame = new JFrame();
		frame.setResizable(false);//每次拖动都会重绘图所以固定
		SnakePanel panel = new SnakePanel();
		new Thread(panel).start();//启动线程
		panel.setBackground(Color.black);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setBounds(400,400,400,400);//外框大小
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	
}
