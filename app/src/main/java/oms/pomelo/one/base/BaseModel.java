package oms.pomelo.one.base;

/**
 * NAME: Sherry
 * DATE: 2019-12-02
 */
public class BaseModel<T> {

    private int res;
    private T data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
