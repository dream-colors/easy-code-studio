package per.meteor.springboot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author meteor
 * @date 2022-03-18 19:03
 */
@Data
@Accessors(chain = true)
public class Post {

    private Integer id;
    private Integer userId;
    private String postName;
    private Double salary;
}
