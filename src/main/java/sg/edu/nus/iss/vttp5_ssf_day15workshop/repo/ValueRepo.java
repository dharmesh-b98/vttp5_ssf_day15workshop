package sg.edu.nus.iss.vttp5_ssf_day15workshop.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.vttp5_ssf_day15workshop.constant.Constants;

@Repository
public class ValueRepo {
    
    @Autowired
    @Qualifier(Constants.template01)
    private RedisTemplate<String,String> template;


    public String getValue(String key){
        return template.opsForValue().get(key);
    }

    public Boolean deleteValue(String key){
        return template.delete(key);
    }

    public long incrementValue (String key, Integer value){
        return template.opsForValue().increment(key, value);
    }

    public long decrementValue (String key, Integer value){
        return template.opsForValue().decrement(key, value);
    }

    public Boolean checkExists(String key){
        return template.hasKey(key);
    }
}
