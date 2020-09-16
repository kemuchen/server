package cn.framework.common.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

import lombok.Data;

/**
 * @ClassName BaseEntity
 * @Desc
 * @Author 柯雷
 * @Date 2020/1/15 9:40
 * @Version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1504020557820226618L;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Date create_time;

    /**
     * 创建时间STA
     */
    @ApiModelProperty(hidden = true)
    private Date create_time_sta;

    /**
     * 创建时间 END
     */
    @ApiModelProperty(hidden = true)
    private Date create_time_end;

    /**
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private Date modify_time;

    /**
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private Date modify_time_sta;

    /**
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private Date modify_time_end;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Integer create_user;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private Integer modify_user;

    /**
     * 是否有效
     */
    @ApiModelProperty(hidden = true)
    private String valid;

    /**
     * 备注
     */
    @ApiModelProperty(hidden = true)
    private String memo = "";

    /**
     * 当前页
     */
    @ApiModelProperty(hidden = true)
    private Integer current;

    /**
     * 页面大小
     */
    @ApiModelProperty(hidden = true)
    private Integer pageSize;

    /**
     * 起始行
     */
    @ApiModelProperty(hidden = true)
    private Integer startRow;

    /**
     * @Description: 获取指定field的值
     * @Params: [field_name]
     * @return: java.lang.Object
     * @Author: 柯雷
     * @Date: 2020/3/8 13:17
     */
    public Object getField(String field_name) {
        String methodName = "get" + field_name.substring(0, 1).toUpperCase() + field_name.substring(1);
        try {
            Class<?> clazz = this.getClass();
            Method method = clazz.getMethod(methodName);
            return method.invoke(this, new Object[]{});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
