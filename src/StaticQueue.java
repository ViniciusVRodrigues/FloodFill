public class StaticQueue<T> extends ArrayStack<T>{

    StaticQueue(int size){
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
        T data = this.data[0];
        for (int i = 1; i <= top; i++) {
            if(this.data[i]==null)
                break;
            this.data[i-1] = this.data[i];
            this.data[i] = null;
        }
        top--;
        return data;
    }
}
