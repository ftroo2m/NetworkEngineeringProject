package NetworkEngineeringProject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zk
 * @since 2024-12-20 16:29:41
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user")
public class UserEntity {

    @TableField("username")
    private String username;

    @TableField("`password`")
    private String password;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
}
