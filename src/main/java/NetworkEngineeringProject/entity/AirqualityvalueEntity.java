package NetworkEngineeringProject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zk
 * @since 2024-11-18 22:41:23
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("airqualityvalue")
public class AirqualityvalueEntity {

    @TableField("`time`")
    private String time;

    @TableField("`value`")
    private double value;
}
