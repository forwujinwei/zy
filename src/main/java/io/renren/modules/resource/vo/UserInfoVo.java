package io.renren.modules.resource.vo;

/**
 * @Auther: jinweia.wu
 * @Date: 2018/9/15 23:15
 * @Description:
 */
public class UserInfoVo {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
