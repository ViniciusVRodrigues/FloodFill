public abstract class ArrayStack<T> implements StackInterface<T>{
    protected int size;
    protected int top =-1;
    protected T[] data;

    public abstract void push(T data);

    public void clear(){
        while(top!=-1){
            this.data[top] = null;
            top--;
        }
    }

    public int lenght(){
        return top+1;
    }

    public boolean isEmpty(){
        return top==-1;
    }

    public boolean isFull(){
        return size==top+1;
    }

    public String toString(){
        String string = "{";
        for (int i = 0; i < size; i++){
            string+= data[i];
            if(i+1!=size)
                string+=", ";
        }

        string += "}";
        return string;
    }
}
