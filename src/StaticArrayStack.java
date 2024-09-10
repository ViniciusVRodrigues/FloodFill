public class StaticArrayStack<T> extends ArrayStack<T>{

    StaticArrayStack(int size){
        this.size = size;
        this.data = (T[]) new Object[size];
    }

    public void push(T data){
        if(isFull())
            return;
        top++;
        this.data[top] = data;
    }

    public T pop(){
        if(isEmpty())
            return null;
        T data = this.data[top];
        this.data[top] = null;
        top--;
        return data;
    }

}
