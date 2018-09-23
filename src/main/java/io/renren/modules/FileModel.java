package io.renren.modules;

/**
 * @Auther: jinweia.wu
 * @Date: 2018/9/23 12:33
 * @Description:
 */
public class FileModel {
    private Long createTime;

    private String filePath;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
