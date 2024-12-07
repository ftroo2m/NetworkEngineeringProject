package NetworkEngineeringProject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("NetworkEngineeringProject.mapper")
public class NetworkEngineeringProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkEngineeringProjectApplication.class, args);
    }

}
