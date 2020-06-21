package com.tiger.demo.enums;


public enum DataSourceEnum {
	MASTE_DB("master", "主库数据源"),
	SLAVE_DB("slave", "从库数据源"),
	IMPALA_DB("impala", "impala数据源"),
	;
	private String value;

	private String remark;

	DataSourceEnum(String value, String remark) {
		this.value = value;
		this.remark = remark;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String value() {
		return getValue();
	}


}
