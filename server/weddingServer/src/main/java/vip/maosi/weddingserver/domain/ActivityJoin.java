package vip.maosi.weddingserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "activity_join")
public class ActivityJoin implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(message = "不能为null")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "`uid`")
    private Integer uid;

    /**
     * 活动id
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 加入时间
     */
    @TableField(value = "`date`")
    private Date date;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_ACTIVITY_ID = "activity_id";

    public static final String COL_DATE = "date";
}