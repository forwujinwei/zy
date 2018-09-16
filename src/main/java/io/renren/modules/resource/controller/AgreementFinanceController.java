package io.renren.modules.resource.controller;


import io.renren.common.constants.DistEnum;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.utils.R;
import io.renren.modules.api.annotation.LoginUser;
import io.renren.modules.api.entity.UserEntity;
import io.renren.modules.resource.model.AgreementFinanceModel;
import io.renren.modules.resource.model.ResourcePersonalPoolModel;
import io.renren.modules.resource.service.AgreementFinanceService;
import io.renren.modules.resource.vo.AgreementFinanceVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/finance")
@RestController
public class AgreementFinanceController {

    @Resource
    private AgreementFinanceService agreementFinanceService;

    /**
     * 打款查询
     * @param user
     * @param params
     * @return
     * @throws ParseException
     */
    @RequestMapping("/list")
    public R list(@LoginUser UserEntity user, @RequestParam Map<String, Object> params) throws ParseException {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }

        Query query = new Query(params);
        List<AgreementFinanceVo> agreementFinanceModels = agreementFinanceService.queryList(query);
        int total = agreementFinanceService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(agreementFinanceModels, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 财务归档
     * @param user
     * @param params
     * @return
     * @throws ParseException
     */
    @RequestMapping("/file/list")
    public R fileList(@LoginUser UserEntity user, @RequestParam Map<String, Object> params)  {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }
        params.put("statusCode",DistEnum.ACCOUNT_STATUS_003.getTypeCode());
        params.put("username",username);
        Query query = new Query(params);
        List<AgreementFinanceVo> agreementFinanceModels = agreementFinanceService.queryFileList(query);
        int total = agreementFinanceService.queryFileListTotal(query);
        PageUtils pageUtil = new PageUtils(agreementFinanceModels, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    @RequestMapping("/save")
    public R save(@LoginUser UserEntity user, @RequestBody AgreementFinanceModel agreementFinanceModel) throws ParseException {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }
        agreementFinanceModel.setId(UUID.randomUUID().toString().replaceAll("-",""));
        agreementFinanceModel.setStatusCode(DistEnum.ACCOUNT_STATUS_001.getTypeCode());
        agreementFinanceModel.setStatusDes(DistEnum.ACCOUNT_STATUS_001.getDes());
        String gatheringAccount = agreementFinanceModel.getGatheringAccount();
        if(StringUtils.isNotBlank(gatheringAccount)){
            agreementFinanceModel.setGatheringAccount(DistEnum.getDesByCode(gatheringAccount));
        }
        agreementFinanceModel.setCreateBy(username);
        agreementFinanceModel.setUpdateBy(username);
        agreementFinanceModel.setCreateDate(new Date());
        agreementFinanceModel.setUpdateDate(new Date());
        agreementFinanceModel.setDeleteFlag("0");
        agreementFinanceModel.setRemitDes(DistEnum.getDesByCode(agreementFinanceModel.getRemitCode()));
        agreementFinanceService.save(agreementFinanceModel);
        return R.ok();
    }


    @RequestMapping("/update")
    public R update(@LoginUser UserEntity user, @RequestBody AgreementFinanceModel agreementFinanceModel) throws ParseException {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }
        String statusCode = agreementFinanceModel.getStatusCode();
        if(StringUtils.isNotBlank(statusCode)){
            agreementFinanceModel.setStatusDes(DistEnum.getDesByCode(statusCode));
        }
        String gatheringAccount = agreementFinanceModel.getGatheringAccount();
        if(StringUtils.isNotBlank(gatheringAccount)){
            agreementFinanceModel.setGatheringAccount(DistEnum.getDesByCode(gatheringAccount));
        }
        agreementFinanceModel.setUpdateBy(username);
        agreementFinanceModel.setUpdateDate(new Date());
        agreementFinanceService.update(agreementFinanceModel);
        return R.ok();
    }

    @RequestMapping("/delete/{id}")
    public R delete(@LoginUser UserEntity user,@PathVariable String id)  {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }
        AgreementFinanceModel agreementFinanceModel = new AgreementFinanceModel();
        agreementFinanceModel.setId(id);
        agreementFinanceModel.setUpdateBy(username);
        agreementFinanceModel.setUpdateDate(new Date());
        agreementFinanceModel.setDeleteFlag("1");
        agreementFinanceService.update(agreementFinanceModel);
        return R.ok();
    }

    @RequestMapping("/submit/{id}")
    public R submit(@LoginUser UserEntity user,@PathVariable String id) throws ParseException {
        //查询列表数据
        String username = user.getUsername();
        if(StringUtils.isBlank(username)){
            return R.error("用户不存在，请联系管理员");
        }
        AgreementFinanceModel agreementFinanceModel = new AgreementFinanceModel();
        agreementFinanceModel.setId(id);
        agreementFinanceModel.setStatusCode(DistEnum.ACCOUNT_STATUS_002.getTypeCode());
        agreementFinanceModel.setStatusDes(DistEnum.ACCOUNT_STATUS_002.getDes());
        agreementFinanceModel.setUpdateBy(username);
        agreementFinanceModel.setUpdateDate(new Date());
        agreementFinanceService.update(agreementFinanceModel);
        return R.ok();
    }
}
