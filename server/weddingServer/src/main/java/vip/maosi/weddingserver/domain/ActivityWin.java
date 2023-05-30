package vip.maosi.weddingserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "activity_win")
public class ActivityWin implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(message = "不能为null")
    private Integer id;

    /**
     * 中奖人id
     */
    @TableField(value = "`uid`")
    private Integer uid;

    /**
     * 活动id
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 奖品id
     */
    @TableField(value = "activity_prize_id")
    private Integer activityPrizeId;

    /**
     * 中奖时间
     */
    @TableField(value = "`date`")
    private Date date;

    /**
     * 额外说明
     */
    @TableField(value = "memo")
    @Size(max = 255,message = "额外说明最大长度要小于 255")
    private String memo;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_ACTIVITY_ID = "activity_id";

    public static final String COL_ACTIVITY_PRIZE_ID = "activity_prize_id";

    public static final String COL_DATE = "date";

    public static final String COL_MEMO = "memo";
}