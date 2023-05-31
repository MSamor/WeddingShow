package vip.maosi.weddingServer.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "image")
public class Image implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(message = "不能为null")
    private Integer id;

    @TableField(value = "filename")
    @Size(max = 255,message = "最大长度要小于 255")
    @NotBlank(message = "不能为空")
    private String filename;

    @TableField(value = "file_type")
    @Size(max = 100,message = "最大长度要小于 100")
    @NotBlank(message = "不能为空")
    private String fileType;

    @TableField(value = "file_size")
    @NotNull(message = "不能为null")
    private Long fileSize;

    @TableField(value = "file_data")
    @NotNull(message = "不能为null")
    private byte[] fileData;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_FILENAME = "filename";

    public static final String COL_FILE_TYPE = "file_type";

    public static final String COL_FILE_SIZE = "file_size";

    public static final String COL_FILE_DATA = "file_data";
}