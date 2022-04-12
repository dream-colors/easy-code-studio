package per.meteor.springboot.common.converter;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import per.meteor.springboot.common.constanst.DatePattern;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * <p>全局处理日期转换问题</p>
 * <p>解决Get及Post表单形式日期转换问题</p>
 * <p>1、自定义参数转换器，Converter</p>
 * <p>2、@DateTimeFormat参数注解，注意，如果使用了自定义参数转化器（Converter），Spring会优先使用该方式进行处理，即@DateTimeFormat注解不生效，两种方式是不兼容的。</p>
 * <p>3、使用@ControllerAdvice配合@initBinder</p>
 * <p></p>
 * <p>处理请求类型为POST、Content-type是application/json的请求，即请求参数标记了{@link org.springframework.web.bind.annotation.RequestBody}注解的参数</p>
 * <p>1、方式一：配置文件配置spring.jackson.*，缺点是不支持Java8日期API，请求参数格式必须是yyyy-MM-dd HH:mm:ss，其他格式会报400错误</p>
 * <p>2、方式二：自定义Jackson2ObjectMapperBuilderCustomizer，实现serializerByType和deserializerByType,可支持Java8日期Api，缺点是请求参数格式必须是yyyy-MM-dd HH:mm:ss，其他格式会报400错误</p>
 * <p>3、方式三：自定义MappingJackson2HttpMessageConverter日期转换器，并自定义ObjectMapper，特点和方式二一样。</p>
 * <p>
 * <p>局部日期处理</p>
 * <p>1、@JsonFormat指定接受格式，@DateFormat指定返回格式</p>
 * <p>2、自定义序列化器和反序列化器，使用@JsonSerialize，并提供{@link com.fasterxml.jackson.databind.JsonSerializer}序列化器和{@link com.fasterxml.jackson.databind.JsonDeserializer}反序列化实现</p>
 * @author meteor
 * @date 2022-03-20 17:27
 */
@Configuration
public class DateParamConverterConfiguration {

    /**
     * LocalDate转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public LocalDateConverter localDateConverter() {
        return source -> {
            if (StringUtils.hasText(source)) {
                return LocalDate.parse(source, DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN));
            }
            return null;
        };
    }

    /**
     * LocalDateTime转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public LocalDateTimeConverter localDateTimeConverter() {
        return source -> {
            if (StringUtils.hasText(source)) {
                return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN));
            }
            return null;
        };
    }

    /**
     * LocalDate转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public LocalTimeConverter localTimeConverter() {
        return source -> {
            if (StringUtils.hasText(source)) {
                return LocalTime.parse(source, DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN));
            }
            return null;
        };
    }

    /**
     * Date转换器，用于转换RequestParam和PathVariable参数
     */
    @Bean
    public DateConverter dateConverter() {
        return source -> {
            if (!StringUtils.hasText(source)) {
                return null;
            }
            if (source.matches(DatePattern.REGEX_TIME_STAMP)) {
                return new Date(Long.parseLong(source));
            }
            DateFormat format;
            if (source.matches(DatePattern.REGEX_NORM_DATE_TIME)) {
                format = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            } else if (source.matches(DatePattern.REGEX_NORM_DATE)) {
                format = new SimpleDateFormat(DatePattern.NORM_DATE_PATTERN);
            } else if (source.matches(DatePattern.REGEX_NORM_YEAR_MONTH)) {
                format = new SimpleDateFormat(DatePattern.NORM_MONTH_PATTERN);
            } else {
                throw new IllegalArgumentException();
            }
            try {
                return format.parse(source);
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        };
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .timeZone(TimeZone.getTimeZone("GMT+8:00"))
                .serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)))
                .serializerByType(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)))
                .serializerByType(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)))
                .serializerByType(Date.class, new DateSerializer(false, new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN)))
                .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)))
                .deserializerByType(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)))
                .deserializerByType(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)))
                .deserializerByType(Date.class, new DateDeserializers.DateDeserializer(DateDeserializers.DateDeserializer.instance, new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN), DatePattern.NORM_DATETIME_PATTERN))
                .build();
    }

    /*
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        // 指定时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        // 日期类型字符串处理
        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));

        // Java8日期日期处理
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN)));
        objectMapper.registerModule(javaTimeModule);

        // 定义序列化规则
        // 忽略json字符串中不识别的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略无法转换的对象
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // PrettyPrinter 格式化输出
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // NULL不参与序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        converter.setObjectMapper(objectMapper);
        return converter;
    }
    */
}

