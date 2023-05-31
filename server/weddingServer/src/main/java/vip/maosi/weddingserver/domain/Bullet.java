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
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "bullet")
public class Bullet implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(message = "不能为null")
    private Integer id;

    /**
     * 时间
     */
    @TableField(value = "`date`")
    private Date date;

    /**
     * 内容
     */
    @TableField(value = "`text`")
    @Size(max = 255, message = "内容最大长度要小于 255")
    private String text;

    /**
     * 用户id
     */
    @TableField(value = "`uid`")
    private Integer uid;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_DATE = "date";

    public static final String COL_TEXT = "text";

    public static final String COL_UID = "uid";
}