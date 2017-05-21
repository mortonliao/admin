package com.longhum.admin.entity;

import com.longhum.admin.model.SysResource;

public class TreeResource extends SysResource{
	private boolean checked;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
