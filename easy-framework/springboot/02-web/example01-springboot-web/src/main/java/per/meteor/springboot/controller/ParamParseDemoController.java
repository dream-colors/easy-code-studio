package per.meteor.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import per.meteor.springboot.common.response.ResponseBody;
import per.meteor.springboot.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>请求注解</p>
 * <ul>
 *     <li>请求方式注解：{@link GetMapping}、{@link PostMapping}、{@link PutMapping}、{@link DeleteMapping}、{@link PatchMapping}、等注解都是在{@link RequestMapping}注解上指定了method</li>
 * </ul>
 * <P>表单参数</P>
 * <ul>
 *     <li>ContentType: application/x-www-form-urlencoded Method: *</li>
 *     <li>单参数形式: 参数注解{@link RequestParam}, 使用{@link org.springframework.web.method.annotation.RequestParamMethodArgumentResolver}参数处理器</li>
 *     <li>对象类型: 无需注解，使用{@link org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor}参数处理器</li>
 * </ul>
 * <P>JSON参数</P>
 * <ul>
 *     <li>ContentType: application/json, Method: POST</li>
 *     <li>参数注解{@link RequestBody}, 使用{@link org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor}参数处理器，最终用到的
 *     是jackson的{@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}转换器</li>
 * </ul>
 * <p>URL参数</p>
 * <ul>
 *     <li>ContentType: application/x-www-form-urlencoded，Method: GET</li>
 *     <li>采用{}作为占位符，参数注解{@link PathVariable}, 使用{@link org.springframework.web.servlet.mvc.method.annotation.PathVariableMethodArgumentResolver}参数处理器</li>
 *     <li>带条件的URL参数：路径参数支持正则表达式，"/{param:pattern}"，如"user/{sex:M|F}"</li>
 *     <li>矩阵参数：URL参数的一种，参数注解{@link MatrixVariable}，不安全，默认关闭，使用需要设置RequestMappingHandlerMapping#setRemoveSemicolonContent方法为false</li>
 * </ul>
 *
 * <p>文件上传</p>
 * <ul>
 *     <li>ContentType: application/form-data，Method：POST</li>
 *     <li>采用{@link org.springframework.web.multipart.MultipartFile}对象接收</li>
 *     <li>无参数注解情况：使用{@link org.springframework.web.method.annotation.RequestParamMethodArgumentResolver}参数处理器</li>
 *     <li>有参数注解{@link RequestPart}情况下，采用{@link org.springframework.web.servlet.mvc.method.annotation.RequestPartMethodArgumentResolver}参数处理器</li>
 * </ul>
 * <p>其他类型参数</p>
 * <ul>
 *     <li>其他参数主要包括请求头、Cookie、Model、Map等相关参数，还有一些并不是很常用或者一些相对原生的属性值获取</li>
 *     <li>请求头：参数注解{@link RequestHeader}, 通过name指定需要获取的头信息, 使用{@link org.springframework.web.method.annotation.RequestHeaderMethodArgumentResolver}参数处理器</li>
 *     <li>Cookie：参数注解{@link CookieValue}, 通过name指定需要获取的cookie信息, 使用{@link org.springframework.web.servlet.mvc.method.annotation.ServletCookieValueMethodArgumentResolver}参数处理器</li>
 *     <li>Model类型参数：使用{@link org.springframework.ui.Model}或{@link org.springframework.ui.ModelMap}接收，用于接口桥接，使用{@link org.springframework.web.method.annotation.ModelMethodProcessor}参数处理器</li>
 *     <li>ModelAttribute参数：通过key-value形式绑定方法参数或者方法返回值到Model(Map)中，使用{@link org.springframework.web.method.annotation.ModelAttributeMethodProcessor}参数处理器</li>
 *     <li>Errors或者BindingResult参数: 异常结果处理，Errors类型的参数处理器为{@link org.springframework.web.method.annotation.ErrorsMethodArgumentResolver}参数处理器</li>
 *     <li>Value参数：从Environment中装配和转换属性值到对应的参数中，使用{@link org.springframework.web.method.annotation.ExpressionValueMethodArgumentResolver}参数处理器</li>
 *     <li>Map类型参数：</li>
 *     <ol>
 *          <li>不带参数，实际上直接回调ModelAndViewContainer中的ModelMap实例，参数处理器为MapMethodProcessor，往Map参数中添加的属性将会带到页面中</li>
 *          <li>使用{@link RequestParam}注解，获取请求参数，使用的请求方式需要指定ContentType为x-www-form-urlencoded，不能使用application/json的方式：</li>
 *          <li>使用{@link RequestHeader}注解，获取所有请求头参数，</li>
 *          <li>使用{@link PathVariable}注解，获取所有URL参数</li>
 *     </ol>
 * </ul>
 * <p>日期参数处理</p>
 * <ul>
 *     <li>统一字符串接收，然后手动处理</li>
 *     <li>使用日期格式化注解{@link org.springframework.format.annotation.DateTimeFormat}或{@link com.fasterxml.jackson.annotation.JsonFormat}注解，
 *     DateTimeFormat注解配合@RequestBody的参数使用的时候，会抛出InvalidFormatException异常，提示转换失败，这是因为在处理此注解的时候，只支持form提交(ContentType为x-www-form-urlencoded),
 *     而@JsonFormat注解可使用在form或者Json请求参数的场景，因此更推荐使用@JsonFormat注解，不过注意需要指定时区(timezone属性，例如在中国是东八区"GMT+8")，否则有可能导致出现"时差",
 *     </li>
 *     <li>Jackson序列化和反序列化定制: 使用钩子接口{@link org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer}可以实现ObjectMapper的属性定制：</li>
 * </ul>
 * <p>请求URL匹配</p>
 * <ul>
 *     <li>Ant风格匹配：单个字符（？），多个字符（*）, 多个目录（**），正则</li>
 * </ul>
 *
 * @author meteor
 * @date 2022-03-19 17:05
 */
@RestController
@RequestMapping("/paramParse")
public class ParamParseDemoController {

    @GetMapping("/formParam")
    public ResponseBody<Map<String, Object>> formParam(@RequestParam String message, User user) {

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("method", "ParamParseDemoController#formParam");
        resultMap.put("message", message);
        resultMap.put("user", user);

        return ResponseBody.success(resultMap);
    }

    @GetMapping("/user")
    public User getUser(User user) {
        return user;
    }

    @PostMapping("/jsonParam")
    public ResponseEntity<Object> jsonParam(@RequestBody User user) {

        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("method", "ParamParseDemoController#jsonParam");
        resultMap.put("user", user);

        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/urlParamPathVariable/{name}")
    public ResponseEntity<Object> urlParamPathVariable(@PathVariable String name) {

        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("method", "ParamParseDemoController#urlParamPathVariable");
        resultMap.put("name", name);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 矩阵参数：/user/tom;password=135;age=18
     * 使用时需开启矩阵参数支持，RequestMappingHandlerMapping#setRemoveSemicolonContent方法为false
     * @param name /
     * @param password /
     * @param age /
     * @return /
     */
    @GetMapping("/urlParamMatrixVariable/{name}")
    public ResponseEntity<Object> urlParamMatrixVariable(@PathVariable(value = "name") String name,
                                            @MatrixVariable(value = "password") String password,
                                            @MatrixVariable(value = "age") Integer age) {

        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("method", "ParamParseDemoController#urlParamMatrixVariable");
        resultMap.put("name", name);
        resultMap.put("passwor", password);
        resultMap.put("age", age);

        return ResponseEntity.ok(resultMap);
    }

    @GetMapping("/urlParamPatternVariable/{name:tom|alen}")
    public ResponseEntity<Object> urlParamPatternVariable(@PathVariable(value = "name") String name) {

        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("method", "ParamParseDemoController#urlParamPatternVariable");
        resultMap.put("name", name);

        return ResponseEntity.ok(resultMap);
    }

    /**
     *  MultipartFile实例的主要属性分别来自Content-Disposition、content-type和content-length
     *  MultipartFile参数注解@RequestPart与@RequestParam的区别
     *  RequestParam可能与名称-值表单字段一起使用，而RequestPart可能与包含更复杂内容的部分（例如 JSON、XML）一起使用。
     * @param file /
     * @return /
     */
    @PostMapping("/fileUpload")
    public ResponseEntity<Object> fileUpload(@RequestPart MultipartFile file) {

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("method", "ParamParseDemoController#fileUpload");
        resultMap.put("fileName", file.getOriginalFilename());
        resultMap.put("fileSize", file.getSize());
        resultMap.put("fileType", file.getContentType());

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 文件批量上传，
     * 1、使用MultipartHttpServletRequest参数接收，然后调用getFiles方法
     * 2、使用@RequestParam注解修饰MultipartFile列表，相当于第一种方式的封装。
     * @param files /
     * @return /
     */
    @PostMapping(value = "/batchFileUpload")
    public ResponseEntity<Object> batchFileUpload(@RequestParam(value = "files") List<MultipartFile> files) {

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("method", "ParamParseDemoController#fileUpload");

        List<HashMap<String, Object>> fileInfoList = files.stream().map(file -> {
            HashMap<String, Object> fileInfoMap = new HashMap<>(4);
            fileInfoMap.put("fileName", file.getOriginalFilename());
            fileInfoMap.put("fileSize", file.getSize());
            fileInfoMap.put("fileType", file.getContentType());
            return fileInfoMap;
        }).collect(Collectors.toList());

        resultMap.put("fileInfos", fileInfoList);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * 使用@RequestHeader获取单个Header时，必须提供对应的key-value, 然会404
     * @param contentType /
     * @param headers /
     * @return /
     */
    @GetMapping(path = "/otherParamHeader")
    public ResponseEntity<Object> otherParamHeader(@RequestHeader(name = "Content-type") String contentType, @RequestHeader Map<String, Object> headers) {

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("method", "ParamParseDemoController#otherParamHeader");

        resultMap.put("contentType", contentType);
        resultMap.put("headers", headers);

        return ResponseEntity.ok(resultMap);
    }


    @GetMapping(path = "/otherParamCookie")
    public ResponseEntity<Object> otherParamCookie(@CookieValue(name = "name") String name) {

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("method", "ParamParseDemoController#otherParamCookie");

        resultMap.put("name", name);

        return ResponseEntity.ok(resultMap);
    }

    /**
     * ModelMap或者Model中添加的属性项会附加到HttpRequestServlet中带到页面中进行渲染。
     * model.equals(modelMap) = true
     * @param model /
     * @param modelMap /
     * @return /
     */
    @GetMapping("/otherParamModel")
    public ResponseEntity<Object> otherParamModel(Model model, ModelMap modelMap) {

        model.addAttribute("model", "model");
        modelMap.addAttribute("modelMap", "modelMap");

        model.addAttribute("sameAttr", "modelAttr");
        modelMap.addAttribute("sameAttr", "modelAttr");

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("method", "ParamParseDemoController#otherParamModel");
        resultMap.put("equals", model.equals(modelMap));
        resultMap.put("model", model);

        return ResponseEntity.ok(resultMap);
    }


    @ModelAttribute
    public void before(Model model) {
        model.addAttribute("before", "beforeValue");
    }

    @ModelAttribute(name = "beforeArg")
    public String beforeArg() {
        return "beforeArg";
    }


    /**
     * ModelAttribute注解：用于设置ModelAttribute参数
     * 1、ModelAttribute作用到方法上时，优先Controller方法执行
     * 2、ModelAttribute作用到无返回值得方法时，需传入Model参数进行手动设置，见before方法
     * 3、ModelAttribute作用到有返回值的方法时，需指定value作为属性名称，并以方法返回值作为属性值,见beforeArg方法
     * 4、多个ModelAttribute修饰的方法按书写顺序执行。
     * @param model /
     * @param beforeArg /
     * @return /
     */
    @GetMapping("/otherParamModelAttribute")
    public ResponseEntity<Object> otherParamModelAttribute(Model model, @ModelAttribute(name = "beforeArg") String beforeArg) {

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("method", "ParamParseDemoController#otherParamModel");
//        resultMap.put("model", model);

        return ResponseEntity.ok(resultMap);
    }
    /**
     * 采用BingResult 错误参数接收，
     * @param user /
     * @param bindingResult /
     * @return /
     */
    @PostMapping(value = "/otherParamError")
    public ResponseEntity<Object> otherParamError(@RequestBody @Validated User user, BindingResult bindingResult) {

        HashMap<String, Object> resultMap = new HashMap<>(2);

        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                resultMap.put("errorInfo", String.format("name=%s,message=%s", objectError.getObjectName(), objectError.getDefaultMessage()));
            }
        }

        return ResponseEntity.ok(resultMap);
    }
}
