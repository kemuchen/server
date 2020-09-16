package cn.framework.system.code;

/**
 * @ClassName MessageTypeEnum
 * @Desc 系统消息类型
 * @Author 柯雷
 * @Date 2020-05-09 10:30
 * @Version 1.0
 */
public enum MessageTypeEnum {

    NOTICE("1", "通知"),
    MESSAGE("2", "消息"),
    EVENT("3", "待办");

    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息类型描述
     */
    private String desc;

    /**
     * 构造函数
     */
    MessageTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
