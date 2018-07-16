package charpter;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class DBTest {

	public static void main(String[] args) {
		try {
			connection b =new connection();
			String sql = "insert into option_sheet(id,question,opta,optb,optc,optd,ans) values(12,'Tom','计算机','湖南','湖南','湖南','湖南')";
			
			b.execute(sql);
			ResultSet rs = b.executeQuery("select * from option_sheet");
			
			ArrayList<Question> list = new ArrayList();
			while(rs.next()){
				String tid = rs.getString(1);
				String question = rs.getString(2);
				String opta = rs.getString(3);
				String optb = rs.getString(4);
				String optc = rs.getString(5);
				String optd = rs.getString(6);
				String ans = rs.getString(7);
				Question t = new Question(tid,question,opta,optb,optc,optd,ans);
				list.add(t);
			}
			b.close();
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
					ques[j]=randnumber;
					j++;
					System.out.println(randnumber);
				}
			}
			
			for(int k=0;k<4;k++) {
				Question s = list.get(ques[k]);
				System.out.println(s.tid);
				System.out.println(s.question);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
