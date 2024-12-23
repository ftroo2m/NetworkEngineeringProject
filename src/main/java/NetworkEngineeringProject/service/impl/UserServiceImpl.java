package NetworkEngineeringProject.service.impl;

import NetworkEngineeringProject.entity.UserEntity;
import NetworkEngineeringProject.mapper.UserMapper;
import NetworkEngineeringProject.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zk
 * @since 2024-12-20 16:29:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
