package charpter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import charpter.DBConnection;

public class getques extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/plain;charset=utf-8");
		try {
			////////////
			// do something
			////////////
			connection db = new connection();
			ResultSet rs = db.executeQuery("select * from answers.option_sheet");
			
			String tid = "";
			String question = "";
			String opta = "";
			String optb = "";
			String optc = "";
			String optd = "";
			String ans = "";
			
			ArrayList<Question> list = new ArrayList();
			while(rs.next()){
				tid = rs.getString(1);
				question = rs.getString(2);
				opta = rs.getString(3);
				optb = rs.getString(4);
				optc = rs.getString(5);
				optd = rs.getString(6);
				ans = rs.getString(7);
				Question q=new Question(tid,question,opta,optb,optc,optd,ans);
				list.add(q);
			}
			db.close();
			
			Random r = new Random();
			int j=0;
			int[] ques= {-1,-1,-1,-1};
			while(j<4) {
				int randnumber = r.nextInt(list.size());
				int k=0;
				while(k<4)
				{
					if(ques[k]==randnumber)
						break;
					k++;
				}
				if(k==4)
				{
					ques[j]=randnumber+1;
					j++;
					System.out.print(randnumber);
				}
			}
			
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			JSONObject obj = new JSONObject();
			
				JSONObject obj1 = new JSONObject();
				Question q1=list.get(ques[0]);
				obj1.put("result", "ok");
				obj1.put("tid", q1.tid);
				obj1.put("question", q1.question);
				obj1.put("opta", q1.opta);
				obj1.put("optb", q1.optb);
				obj1.put("optc", q1.optc);
				obj1.put("optd", q1.optd);
				obj1.put("ans", q1.ans);
				obj.put("question1",obj1);
				
				JSONObject obj2 = new JSONObject();
				Question q2=list.get(ques[1]);
				obj2.put("result", "ok");
				obj2.put("tid", q2.tid);
				obj2.put("question", q2.question);
				obj2.put("opta", q2.opta);
				obj2.put("optb", q2.optb);
				obj2.put("optc", q2.optc);
				obj2.put("optd", q2.optd);
				obj2.put("ans",q2.ans);
				obj.put("question2",obj2);
				
				JSONObject obj3 = new JSONObject();
				Question q3=list.get(ques[2]);
				obj3.put("result", "ok");
				obj3.put("tid", q3.tid);
				obj3.put("question", q3.question);
				obj3.put("opta", q3.opta);
				obj3.put("optb", q3.optb);
				obj3.put("optc", q3.optc);
				obj3.put("optd", q3.optd);
				obj3.put("ans", q3.ans);
				obj.put("question3",obj3);
				
				
				JSONObject obj4 = new JSONObject();
				Question q4=list.get(ques[3]);
				obj4.put("result", "ok");
				obj4.put("tid", q4.tid);
				obj4.put("question",q4.question);
				obj4.put("opta", q4.opta);
				obj4.put("optb", q4.optb);
				obj4.put("optc", q4.optc);
				obj4.put("optd", q4.optd);
				obj4.put("ans", q4.ans);
				obj.put("question4",obj4);
			
			
			System.out.println(obj.toString());
			out.print(obj.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
	}

}
