package com.bridgelabz.fundoo.labels.dto;

public class LabelsDto {

private String labelName;

public String getLabelName() {
	return labelName;
}

public void setLabelName(String labelName) {
	this.labelName = labelName;
}

@Override
public String toString() {
	return "LabelsDto [labelName=" + labelName + "]";
}

	
}
