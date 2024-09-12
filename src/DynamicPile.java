public class DynamicPile<T> extends ArrayStack<T>{

    private Float growthRate = 1.5f;

    DynamicPile(){
        size = 20;
        this.data = (T[]) new Object[size];
    }
    public void push(T data){
        if(isFull())
            increaseSize();
        top++;
        this.data[top] = data;
    }

    public void push(T[] data){
        for (int i = 0; i < data.length; i++) {
            if(isFull())
                increaseSize();
            top++;
            this.data[top] = data[i];
        }
    }

    public T pop(){
        if(isEmpty())
            return null;
        T data = this.data[top];
        this.data[top] = null;
        top--;
        return data;
    }

    public void increaseSize(){
        Float newSizeFloat = size*growthRate;
        size = newSizeFloat.intValue();
        T[] newData = (T[]) new Object[size];
        System.arraycopy(data, 0, newData, 0, top+1);
        data = newData;
    }

    public boolean contains(T data){
        for (int i = 0; i <= top; i++) {
            if(this.data[i].equals(data))
                return true;
        }
        return false;
    }
}
