package com.carousell.qe.mobile.enums;

public enum ConditionType {
	NEW {
		@Override
		public String toString() {
		return "New";
	  }
	},
	USED {
		@Override
		public String toString() {
		return "Used";
	  }
	}

}
