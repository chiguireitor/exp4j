package net.objecthunter.exp4j.operator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.objecthunter.exp4j.ComplexNumber;

public class Operators {
	public static final int PRECEDENCE_ADDITION = 500;
	public static final int PRECEDENCE_SUBTRACTION = PRECEDENCE_ADDITION;
	public static final int PRECEDENCE_MULTIPLICATION = 1000;
	public static final int PRECEDENCE_DIVISION = PRECEDENCE_MULTIPLICATION;
	public static final int PRECEDENCE_MODULO = PRECEDENCE_DIVISION;
	
	private static Map<String,CustomOperator> builtin = new HashMap<String, CustomOperator>();
	
	static{
		builtin.put("+",new CustomOperator("+",PRECEDENCE_ADDITION) {
			@Override
			public Object apply(Object... args) {
				if (args[0] instanceof Float){
					return (float) args[0] + (float) args[1];
				}else if (args[0] instanceof Double){
					return (double) args[0] + (double) args[1];
				}else if (args[0] instanceof BigDecimal){
					return ((BigDecimal)args[0]).add((BigDecimal) args[1]);
				}else if (args[0] instanceof ComplexNumber){
					throw new RuntimeException("No support for complex numbers");
				}else {
					throw new RuntimeException("Unknown type " + args[0].getClass().getName());
				}
			}
		});
		builtin.put("-",new CustomOperator("-",PRECEDENCE_SUBTRACTION) {
			@Override
			public Object apply(Object... args) {
				if (args[0] instanceof Float){
					return (float) args[0] - (float) args[1];
				}else if (args[0] instanceof Double){
					return (double) args[0] - (double) args[1];
				}else if (args[0] instanceof BigDecimal){
					return ((BigDecimal)args[0]).subtract((BigDecimal) args[1]);
				}else if (args[0] instanceof ComplexNumber){
					throw new RuntimeException("No support for complex numbers");
				}else {
					throw new RuntimeException("Unknown type " + args[0].getClass().getName());
				}
			}
		});
		builtin.put("*",new CustomOperator("*",PRECEDENCE_MULTIPLICATION) {
			@Override
			public Object apply(Object... args) {
				if (args[0] instanceof Float){
					return (float) args[0] * (float) args[1];
				}else if (args[0] instanceof Double){
					return (double) args[0] * (double) args[1];
				}else if (args[0] instanceof BigDecimal){
					return ((BigDecimal)args[0]).multiply((BigDecimal) args[1]);
				}else if (args[0] instanceof ComplexNumber){
					throw new RuntimeException("No support for complex numbers");
				}else {
					throw new RuntimeException("Unknown type " + args[0].getClass().getName());
				}
			}
		});
		builtin.put("/",new CustomOperator("/",PRECEDENCE_DIVISION) {
			@Override
			public Object apply(Object... args) {
				if (args[0] instanceof Float){
					return (float) args[0] / (float) args[1];
				}else if (args[0] instanceof Double){
					return (double) args[0] / (double) args[1];
				}else if (args[0] instanceof BigDecimal){
					return ((BigDecimal)args[0]).divide((BigDecimal) args[1]);
				}else if (args[0] instanceof ComplexNumber){
					throw new RuntimeException("No support for complex numbers");
				}else {
					throw new RuntimeException("Unknown type " + args[0].getClass().getName());
				}
			}
		});
		builtin.put("%",new CustomOperator("%",PRECEDENCE_MODULO) {
			@Override
			public Object apply(Object... args) {
				if (args[0] instanceof Float){
					return (float) args[0] % (float) args[1];
				}else if (args[0] instanceof Double){
					return (double) args[0] % (double) args[1];
				}else if (args[0] instanceof BigDecimal){
					throw new RuntimeException("No support for big decimals");
				}else if (args[0] instanceof ComplexNumber){
					throw new RuntimeException("No support for complex numbers");
				}else {
					throw new RuntimeException("Unknown type " + args[0].getClass().getName());
				}
			}
		});
	}
	public static boolean isOperator(char c){
		return builtin.containsKey(String.valueOf(c));
	}
	public static CustomOperator getOperator(char c){
		return builtin.get(String.valueOf(c));
	}
}