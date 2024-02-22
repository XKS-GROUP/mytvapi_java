package com.mytv.api.execptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseModel {

    public String type;

    public List<ErrorModel> errorModels;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ErrorModel> getErrorModels() {
		return errorModels;
	}

	public void setErrorModels(List<ErrorModel> errorModels) {
		this.errorModels = errorModels;
	}
    
    
}