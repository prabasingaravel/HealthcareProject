package com.module.patientmodule.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DataDto represent Data table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDto {

	private List<String> fieldName;
	private List<Object> oldValue;
	private List<Object> newValue;
}
