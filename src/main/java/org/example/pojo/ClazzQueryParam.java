package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;//班级名称
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate begin;//范围匹配的开始时间(结课时间)
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    private LocalDate end;//范围匹配的结束时间(结课时间)
}
