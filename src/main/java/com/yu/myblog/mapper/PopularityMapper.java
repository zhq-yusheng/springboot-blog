package com.yu.myblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.myblog.pojo.Popularity;
import com.yu.myblog.pojo.view.EvenDayPopularityView;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: my-blog
 * @Package: com.yu.myblog.mapper
 * @ClassName: PopularityMapper
 * @Author: 钟洪强
 * @Description: 人气表mapper层
 * @Date: 2022/1/24 14:02
 * @Version: 1.0
 */
@Repository
public interface PopularityMapper extends BaseMapper<Popularity> {
     List<EvenDayPopularityView> getSevenDayPopularity();
}
