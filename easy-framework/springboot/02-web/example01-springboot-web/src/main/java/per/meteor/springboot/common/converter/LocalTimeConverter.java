package per.meteor.springboot.common.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

/**
 * @author meteor
 * @date 2022-03-20 18:38
 */
public interface LocalTimeConverter extends Converter<String, LocalTime> {
}
