package io.renren.modules.resource.vo;

import io.renren.modules.resource.model.ResourceAgreement;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * 合同列表
 *
 * @author jinweia.wu
 * @create 2018-08-19 12:29
 **/
public class ResourceAgreementVo extends ResourceAgreement {
    /**
     * 从数据库查询回来字符串类型
     */
    private String tradeMarkName;

    private List<Map<String,String>> tradeMarkNameArray;

    private int nameLength;

    public String getTradeMarkName() {
        return tradeMarkName;
    }

    public void setTradeMarkName(String tradeMarkName) {
        this.tradeMarkName = tradeMarkName;
    }

    public List<Map<String,String>> getTradeMarkNameArray() {
        this.tradeMarkNameArray= new ArrayList<>();
        if(StringUtils.isNotBlank(tradeMarkName)){
            String[] split = this.tradeMarkName.split("\\|");
            for (int i = 0 ;i<split.length;i++){
               Map<String, String> nameMap = new HashMap<>();
                nameMap.put("name",split[i]);
                this.tradeMarkNameArray.add(nameMap);
            }
        }
        return tradeMarkNameArray;
    }


    public int getNameLength() {
        if(tradeMarkNameArray!=null){
            this.nameLength=tradeMarkNameArray.size();
        }else{
            this.nameLength=1;
        }
        return nameLength;
    }
}
