package com.curisLoc.Util;

public class GenererPasswordAleatoire {
private static String caracterePassword = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	
	public static String genererPasswordAleatoire(int sizePassword) {

		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i<sizePassword; i++){
			builder.append(caracterePassword.charAt((int) (Math.random() * 36)));
		}
	
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println(genererPasswordAleatoire(6));
	} 

}
