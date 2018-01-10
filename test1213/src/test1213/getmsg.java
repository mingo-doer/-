package test1213;
import java.io.IOException;  
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



public class getmsg extends HttpServlet{
	 	private static final long serialVersionUID = 1L;  

	    @Override  
	    protected void doGet(HttpServletRequest req, HttpServletResponse res)  
	        throws ServletException, IOException {  
	        
	        res.setContentType("text/html;charset=utf-8");          
	        /* ������Ӧͷ����ajax������� */  
	        res.setHeader("Access-Control-Allow-Origin", "*");  
	        /* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */  
	        res.setHeader("Access-Control-Allow-Methods", "GET,POST");  
	        
	        //��ȡ΢��С����get�Ĳ���ֵ����ӡ
	        String username = req.getParameter("username");
	        System.out.println("username="+username);
	        
	        List<String> jsonlist = new ArrayList<String>();
	        //��ѯmysql���ݿ�
	        String sql="select messageinfo.Title,messageinfo.WhoSend,messageinfo.Time,messageinfo.Context FROM messageinfo";
	        DBHelper dbhelper=new DBHelper();
	        try {
				ResultSet ret=dbhelper.execute(sql);
				while(ret.next()){
					String title=ret.getString(1);
					String whosend=ret.getString(2);
					String time=ret.getString(3);
					String context=ret.getString(4);
					JSONObject json=new JSONObject();
					json.put("title", title);
		            json.put("whosend", whosend);
		            json.put("time", time);
		            json.put("context", context);
		            jsonlist.add(json.toString());
				}				
				dbhelper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
	        //�����msgdata �Ƿ���С������ setdata ��res.data.mesdata
	        String resdata=JSONHelper.createJsonData("msgdata", jsonlist);
	        System.out.println(resdata);
	        
	        //����ֵ��΢��С����
	        Writer out = res.getWriter();
	        out.write(resdata);
	        out.flush();
	        out.close();
	    }  
	  
	  
	    @Override  
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
	        throws ServletException, IOException {  

	    }  
}
