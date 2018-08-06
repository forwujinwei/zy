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
    INFO_RANGE_FOUR("IR_003","4个月内信息")
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
