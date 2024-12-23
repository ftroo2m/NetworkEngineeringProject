package NetworkEngineeringProject.controller;

import NetworkEngineeringProject.entity.Result;
import NetworkEngineeringProject.entity.UserEntity;
import NetworkEngineeringProject.service.IUserService;
import NetworkEngineeringProject.util.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zk
 * @since 2024-12-20 16:06:39
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result postAllAirQualityValues(@RequestBody UserEntity user) {
        System.out.println(user);
        QueryWrapper<UserEntity> queryWrapper = Wrappers.<UserEntity>query()
                .eq("username", user.getUsername());
        UserEntity userReal=userService.getOne(queryWrapper);
        if (userReal.getPassword().equals(user.getPassword())){
            Map<String, Object> claims=new HashMap<>();
            claims.put("username",userReal.getUsername());
            claims.put("id",userReal.getId());
            String jwt= JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
        else {
            return Result.error("用户名或密码错误");
        }
    }
}
