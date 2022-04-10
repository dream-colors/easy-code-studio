package per.meteor.springboot.controller;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import per.meteor.springboot.SpringbootWebApplication;
import per.meteor.springboot.entity.User;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.io.FileInputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;

/**{@link }
 * <P>1、引入测试依赖：</p>
 * <ul>
 *     <li>{@link AutoConfigureMockMvc}注解引入MockMvc测试依赖</li>
 *     <li>{@link SpringBootTest}注解引入Springboot测试上下文依赖，需指定加载容器，可配置容器属性、启动参数、上下文对象</li>
 * </ul>
 *
 * @author meteor
 * @date 2022-03-19 23:14
 */
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringbootWebApplication.class)
class ParamParseDemoControllerTest {

    /**
     * <p>2、构建MockMvc对象</p>
     * <ul>
     *     <li>MockMvcBuilder 是用来构造 MockMvc 的构造器，其主要有两个实现：StandaloneMockMvcBuilder 和 DefaultMockMvcBuilder，
     *     分别对应两种测试方式，即独立安装和集成 Web 环境测试（此种方式并不会集成真正的 web 环境，而是通过相应的 Mock API 进行模拟测试，无须
     *     启动服务器）。对于我们来说直接使用静态工厂 MockMvcBuilders 创建即可。</li>
     *     <li>Web集成测试：MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定 WebApplicationContext，
     *     将会从该上下文获取相应的控制器并得到相应的 MockMvc；WebApplicationContext可采用自动注入的形式</li>
     *     <li>独立测试环境：MockMvcBuilders.standaloneSetup(Object… controllers)：通过参数指定一组控制器，这样就不需要从上下文获取了；</li>
     * </ul>
     */
    @Resource
    private MockMvc mockMvc;


    /**
     * <p>3、MockMvc介绍</p>
     * <p>核心构件</p>
     * <ul>
     *     <li>{@link org.springframework.test.web.servlet.RequestBuilder}请求构件器，用于构建{@link org.springframework.mock.web.MockHttpServletRequest},
     *     可设置请求相关信息（请求头、请求体）, 主要有两个子类 {@link MockHttpServletRequestBuilder} 和 {@link org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder}
     *     （如文件上传使用），即用来 Mock 客户端请求需要的所有数据。</li>
     *     <li>{@link ResultActions}：请求响应对象，调用 MockMvc.perform(RequestBuilder requestBuilder)后将得到 </li>
     *     <li>{@link ResultMatcher}：结果验证器，验证响应结果是否符合预期</li>
     * </ul>
     * <p>执行流程</p>
     * <ol>
     *     <li>准备测试环境(RequestBuilder)</li>
     *     <li>通过 MockMvc 执行请求(mockMvc.perform)</li>
     *     <li>添加验证断言(ResultActions.andExpect)</li>
     *     <li>添加结果处理器(ResultActions.andDo)</li>
     *     <li>得到 MvcResult 进行自定义断言/进行下一步的异步请求(ResultActions.andReturn)</li>
     *     <li>卸载测试环境</li>
     * </ol>
     * @throws Exception /
     */
    @Test
    void formParam() throws Exception {

        /*
          1、构建请求对象
          a、定义请求方式：get/put/delete/post/header/patch/multipart(文件上传)/异步转发
          b、定义请求信息：secure(安全协议，如https)、characterEncoding、contentType、accept(媒体类型)、headers、param、
            queryParam、local(本地语言)、cookie、requestAttr、sessionAttr、flushAttr(缓存)

           备注:
           form-data与x-www-form-urlencoded的区别
            multipart/form-data：可以上传文件或者键值对，最后都会转化为一条消息
            x-www-form-urlencoded：只能上传键值对，而且键值对都是通过&间隔分开的
         */
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/paramParse/formParam")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .param("message", "hello word")
                .content("id=1&name=tom&password=123&married=false&birthday=1997-03-15");
        /*
        2、构建结果匹配器，校验结果是否符合预期
            a、响应头匹配器(MockMvcResultMatchers.header())
            b、响应状态匹配器(MockMvcResultMatchers.status())
            c、响应内容匹配器: 内容体(MockMvcResultMatchers.content())、cookie(MockMvcResultMatchers.cookie())、缓存(MockMvcResultMatchers.flush())
            session(MockMvcResultMatchers.session())
            d、请求与转发：请求(MockMvcResultMatchers.request())、转发(MockMvcResultMatchers.redirect(forward))、重定向(MockMvcResultMatchers.forward())
         */
        ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
        ResultMatcher handlerMatcher = MockMvcResultMatchers.handler().handlerType(ParamParseDemoController.class);
        ResultMatcher contentMatcher = MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON);

        /*
        3、结果处理，调用andDo传入处理器，或者调用andReturn得返回结果自行处理结果
         */
        ResultActions actions = mockMvc.perform(requestBuilder)
                .andExpect(statusMatcher)
                .andExpect(handlerMatcher)
                .andExpect(contentMatcher)
                .andDo(result -> System.out.print(result.getResponse().getContentAsString()));
    }

    @Test
    void jsonParam() throws Exception {

        User user = new User().setId(2)
                .setName("alen")
                .setPassword("123")
                .setMarried(false)
                .setBirthday(LocalDate.of(1997, 3, 15));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI.create("/paramParse/jsonParam"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8)
                .content(JSONUtil.toJsonStr(user));

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.ISO_8859_1))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        Assertions.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void urlParamPathVariable() throws Exception {

        HashMap<String, Object> expectJson = new HashMap<>();
        expectJson.put("method", "ParamParseDemoController#urlParamPathVariable");
        expectJson.put("name", "tom");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI.create("/paramParse/urlParamPathVariable/tom"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .characterEncoding(StandardCharsets.UTF_8);

        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding(StandardCharsets.ISO_8859_1))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(JSONUtil.toJsonStr(expectJson)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    void urlParamMatrixVariable() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/paramParse/urlParamMatrixVariable/tom;password=123;age=12"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void urlParamPatternVariable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/paramParse/urlParamPatternVariable/alen"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void fileUpload() throws Exception {

        MockMultipartFile multipartFile = new MockMultipartFile("file", "file", MediaType.APPLICATION_PDF_VALUE, new FileInputStream("D:\\download\\钟宗兵 - Java开发工程师.pdf"));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/paramParse/fileUpload").file(multipartFile))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void batchFileUpload() throws Exception {

        MockMultipartFile multipartFile1 = new MockMultipartFile(
                "files",
                "file1",
                MediaType.APPLICATION_PDF_VALUE,
                new FileInputStream("D:\\download\\钟宗兵 - Java开发工程师.pdf"));

        MockMultipartFile multipartFile2 = new MockMultipartFile(
                "files",
                "file2",
                MediaType.TEXT_PLAIN_VALUE,
                ParamParseDemoControllerTest.class.getClassLoader().getResourceAsStream("test.txt"));

        mockMvc.perform(MockMvcRequestBuilders.multipart("/paramParse/batchFileUpload").file(multipartFile1).file(multipartFile2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 请求头测试
     */
    @Test
    void otherParamHeader() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/paramParse/otherParamHeader")
                .header("content-type", "application/utf-8")
                .header("name", "tom")
                .header("password", "123")
                .header("sex", "男");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void otherParamCookie() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/paramParse/otherParamCookie")
                .cookie(new Cookie("name", "tom"));

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void otherParamModel() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/paramParse/otherParamModel"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void otherParamModelAttribute() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/paramParse/otherParamModelAttribute"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void otherParamError() throws Exception {

        User user = new User().setId(2)
                .setPassword("123")
                .setMarried(false)
                .setBirthday(LocalDate.of(1997, 3, 15));


        mockMvc.perform(MockMvcRequestBuilders.post("/paramParse/otherParamError").contentType(MediaType.APPLICATION_JSON).content(JSONUtil.toJsonStr(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
}
