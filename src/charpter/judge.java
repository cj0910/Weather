package charpter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class judge extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	} 

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		double s1=0,s2=0,s3=0,s4=0;
		String tid1=request.getParameter("tid1");
		String tid2=request.getParameter("tid2");
		String tid3=request.getParameter("tid3");
		String tid4=request.getParameter("tid4");
		String op1=request.getParameter("op1");
		String op2=request.getParameter("op2");
		String op3=request.getParameter("op3");
		String op4=request.getParameter("op4");
		
		JSONObject object;
		try {
			connection db = new connection();
			ResultSet rs1 = db.executeQuery("select answer from answers.option_sheet where tid='"+tid1+"'");
			ResultSet rs2 = db.executeQuery("select answer from answers.option_sheet where tid='"+tid2+"'");
			ResultSet rs3 = db.executeQuery("select answer from answers.option_sheet where tid='"+tid3+"'");
			ResultSet rs4 = db.executeQuery("select answer from answers.option_sheet where tid='"+tid4+"'");
			if(rs1.next()){
				if(rs1.getString("answer").equals(op1))
				{
					s1=25;
				}else{
					s1=0;
				}
			}
			if(rs2.next()){
				if(rs2.getString("answer").equals(op2))
				{
					s2=25;
				}else{
					s2=0;
				}
			}
			if(rs3.next()){
				if(rs3.getString("answer").equals(op3))
				{
					s3=25;
				}else{
					s3=0;
				}
			}
			if(rs4.next()){
				if(rs4.getString("answer").equals(op4))
				{
					s4=25;
				}else{
					s4=0;
				}
			}
			double s=s1+s2+s3+s4;
			String sql="insert into answers.score_sheet(id,userid,score,time) values('";
			sql=sql+1+"','";
			sql=sql+1+"',";
			sql=sql+s+",'";
			sql=sql+0+"')";
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			try {
				obj.put("my_answer", op1+op2+op3+op4);
			} catch (JSONException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			try {
				obj.put("score",s);
			} catch (JSONException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			//obj.put("user", username);
			
			System.out.println(obj.toString());
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	public static void main(String[] args) {

	}

}
