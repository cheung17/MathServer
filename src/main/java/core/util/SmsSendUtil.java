package core.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import core.constant.SMSConst;


/**
 * 短信发送工具类 
 *
 * @author chenweju
 *
 * @since Apr 25, 2015
 */
public class SmsSendUtil {
	 /**
	  * 发送短信
	  * @param mobile
	  * 			 手机号
	  * @param msg
	  * 			 短信内容
	  * @return
	  * @throws Exception
	  */
    public static ResultInfo sendSMS(String mobile, String msg) throws Exception {
    	ResultInfo resultInfo = new ResultInfo();
    	if (SMSConst.SMS_OPEN) {
    		resultInfo = validMobile(mobile);//对手机号进行校验
    		if (resultInfo.getCode() == -1) {
    			return resultInfo;
    		}
    		String smsurl = "";
    		try {
    			String message = java.net.URLEncoder.encode(msg, "UTF-8");
    			message = new String(message.getBytes(), "UTF-8");
    			 smsurl = SMSConst.SMS_URL + "?name="
    					+ SMSConst.SMS_USER + "&pwd="
    					+ SMSConst.SMS_PWD + "&dst="
    					+ mobile+ "&msg=" + message;
    			HttpURLConnection connect = (HttpURLConnection) ((new URL(
    					smsurl)).openConnection());
    			connect.setRequestMethod("POST");
    			connect.setDoOutput(true);
    			connect.connect();
    			if (connect.getResponseCode() == 200) {
    				resultInfo.setCode(1);
    				resultInfo.setMsg("短信发送成功");
    			} else {
    				resultInfo.setCode(-1);
    				resultInfo.setMsg("调用接口返回："+connect.getResponseCode());
    			}
    		} catch (Exception e) {
    			resultInfo.setCode(-1);
    			resultInfo.setMsg("执行异常："+e.getMessage());
    			LogUtil.error(SmsSendUtil.class, smsurl);
    			LogUtil.error(SmsSendUtil.class, e);
    		}
		}
        return resultInfo;
    }
   
   
    /**
     * 手机号校验
     * @param mobile
     * @return
     * @throws Exception
     */
    private static ResultInfo validMobile(String mobile) throws Exception {
    	  Pattern p = null;  
    	  Matcher m = null;  
    	  ResultInfo resultInfo = new ResultInfo();  
    	  if(null == mobile || mobile.isEmpty()){
    		  resultInfo.setCode(-1);
    		  resultInfo.setMsg("手机号为空");
    		  return resultInfo;  
    	  }
    	  p = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号  
    	  m = p.matcher(mobile.trim());  
    	  if( m.matches()){
    		  resultInfo.setCode(1);
    		  resultInfo.setMsg("手机号验证通过");
    	  }else{
    		  resultInfo.setCode(-1);
    		  resultInfo.setMsg("手机号["+mobile+"]验证未通过");
    	  }
    	  return resultInfo;  

    }

    public static void main(String[] args) {
    	
		try {
			ResultInfo resultInfo1;
			resultInfo1 = SmsSendUtil.validMobile("11110929271");
			 System.out.println(resultInfo1.getMsg());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
       
    	 ApplicationContext ctx = new FileSystemXmlApplicationContext("D:\\workspace\\platform_jar\\resources\\conf\\application-context.xml");
		 BeanFactory.init(ctx);
		 try {
			 PropUtil.setROOT_PATH( "D:\\workspace\\platform_web\\WebContent\\");
			 SMSConst.reloadConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
        	ResultInfo resultInfo = SmsSendUtil.sendSMS("11110929271","测试");
            System.out.println(resultInfo.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
