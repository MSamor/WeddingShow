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
@TableName(value = "`user`")
public class User implements Serializable {
    /**
     * 用户uid
     */
    @TableId(value = "id", type = IdType.INPUT)
    @NotNull(message = "用户uid不能为null")
    private Integer id;

    /**
     * openid
     */
    @TableField(value = "openid")
    @Size(max = 100,message = "openid最大长度要小于 100")
    private String openid;

    /**
     * 注册时间
     */
    @TableField(value = "`date`")
    private Date date;

    /**
     * 头像
     */
    @TableField(value = "avatar_url")
    @Size(max = 5000,message = "头像最大长度要小于 5000")
    private String avatarUrl;

    /**
     * 昵称
     */
    @TableField(value = "nick_name")
    @Size(max = 255,message = "昵称最大长度要小于 255")
    private String nickName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_OPENID = "openid";

    public static final String COL_DATE = "date";

    public static final String COL_AVATAR_URL = "avatar_url";

    public static final String COL_NICK_NAME = "nick_name";
}