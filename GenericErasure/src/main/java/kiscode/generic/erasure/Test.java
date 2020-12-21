package kiscode.generic.erasure;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> stringList=new ArrayList<>();
        List<Integer> intList=new ArrayList<>();
        System.out.println(stringList.getClass()+" --- "+intList.getClass());
    }


 /*   public class Erase<T>{
        public void f(Object arg){
            if(arg instanceof T){} //编译错误,无法使用instanceof 关键字
            T var = new T();//编译错误,无法通过new表达式创建泛型参数类型对象
            T[] array = new T[10];//编译错误,无法创建泛型数组
        }
    }*/
}