package com.example.mahasiswapc.myapplication.model.update;


import com.google.gson.annotations.SerializedName;


public class UpdateResponse{

	@SerializedName("data")
	private Object data;

	@SerializedName("errors")
	private String errors;

	public void setData(Object data){
		this.data = data;
	}

	public Object getData(){
		return data;
	}

	public void setErrors(String errors){
		this.errors = errors;
	}

	public String getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"UpdateResponse{" + 
			"data = '" + data + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}