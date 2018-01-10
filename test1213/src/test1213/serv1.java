package test1213;
import java.io.IOException;  
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



  
  
  
  
public class serv1 extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
  
  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse res)  
        throws ServletException, IOException {  
// TODO Auto-generated method stub
        
        res.setContentType("text/html;charset=utf-8");          
        /* ������Ӧͷ����ajax������� */  
        res.setHeader("Access-Control-Allow-Origin", "*");  
        /* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */  
        res.setHeader("Access-Control-Allow-Methods", "GET,POST");  
        
        //��ȡ΢��С����get�Ĳ���ֵ����ӡ
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("username="+username+" ,password="+password);
        
        
        String sql="SELECT * from userinfo";
        DBHelper dbhelper=new DBHelper();
        try {
			ResultSet ret=dbhelper.execute(sql);
			while(ret.next()){
				String dbuserid=ret.getString(1);
				String dbusername=ret.getString(2);
				System.out.println(dbuserid+"\t"+dbusername);
			}
			dbhelper.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        
        
        //json��ʽ����
        //����һ��resdata-msgdata��json���ݴ�
        String resdata="{\"msgdata\":[";  
        for(int i=0;i<4;i++){
        	JSONObject json=new JSONObject();
            json.put("title", i);
            json.put("whosend", i+""+i);
            json.put("time", "2017-12-17");
            json.put("context", "��������"+i);
            json.put("whoread", new String[]{"����","����","����"});
            System.out.println(json.toString());
            if(i!=3)resdata=resdata+json.toString()+",";
            else resdata+=json.toString();
        }
        resdata+="]}";
        System.out.println(resdata);
        
        //����ֵ��΢��С����
        Writer out = res.getWriter();
        //out.write("�����̨��");
        //String s = "{\"employees\": [{ \"firstName\":\"John\" , \"lastName\":\"Doe\" },{ \"firstName\":\"Anna\" , \"lastName\":\"Smith\" },{ \"firstName\":\"Peter\" , \"lastName\":\"Jones\" }]}";
        
        out.write(resdata);
        out.flush();   
    }  
  
  
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)  
        throws ServletException, IOException {  
        PrintWriter out = resp.getWriter();  
        String str = "<html><title></title><body>���Hello World sb!!!</body></html>";  
        out.println(str);  
        out.flush();  
        out.close();  
        System.out.println("dopost...");  
    }  
}  