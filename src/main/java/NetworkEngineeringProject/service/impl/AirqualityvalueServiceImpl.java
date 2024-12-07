package NetworkEngineeringProject.service.impl;

import NetworkEngineeringProject.entity.AirqualityvalueEntity;
import NetworkEngineeringProject.mapper.AirqualityvalueMapper;
import NetworkEngineeringProject.service.IAirqualityvalueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zk
 * @since 2024-11-18 22:41:23
 */
@Service
public class AirqualityvalueServiceImpl extends ServiceImpl<AirqualityvalueMapper, AirqualityvalueEntity> implements IAirqualityvalueService {
    public void insertValue(String time,int value){
        AirqualityvalueEntity airqualityvalueEntity = new AirqualityvalueEntity();
        airqualityvalueEntity.setTime(time);
        airqualityvalueEntity.setValue(value);
        this.save(airqualityvalueEntity);
    }
}
