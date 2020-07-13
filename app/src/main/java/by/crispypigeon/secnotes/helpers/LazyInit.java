package by.crispypigeon.secnotes.helpers;

//Java util Supplier work in 24 level api or above
public class LazyInit<T> {

    private T value;
    private MySupplier<T> supplier;

    public LazyInit(MySupplier<T> supplier) {
        this.supplier = supplier;
    }

    public T getValue(){
        if(value == null)
            value = supplier.get();

        return value;
    }
}
