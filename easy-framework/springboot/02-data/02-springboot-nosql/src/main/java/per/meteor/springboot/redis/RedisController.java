package per.meteor.springboot.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meteor
 * @date 2022-04-15 23:55
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @GetMapping
    public ResponseEntity<String> setAndGet(@RequestParam String key, @RequestParam String value) {
        final ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set(key, value);

        return ResponseEntity.ok(opsForValue.get(key));
    }
}
