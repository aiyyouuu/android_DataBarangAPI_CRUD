package com.example.mahasiswapc.myapplication.model.create;

public class PostResponse{
	private Data data;
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
			"PostResponse{" + 
			"data = '" + data + '\'' + 
			",errors = '" + errors + '\'' + 
			"}";
		}
}
