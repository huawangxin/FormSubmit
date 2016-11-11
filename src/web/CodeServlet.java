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
		//1.创建一张空白的图片
		BufferedImage image = new 
			BufferedImage(100,30,
			BufferedImage.TYPE_INT_RGB);
		//2.获取该图片的画笔
		Graphics g = image.getGraphics();
		//3.绘制背景
		Random r = new Random();
		//设置画笔的颜色
		g.setColor(
			new Color(r.nextInt(255),
					  r.nextInt(255),
					  r.nextInt(255)));
		//绘制一个实心的矩形区域
		g.fillRect(0, 0, 100, 30);
		//4.绘制内容
		g.setColor(new Color(0,0,0));
		//new(字体，字体常量（粗体、斜体。。），字体大小)
		g.setFont(new Font(null,Font.BOLD,25));
		String num=getNumber(5);
		//将验证码的内容保存到session中
		HttpSession session = 
			request.getSession();
		session.setAttribute("vcode", num);
		g.drawString(num, 5,25);
		//5.将内存中的图片发回到客户端
		response.setContentType("image/jpeg");
		OutputStream ops = 
			  response.getOutputStream();
		ImageIO.write(image, "jpeg", ops);
	}
	/**随机生成指定长度的字符串*/
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
