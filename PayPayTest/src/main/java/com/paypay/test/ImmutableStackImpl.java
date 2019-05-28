package com.paypay.test;

public final class ImmutableStackImpl<T> implements Stack<T> {

	private final T head;
	private final Stack<T> tail;
	
	private ImmutableStackImpl(T head, Stack<T> tail){
		this.head = head;
		this.tail = tail;
	}
	
	public final Stack<T> push(T t){		
		return new ImmutableStackImpl<T>(t, this);
	}
	
	public final Stack<T> pop(){
		return tail;
	}
	
	public final T head(){
		return head;
	}
	
	public final boolean isEmpty(){
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	public final static Stack getEmptyStack(){
		return EmptyStack.getInstance();
	}
	
	
	 // Represents an empty stack. This is a singleton.
	 
	private static final class EmptyStack<T> implements Stack<T>{
		
		@SuppressWarnings("rawtypes")
		private final static EmptyStack emptyStack = new EmptyStack();
		
		@SuppressWarnings("rawtypes")
		public final static EmptyStack getInstance(){
			return emptyStack;
		}
		
		public final Stack<T> push(T t){			
			return new ImmutableStackImpl<T>(t, this);
		}
		
		public final Stack<T> pop() throws Exception{
			throw new Exception("Stack is empty.");
		}
		
		public final T head() throws Exception{
			throw new Exception("Stack is empty.");
		}
		
		public final boolean isEmpty(){
			return true;
		}
	}
}
