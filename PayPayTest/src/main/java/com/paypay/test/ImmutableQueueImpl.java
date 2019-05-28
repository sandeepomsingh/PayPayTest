package com.paypay.test;



public final class ImmutableQueueImpl<T> implements Queue<T> {

	private final Stack<T> backwards;
    private final Stack<T> forwards;
     
    private ImmutableQueueImpl(Stack<T> forwards, Stack<T> backwards)
    {
        this.forwards = forwards;
        this.backwards = backwards;
    }
    
    /**
     * Reverses the provided stack.
     * @param stack
     * @return
     * @throws Exception
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public final static Stack reverseStack(Stack stack) throws Exception
    {
        com.paypay.test.Stack r = ImmutableStackImpl.getEmptyStack();
        while(!stack.isEmpty()){
        	r = r.push(stack.head());   
        	stack = stack.pop();
        }
      
        return r;
    }
	
	@SuppressWarnings({ "rawtypes" })
	public final static Queue getEmptyQueue(){
		return EmptyQueue.getInstance();
	}
	
	public final Queue<T> enQueue(T t){
		return new ImmutableQueueImpl<T>(forwards, (Stack<T>) backwards.push(t));
	}
	
	@SuppressWarnings("unchecked")
	public final Queue<T> deQueue() throws Exception{
		Stack<T> f = forwards.pop();
        if (!f.isEmpty()){
            return new ImmutableQueueImpl<T>(f, backwards);
        }
        else if (backwards.isEmpty()){
            return ImmutableQueueImpl.getEmptyQueue();
        }
        else {
            return new ImmutableQueueImpl<T>(ImmutableQueueImpl.reverseStack(backwards), ImmutableStackImpl.getEmptyStack());
        }
	}
	
	public final T head() throws Exception{
		return forwards.head();
	}
	
	public final boolean isEmpty(){
		return false;
	}
	
	/**
	 * Represents an empty queue. This is a singleton.
	 * @author asifiqbal
	 *
	 * @param <T>
	 */
	private static final class EmptyQueue<T> implements Queue<T>{
		
		@SuppressWarnings("rawtypes")
		private final static EmptyQueue emptyQueue = new EmptyQueue();
		
		@SuppressWarnings("rawtypes")
		public final static EmptyQueue getInstance(){
			return emptyQueue;
		}
		
		@SuppressWarnings("unchecked")
		public final Queue<T> enQueue(T t){
			return new ImmutableQueueImpl<T>(ImmutableStackImpl.getEmptyStack().push(t), ImmutableStackImpl.getEmptyStack());
		}
		
		public final Queue<T> deQueue() throws Exception{
			throw new Exception("Queue is empty.");
		}
		
		public final T head() throws Exception{
			throw new Exception("Queue is empty.");
		}
		
		public final boolean isEmpty(){
			return true;
		}
	}
}
