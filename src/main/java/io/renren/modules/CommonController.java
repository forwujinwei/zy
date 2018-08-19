package io.renren.modules;

import io.renren.common.constants.DistEnum;
import io.renren.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 公共controller
 *
 * @author jinweia.wu
 * @create 2018-08-19 3:29
 **/
@RequestMapping("/api/common")
@RestController
public class CommonController {
    @RequestMapping(value = "/trademark/classify")
    public R getTrademarkClassify(){
        List<DistModel> tradeMarkClassify = DistEnum.getDistByType("TRADE_MARK_CLASSIFY");
        return R.ok().put("data",tradeMarkClassify);
    }
}
