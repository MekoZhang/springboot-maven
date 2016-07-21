package cn.zhangxd.trip.infrastructure;

import cn.zhangxd.trip.Application;
import cn.zhangxd.trip.infrastructure.repository.Scenic;
import cn.zhangxd.trip.infrastructure.repository.ScenicRepository;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserRepoTest {

    @Autowired
    private ScenicRepository scenicRepository;

    @Test
    public void testScenic() {
        List<Scenic> list =  scenicRepository.getPageList();
        System.out.println(list.size());
        for (Scenic scenic : list) {
            System.out.println(new Gson().toJson(scenic));
        }
    }

}
