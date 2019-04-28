package com.example.mahasiswapc.myapplication.model.getid;


import com.google.gson.annotations.SerializedName;

public class GetItemResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("errors")
	private Object errors;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
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
			"GetItemResponse{" + 
			"data = '" + data + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}