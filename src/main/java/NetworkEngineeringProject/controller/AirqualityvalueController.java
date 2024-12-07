package NetworkEngineeringProject.controller;

import NetworkEngineeringProject.entity.AirqualityvalueEntity;
import NetworkEngineeringProject.service.IAirqualityvalueService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/airqualityvalue-entity")
public class AirqualityvalueController {

    @Autowired
    IAirqualityvalueService airqualityvalueService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> postAllAirQualityValues(@RequestBody String jsonPayload) {
        try {
            AirqualityvalueEntity airqualityvalueEntity = gson.fromJson(jsonPayload, AirqualityvalueEntity.class);

            // 打印解析后的对象
            System.out.println(airqualityvalueEntity);

            // 保存实体
            airqualityvalueService.save(airqualityvalueEntity);

            System.out.println("Post Success");

            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON payload");
        }
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
