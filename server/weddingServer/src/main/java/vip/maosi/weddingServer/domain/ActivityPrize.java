package vip.maosi.weddingServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "activity_prize")
public class ActivityPrize implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "id不能为null")
    private Integer id;

    /**
     * 奖品名称
     */
    @TableField(value = "prize_name")
    @Size(max = 255, message = "奖品名称最大长度要小于 255")
    private String prizeName;

    /**
     * 数量
     */
    @TableField(value = "`count`")
    private Integer count;

    /**
     * 活动id
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 排序码
     */
    @TableField(value = "`order`")
    @Size(max = 255, message = "排序码最大长度要小于 255")
    private String order;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PRIZE_NAME = "prize_name";

    public static final String COL_COUNT = "count";

    public static final String COL_ACTIVITY_ID = "activity_id";

    public static final String COL_ORDER = "order";
}