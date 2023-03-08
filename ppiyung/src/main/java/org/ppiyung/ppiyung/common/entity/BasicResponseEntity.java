package org.ppiyung.ppiyung.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicResponseEntity<T> {
	private boolean success;
	private String msg;
	private T payload;
}
