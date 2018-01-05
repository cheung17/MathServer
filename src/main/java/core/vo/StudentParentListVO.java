package core.vo;

/**
 * 学生名单vo
 * @author lb
 */
public class StudentParentListVO extends SuperVO {

	/**学生用户名*/
	private String stuUserName;
	
	/**学生姓名*/
	private String stuName;
	
	/**学生联系电话*/
	private String stuMobile;
	
	/**家长(主要指父亲)用户名*/
	private String parOneUserName;
	
	/**家长(主要指父亲)姓名*/
	private String parOneName;
	
	/**家长(主要指父亲)联系电话*/
	private String parOneMobile;
	
	/**家长(主要指母亲)用户名*/
	private String parTwoUserName;
	
	/**家长(主要指母亲)姓名*/
	private String parTwoName;
	
	/**家长(主要指母亲)联系电话*/
	private String parTwoMobile;
	
	/**学生vip卡号（主要指vip激活码）*/
	private String vipcode;
	
	

	/**
	 * 无参数的构造器
	 */
	public StudentParentListVO() {
	}

	public String getStuUserName() {
		return stuUserName;
	}

	public void setStuUserName(String stuUserName) {
		this.stuUserName = stuUserName;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuMobile() {
		return stuMobile;
	}

	public void setStuMobile(String stuMobile) {
		this.stuMobile = stuMobile;
	}

	public String getParOneUserName() {
		return parOneUserName;
	}

	public void setParOneUserName(String parOneUserName) {
		this.parOneUserName = parOneUserName;
	}

	public String getParOneName() {
		return parOneName;
	}

	public void setParOneName(String parOneName) {
		this.parOneName = parOneName;
	}

	public String getParOneMobile() {
		return parOneMobile;
	}

	public void setParOneMobile(String parOneMobile) {
		this.parOneMobile = parOneMobile;
	}

	public String getParTwoUserName() {
		return parTwoUserName;
	}

	public void setParTwoUserName(String parTwoUserName) {
		this.parTwoUserName = parTwoUserName;
	}

	public String getParTwoName() {
		return parTwoName;
	}

	public void setParTwoName(String parTwoName) {
		this.parTwoName = parTwoName;
	}

	public String getVipcode() {
		return vipcode;
	}

	public void setVipcode(String vipcode) {
		this.vipcode = vipcode;
	}

	public String getParTwoMobile() {
		return parTwoMobile;
	}

	public void setParTwoMobile(String parTwoMobile) {
		this.parTwoMobile = parTwoMobile;
	}

	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "id";
	}

	@Override
	public String getParentPKFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "hrm_student";
	}
	
	
}
