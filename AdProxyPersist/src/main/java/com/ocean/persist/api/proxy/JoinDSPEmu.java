package com.ocean.persist.api.proxy;

public enum JoinDSPEmu {
	
	/** 灵集*/
	LINGJI(7, "灵集", "lj", 4),
	
	/** 泰莱*/
	TAYLOR(8, "泰莱", "tl", 5),

	/** 讯飞*/
	XUNFEI(9, "讯飞", "xf", 6),

	/** 搏点*/
	BOCLICK(10, "搏点", "bd", 7),
	
	/** 万流客*/
	VAMAKER(11, "万流客", "wlk", 8),
	
	/** 摩邑城*/
	MEX(12, "摩邑城", "myc", 9),
	
	/** adview*/
	ADVIEW(13, "adview", "av", 11),
	
	/** 易积分*/
	YIJIFEN(14, "易积分", "yjf", 12),
	
	/** 玩咖*/
	WANKA(15, "玩咖", "wk", 10),
	
	/** 申米*/
	SHENMI(16, "申米", "sm", 13),
	
	/** 头条*/
	TOUTIAO(17, "头条", "tt", 14),
	
	/** 光音*/
	GUANGYIN(18, "光音", "gy", 15),
	
	/** 搜影*/
	SOUYING(19, "搜影", "sy", 16),
	
	/** OneMob*/
	ONEMOB(20, "OneMob", "om", 17),
	
	/** 仙果*/
	XIANGUO(21, "仙果", "xg", 18),
	
	/** 有道*/
	YOUDAO(22, "有道", "yd", 19),
	
	/** 众橙*/
	ZHONGCHENG(23, "众橙", "zc", 20),
	
	/** InMobi*/
	INMOBI(24, "InMobi", "im", 21),
	
	/** 瑞恩*/
	RYAN(25, "瑞恩", "re", 22),
	
	/** 捷酷*/
	JIEKU(26, "捷酷", "jk", 23);
	
	private final int value;

	private final String name;
	
	private final String abbrev;
	
	private final int id2adr;
	
	private JoinDSPEmu(int value, String name, String abbrev, int id2adr) {
		this.value = value;
		this.name = name;
		this.abbrev = abbrev;
		this.id2adr = id2adr;
	}

	public int getValue() {
		return value;
	}
	
	public String getName(){
		return name;
	}

	public String getAbbrev() {
		return abbrev;
	}
	
	public int getId2adr() {
		return id2adr;
	}

	public static JoinDSPEmu getDsp(int value){
		
		JoinDSPEmu[] dsps = values();
		for (JoinDSPEmu dsp : dsps) 
		{
			if(dsp.getValue() == value)
			{
				return dsp;
			}	
		}
		return null;
	}
	public static JoinDSPEmu getJoinDspByName(String value){
		
		JoinDSPEmu[] dsps = values();
		for (JoinDSPEmu dsp : dsps) 
		{
			if(dsp.getAbbrev().equals(value))//english name
			{
				return dsp;
			}	
		}
		return null;
	}
}
