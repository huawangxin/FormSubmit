package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CodeServlet extends HttpServlet {
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.����һ�ſհ׵�ͼƬ
		BufferedImage image = new 
			BufferedImage(100,30,
			BufferedImage.TYPE_INT_RGB);
		//2.��ȡ��ͼƬ�Ļ���
		Graphics g = image.getGraphics();
		//3.���Ʊ���
		Random r = new Random();
		//���û��ʵ���ɫ
		g.setColor(
			new Color(r.nextInt(255),
					  r.nextInt(255),
					  r.nextInt(255)));
		//����һ��ʵ�ĵľ�������
		g.fillRect(0, 0, 100, 30);
		//4.��������
		g.setColor(new Color(0,0,0));
		//new(���壬���峣�������塢б�塣�����������С)
		g.setFont(new Font(null,Font.BOLD,25));
		String num=getNumber(5);
		//����֤������ݱ��浽session��
		HttpSession session = 
			request.getSession();
		session.setAttribute("vcode", num);
		g.drawString(num, 5,25);
		//5.���ڴ��е�ͼƬ���ص��ͻ���
		response.setContentType("image/jpeg");
		OutputStream ops = 
			  response.getOutputStream();
		ImageIO.write(image, "jpeg", ops);
	}
	/**�������ָ�����ȵ��ַ���*/
	public String getNumber(int size){
		String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		String number = "";
		Random r = new Random();
		for(int i=0;i<size;i++){
			number+=str.charAt(
			 r.nextInt(str.length()));
		}
		return number;
	}

}
