package core.vo;

import java.io.Serializable;

/**
 * 字段变化比较类
 *
 * @author chenweju
 *
 * @since Apr 24, 2015
 */
public class EntityDifferenceVO implements Serializable{
 
    private static final long serialVersionUID = -4338310134846628864L;
    /**
     * 字段描述
     */
    private String fieldDesc;
    /**
     * 新值
     */
    private String newVal;
    /**
     * 旧值
     */
    private String oldVal;
    /**
     * 描述（fieldDesc：由oldVal变为val）
     */
    private String desc;
    
    public String getFieldDesc() {
        return fieldDesc;
    }
    
    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }
    
    public String getNewVal() {
        return newVal;
    }

    public void setNewVal(String newVal) {
        this.newVal = newVal;
    }

    public String getOldVal() {
        return oldVal;
    }
    
    public void setOldVal(String oldVal) {
        this.oldVal = oldVal;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public void setDesc(String desc) {
        this.desc = desc;
    }
}
