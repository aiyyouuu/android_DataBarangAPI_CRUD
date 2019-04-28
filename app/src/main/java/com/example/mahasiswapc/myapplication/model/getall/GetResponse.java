package com.example.mahasiswapc.myapplication.model.getall;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class GetResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("errors")
	private Object errors;

	public void setData(List<DataItem> data){
		this.data = data;
	}

	public List<DataItem> getData(){
		return data;
	}

	public void setErrors(Object errors){
		this.errors = errors;
	}

	public Object getErrors(){
		return errors;
	}

	@Override
 	public String toString(){
		return 
			"GetResponse{" + 
			"data = '" + data + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}