package com.abc.newsserversec.model.user;


/**
 * 用户信息数据类
 */
public class UserInfo {

    //序号
    private long id;
    //用户名
    private String username;
    //密码
    private String password;
    //openid
    private String openid;
    //昵称
    private String nickname;
    //手机号
    private String mobilephone;
    //真实姓名
    private String realname;
    //性别
    private String sex;
    //身份证号
    private String cardid;
    //创建日期
    private String createdate;
    //vip等级
    private int viplevel;
    //登录次数
    private long logincount;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getNickname() { return nickname; }

    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getMobilephone() { return mobilephone; }

    public void setMobilephone(String mobilephone) { this.mobilephone = mobilephone; }

    public String getRealname() { return realname; }

    public void setRealname(String realname) { this.realname = realname; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getCardid() { return cardid; }

    public void setCardid(String cardid) { this.cardid = cardid; }

    public String getCreatedate() { return createdate; }

    public void setCreatedate(String createdate) { this.createdate = createdate; }

    public int getViplevel() { return viplevel; }

    public void setViplevel(int viplevel) { this.viplevel = viplevel; }

    public long getLogincount() { return logincount; }

    public void setLogincount(long logincount) { this.logincount = logincount; }

}
