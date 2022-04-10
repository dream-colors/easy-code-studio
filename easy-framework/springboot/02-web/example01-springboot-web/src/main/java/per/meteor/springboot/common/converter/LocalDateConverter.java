package per.meteor.springboot.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

/**
 * @author meteor
 * @date 2022-03-20 18:38
 */
public interface LocalDateConverter extends Converter<String, LocalDate> {
}
