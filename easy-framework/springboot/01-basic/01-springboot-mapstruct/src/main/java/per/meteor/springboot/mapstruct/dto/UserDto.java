package per.meteor.springboot.mapstruct.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author meteor
 * @date 2022-03-18 16:50
 */
@Data
@Accessors(chain = true)
public class UserDto {

    private Integer id;

    private String name;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime birthday;

    private String post;

    private Double salary;

}
