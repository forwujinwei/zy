package io.renren.common.constants;

public enum DistEnum {
    INTENT_GOOD("IN_001","意向特别好的"),
    INTENT_RENAME("IN_002","改名"),
    INTENT_NON_LIC("IN_003","执照没下来"),
    INTENT_ONLY_REGIST("IN_004","只可以注册，还没有委托我"),
    INTENT_REGIST_ONE("IN_005","已经注册过了，一手标"),
    INTENT_OTHERS("IN_006","其他"),
    INTENT_NO_FOLLOW("IN_008","不跟踪了"),
    INTENT_SIGN_AGREEMENT("IN_009","已经签合同的资源"),
    INTENT_TOBE_CONFIRM("IN_010","待确认"),
    INTENT_RESUBMIT("IN_011","重复提交"),

    QUERY_PHONE("QU_001","电话"),
    QUERY_QQ("QU_002","QQ"),
    QUERY_CONSUMER("QU_003","客户名称"),
    IQUERY_ADVISER("QU_004","顾问名称"),
    QUERY_TRADEMARK("QU_005","商标名称"),

    ACROSS_TYPE_HOME_STATION("AT_001","本站"),
    ACROSS_TYPE_SHENGFAN("AT_002","盛凡"),
    ACROSS_TYPE_QINGSHANG("AT_003","清上"),

    RESOURCE_TYPE_SB("RT_001","商标"),
    RESOURCE_TYPE_ZL("RT_002","专利"),
    RESOURCE_TYPE_GSB("RT_003","国际商标"),
    RESOURCE_TYPE_GZL("RT_004","国际专利"),

    SOURCE_TYPE_SHANGQIAO("ST_001","商桥"),
    SOURCE_TYPE_DIANXIAO("ST_002","电销"),
    SOURCE_TYPE_400("ST_003","400"),
    SOURCE_TYPE_QQ("ST_004","QQ"),
    SOURCE_TYPE_OTHERS("ST_005","其他"),

    RESOURCE_STATUS_NO_SURE("RS_001","待确认"),
    RESOURCE_STATUS_SURE("RS_002","确认"),
    RESOURCE_STATUS_GIVE_UP("RS_003","放弃"),


    INFO_RANGE_TWO("IR_001","2个月内信息"),
    INFO_RANGE_THREE("IR_002","3个月内信息"),
    INFO_RANGE_FOUR("IR_003","4个月内信息"),

    AGREEMENT_TYPE_PATENT("AGT_001","专利"),
    AGREEMENT_TYPE_COPYRIGHT("AGT_002","版权"),
    AGREEMENT_TYPE_TRADEMARK("AGT_003","商标"),

    URGENT_YES("UY_001","加急"),
    URGENT_NO("UY_002","不加急"),

    AGREEMENT_QUERY_TYPE_AGREEMENT_CODE("AQT_001","合同号"),
    AGREEMENT_QUERY_TYPE_TRADE_MARK("AQT_002","商标名称"),
    AGREEMENT_QUERY_TYPE_LINKMAN("AQT_003","联系人"),
    AGREEMENT_QUERY_TYPE_ADVISER("AQT_004","不加急"),

    TRADE_MARK_STATUS_APPLY("TMS_001","申请"),
    TRADE_MARK_STATUS_ACCEPT("TMS_002","下达受理"),
    TRADE_MARK_STATUS_REJECT("TMS_003","驳回"),
    TRADE_MARK_STATUS_OBJECTION("TMS_004","异议"),
    TRADE_MARK_STATUS_CERTIFICATE("TMS_005","下证"),
    TRADE_MARK_STATUS_OTHER("TMS_006","其他"),

    TRADE_MARK_CLASSIFY_01("TMC_001","化学原料"),
    TRADE_MARK_CLASSIFY_02("TMC_002","颜料油漆"),
    TRADE_MARK_CLASSIFY_03("TMC_003","日化用品"),
    TRADE_MARK_CLASSIFY_04("TMC_004","燃料油脂"),
    TRADE_MARK_CLASSIFY_05("TMC_005","医药卫生"),
    TRADE_MARK_CLASSIFY_06("TMC_006","五金金属"),
    TRADE_MARK_CLASSIFY_07("TMC_007","机械设备"),
    TRADE_MARK_CLASSIFY_08("TMC_008","手工器械"),
    TRADE_MARK_CLASSIFY_09("TMC_009","科学仪器"),
    TRADE_MARK_CLASSIFY_10("TMC_010","医疗器械"),
    TRADE_MARK_CLASSIFY_11("TMC_011","家用电器"),
    TRADE_MARK_CLASSIFY_12("TMC_012","运输工具"),
    TRADE_MARK_CLASSIFY_13("TMC_013","军用烟花"),
    TRADE_MARK_CLASSIFY_14("TMC_014","珠宝钟表"),
    TRADE_MARK_CLASSIFY_15("TMC_015","乐器"),
    TRADE_MARK_CLASSIFY_16("TMC_016","文化用品"),
    TRADE_MARK_CLASSIFY_17("TMC_017","橡胶制品"),
    TRADE_MARK_CLASSIFY_18("TMC_018","皮革皮具"),
    TRADE_MARK_CLASSIFY_19("TMC_019","建筑材料"),
    TRADE_MARK_CLASSIFY_20("TMC_020","家具"),
    TRADE_MARK_CLASSIFY_21("TMC_021","家用器具"),
    TRADE_MARK_CLASSIFY_22("TMC_022","绳网袋篷"),
    TRADE_MARK_CLASSIFY_23("TMC_023","纺织纱线"),
    TRADE_MARK_CLASSIFY_24("TMC_024","床单布料"),
    TRADE_MARK_CLASSIFY_25("TMC_025","服装鞋帽"),
    TRADE_MARK_CLASSIFY_26("TMC_026","花边拉链"),
    TRADE_MARK_CLASSIFY_27("TMC_027","地毯席垫"),
    TRADE_MARK_CLASSIFY_28("TMC_028","体育玩具"),
    TRADE_MARK_CLASSIFY_29("TMC_029","食品罐头"),
    TRADE_MARK_CLASSIFY_30("TMC_030","调味茶糖"),
    TRADE_MARK_CLASSIFY_31("TMC_031","水果花木"),
    TRADE_MARK_CLASSIFY_32("TMC_032","啤酒饮料"),
    TRADE_MARK_CLASSIFY_33("TMC_033","酒"),
    TRADE_MARK_CLASSIFY_34("TMC_034","烟草烟具"),
    TRADE_MARK_CLASSIFY_35("TMC_035","广告贸易"),
    TRADE_MARK_CLASSIFY_36("TMC_036","金融物管"),
    TRADE_MARK_CLASSIFY_37("TMC_037","建筑修理"),
    TRADE_MARK_CLASSIFY_38("TMC_038","通讯电信"),
    TRADE_MARK_CLASSIFY_39("TMC_039","运输旅行"),
    TRADE_MARK_CLASSIFY_40("TMC_040","材料处理"),
    TRADE_MARK_CLASSIFY_41("TMC_041","教育娱乐"),
    TRADE_MARK_CLASSIFY_42("TMC_042","科研服务"),
    TRADE_MARK_CLASSIFY_43("TMC_043","餐饮酒店"),
    TRADE_MARK_CLASSIFY_44("TMC_044","医疗园艺"),
    TRADE_MARK_CLASSIFY_45("TMC_045","社会法律"),
    ;





    private String typeCode;
    private String des;
    DistEnum(String typeCode,String des){
        this.typeCode=typeCode;
        this.des=des;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getDes() {
        return des;
    }

    public static String getDesByCode(String typeCode) {
        for (DistEnum distEnum : DistEnum.values()) {
            if (distEnum.getTypeCode().equalsIgnoreCase(typeCode) ) {
                return distEnum.getDes();
            }
        }
        return "";
    }
}
