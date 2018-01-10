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



public class getwhoread extends HttpServlet{
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
	        String msgid = req.getParameter("msgid");
	        System.out.println("msgid="+msgid);
	        
	        List<String> jsonlist = new ArrayList<String>();
	        //��ѯmysql���ݿ�
	        String sql="select msgreadinfo.WhoRead,msgreadinfo.ReadTime from msgreadinfo where MsgID="+msgid;
	        DBHelper dbhelper=new DBHelper();
	        try {
				ResultSet ret=dbhelper.execute(sql);
				while(ret.next()){
					String whoread=ret.getString(1);
					String readtime=ret.getString(2);
					JSONObject json=new JSONObject();
					json.put("whoread", whoread);
		            json.put("readtime", readtime);
		            jsonlist.add(json.toString());
				}				
				dbhelper.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        
	        String resdata=JSONHelper.createJsonData("whoreaddata", jsonlist);
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