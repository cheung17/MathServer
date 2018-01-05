/**
 * 
 */
package core.user.util;

import java.util.Map;

/**
 * @author libing
 * @descripton 初始化存入Redis中的userBean对象
 * @date 2015年9月12日 下午6:13:19
 */
public class RedisUserBeanUtil {
	/**
	 * 
	 * @functionName:initUserLoginBean
	 * @description:初始化存入Redis中的userBean对象
	 * @param:
	 * @return:
	 * @date:2015年9月12日 下午6:20:59
	 */
	public static UserLoginBean initUserLoginBean(RequestLogin requestLoginparam,Map<String, Object> resultMap){
		UserLoginBean userBean = new UserLoginBean();
		//从登录请求参数requestLoginparam中取出数据存放userBean
		userBean.setAccount(requestLoginparam.getAccount());
		userBean.setBrand(requestLoginparam.getBrand());
		userBean.setCheckcode(requestLoginparam.getCheckcode());
		userBean.setLat(requestLoginparam.getLat());
		userBean.setLnd(requestLoginparam.getLnd());
		userBean.setLoginpassword(requestLoginparam.getLoginpassword());
		userBean.setMachineid(requestLoginparam.getMachineid());
		userBean.setModel(requestLoginparam.getModel());
		userBean.setStationid(requestLoginparam.getStationid());
		userBean.setUserstate(requestLoginparam.getUserstate());
		userBean.setUsertype(requestLoginparam.getUsertype());
		
		//从登录成功后的结果集中resultMap中取出数据存放userBean
		userBean.setId((String)resultMap.get("id"));
		
		
		return userBean;
	}
	
	
	public static UserLoginBean initUserLoginBean(LoginBean Login,LoginResultBean result){
		UserLoginBean userBean = new UserLoginBean();
		//从登录请求参数requestLoginparam中取出数据存放userBean
		userBean.setAccount(Login.getAccount());
		userBean.setId(result.getUserId());
		//从登录成功后的结果集中resultMap中取出数据存放userBean
		return userBean;
	}
}
