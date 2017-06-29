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
		frame.setResizable(false);//ÿ���϶������ػ�ͼ���Թ̶�
		SnakePanel panel = new SnakePanel();
		new Thread(panel).start();//�����߳�
		panel.setBackground(Color.black);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setBounds(400,400,400,400);//����С
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	
}
