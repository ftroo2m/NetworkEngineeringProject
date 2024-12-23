package NetworkEngineeringProject.controller;

import NetworkEngineeringProject.entity.AirqualityvalueEntity;
import NetworkEngineeringProject.service.IAirqualityvalueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static NetworkEngineeringProject.util.util.gson;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zk
 * @since 2024-11-18 22:41:23
 */
@RestController
@RequestMapping("/api/airqualityvalue")
public class AirqualityvalueController {

    @Autowired
    IAirqualityvalueService airqualityvalueService;

    @PostMapping("/realtime")
    @ResponseBody
    public String postAllAirQualityValues() {
        QueryWrapper<AirqualityvalueEntity> queryWrapper = new QueryWrapper<>();
        // 将时间字符串转换为日期时间格式，并按降序排序
        queryWrapper.orderByDesc("STR_TO_DATE(time, '%Y-%m-%d %H:%i:%s')");
        // 添加LIMIT子句以限制结果数量为1
        queryWrapper.last("LIMIT 1");
        // 使用selectOne方法获取单条记录
        AirqualityvalueEntity result = airqualityvalueService.getBaseMapper().selectOne(queryWrapper);
        // 将结果转换为JSON字符串并返回
        return gson.toJson(result);
    }

    @GetMapping
    public String GetAllAirQualityValues() {
        System.out.println("GetSuccess");
        QueryWrapper<AirqualityvalueEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("time", "2014");
        AirqualityvalueEntity airqualityvalueEntity = airqualityvalueService.getOne(queryWrapper);
        return gson.toJson(airqualityvalueEntity);
    }
}
