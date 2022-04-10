package per.meteor.springboot.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

/**
 * @author meteor
 * @date 2022-03-20 18:34
 */
public interface LocalDateTimeConverter extends Converter<String, LocalDateTime> {
}
