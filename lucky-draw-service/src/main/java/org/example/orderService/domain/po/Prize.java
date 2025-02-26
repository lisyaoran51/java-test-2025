package org.example.orderService.domain.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("prize")
@Builder
public class Prize {
    Long id;
    Integer quantity;
    Double probability;
    Timestamp createTime;
    Timestamp updateTime;
}
