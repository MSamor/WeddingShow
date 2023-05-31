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
@TableName(value = "activity")
public class Activity implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(message = "不能为null")
    private Integer id;

    /**
     * 活动名称
     */
    @TableField(value = "activity_name")
    @Size(max = 255, message = "活动名称最大长度要小于 255")
    private String activityName;

    /**
     * 活动结束时间,开奖时间
     */
    @TableField(value = "activity_end_date")
    private Date activityEndDate;

    /**
     * 中奖人数
     */
    @TableField(value = "win_num")
    private Integer winNum;

    /**
     * 活动描述
     */
    @TableField(value = "activity_desc")
    private Integer activityDesc;

    /**
     * Lucky draw，抽奖code
     */
    @TableField(value = "code")
    @Size(max = 255, message = "Lucky draw，抽奖code最大长度要小于 255")
    private String code;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ACTIVITY_NAME = "activity_name";

    public static final String COL_ACTIVITY_END_DATE = "activity_end_date";

    public static final String COL_WIN_NUM = "win_num";

    public static final String COL_PRIZE_ID = "prize_id";

    public static final String COL_CODE = "code";
}