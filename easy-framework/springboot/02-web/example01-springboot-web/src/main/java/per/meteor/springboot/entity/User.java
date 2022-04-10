package per.meteor.springboot.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author meteor
 * @date 2022-03-19 16:40
 */
@Data
@Accessors(chain = true)
public class User implements Serializable {
    private static final long serialVersionUID = -4294461808433313537L;

    private Integer id;

    private String name;

    private String password;

    private boolean married;

//    @JsonDeserialize(using = CustomizedLocalDateTimeSerializer.LocalDateTimeDeserializer.class)
    private LocalDate birthday;

}
