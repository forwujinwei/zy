package io.renren.modules.resource.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.renren.common.utils.R;
import io.renren.modules.api.annotation.AuthIgnore;
import io.renren.modules.resource.model.TradeMarkClassify;
import io.renren.modules.resource.service.TradeMarkClassifyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.util.Map;
import java.util.Set;

/**
 * @author jinweia.wu
 * @create 2018-08-12 12:34
 **/
@RequestMapping("/api/resource/tradeMark")
@RestController
public class TradeMarkClassifyController {
    @Resource
    private TradeMarkClassifyService tradeMarkClassifyService;
    @AuthIgnore
    @RequestMapping("/save")
    public R save(){
            File file = new File("D:/tradeMarkClassify.txt");
            StringBuilder localStrBulider = new StringBuilder();
            if(file.isFile() && file.exists()) {
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
                    BufferedReader bufferReader = new BufferedReader(inputStreamReader);
                    String lineStr = null;
                    try {
                        while((lineStr = bufferReader.readLine()) != null) {
                            localStrBulider.append(lineStr);
                        }
                        bufferReader.close();
                        inputStreamReader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        System.out.println("file read error!");
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    System.out.println("file catch unsupported encoding!");
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    System.out.println("file not found!");
                    e.printStackTrace();
                }
            }else {
                System.out.println("file is not a file or file is not existing!");
                return null;
            }
            String jsonStr =  localStrBulider.toString();

            Map<String, Object> parentMap = JSONObject.parseObject(jsonStr);
            Set<String> parentIds = parentMap.keySet();
            for (String parentId:parentIds) {
                Map parentValue = (Map<String, Object>) parentMap.get(parentId);
                String name = (String) parentValue.get("name");
                String cname = (String) parentValue.get("cname");
                TradeMarkClassify parentTradeMark = new TradeMarkClassify();
                parentTradeMark.setId(parentId);
                parentTradeMark.setName(name);
                parentTradeMark.setCname(cname);
                parentTradeMark.setParentId("0");

                tradeMarkClassifyService.save(parentTradeMark);

                Map<String, JSONObject> childMap = (Map<String, JSONObject>) parentValue.get("item");
                Set<String> childIds = childMap.keySet();
                for (String childId : childIds) {


                    JSONObject jsonObject = childMap.get(childId);
                    String childName = jsonObject.get("name").toString();
                    TradeMarkClassify childTradeMark = new TradeMarkClassify();
                    childTradeMark.setParentId(parentId);
                    childTradeMark.setId(childId);
                    childTradeMark.setName(childName);
                    tradeMarkClassifyService.save(childTradeMark);
                    JSONArray items = jsonObject.getJSONArray("item");

                    for (int y = 0; y < items.size(); y++) {
                        Map<String, String> itemMap = (Map<String, String>) items.get(y);
                        Set<String> itemIds = itemMap.keySet();
                        for (String itemId : itemIds) {
                            TradeMarkClassify itemTradeMark = new TradeMarkClassify();
                            itemTradeMark.setParentId(childId);
                            itemTradeMark.setName(itemMap.get(itemId));
                            itemTradeMark.setId(itemId);
                            tradeMarkClassifyService.save(itemTradeMark);
                        }
                    }
                }
            }
        return R.ok().put("page", null);
    }
}
