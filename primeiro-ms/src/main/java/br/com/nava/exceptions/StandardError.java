package br.com.nava.exceptions;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

	private Long timestamp;
	private Integer status;
	private String error;
	private String  message;
	private String path;	
}