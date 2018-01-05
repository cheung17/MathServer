package core.vo;

import java.io.Serializable;

/**
 * 邮件实体类
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class EMailVO implements Serializable{
    private static final long serialVersionUID = 8043808300855236755L;
    /**
     * 发送主机
     */
    private String host;
    /**
     * 发送用户名
     */
    private String userName;
    /**
     * 发件人昵称
     */
    private String nickName;
    /**
     * 发件人
     */
    private String from;
    /**
     * 发送密码
     */
    private String pwd;
    /**
     * 接收人
     */
    private String recivers;
    /**
     * 抄送人
     */
    private String cc;
    /**
     * 密送人
     */
    private String bcc;
    /**
     * 回复邮箱
     */
    private String replyTo;
    /**
     * 邮件主题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 附件在路径+名称，多个附件用分号(;)分割
     */
    private String file;
    /**
     * 错误信息
     */
    private String errorMsg;
    public EMailVO() {
        super();
    }
    /**
     * 
     * @param host 主机
     * @param userName 用户名
     * @param nickName 发件人昵称
     * @param from 发件人
     * @param pwd 密码
     * @param recivers 收件人
     * @param cc 抄送人
     * @param bcc 密送人
     * @param replyTo 回复邮箱
     * @param title 主题
     * @param content 内容
     * @param file 附件
     */
    public EMailVO(String host, String userName, String nickName, String from, String pwd, String recivers,
            String cc, String bcc, String replyTo, String title, String content,String file) {
        super();
        this.host = host;
        this.userName = userName;
        this.nickName = nickName;
        this.from = from;
        this.pwd = pwd;
        this.recivers = recivers;
        this.cc = cc;
        this.bcc = bcc;
        this.replyTo = replyTo;
        this.title = title;
        this.content = content;
        this.file = file;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getRecivers() {
        return recivers;
    }
    public void setRecivers(String recivers) {
        this.recivers = recivers;
    }
    public String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }
    public String getBcc() {
        return bcc;
    }
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }
    public String getReplyTo() {
        return replyTo;
    }
    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
