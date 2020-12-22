# JsonTypeSupport
Json generic types support.Json泛型类型解析，支持多重泛型嵌套，解决java泛型擦除问题

由json反序列化泛型对象引发的思考

- 1. 泛型擦除
- 2. Gson TypeToken原理
- 3. Retrofit 泛型解析原理



## 1. 泛型擦除
### 1.1 (What)什么是泛型擦除
- Java中泛型是JDK 1.5后引入，java泛型仅作为编译阶段校验，在运行阶段泛型类型会被擦除，如List<String>和List<Integer>编译后的类型都为ArrayList

```java
List<String> stringList=new ArrayList<>();
List<Integer> intList=new ArrayList<>();
System.out.println(stringList.getClass()+" --- "+intList.getClass());
//输出 class java.util.ArrayList --- class java.util.ArrayList
```

### 1.2 (Why)为什么会产生泛型擦除
- java在JDK 1.5之后才引入了泛型，因为泛型不是 Java 语言出现时就有的，所以为了向前兼容旧版本，编译时将泛型进行擦除处理，这本质是一种妥协处理。
- 泛型类型只有在静态类型检测期间(编译期间)才出现，在此之后，程序中的所有泛型类型都将被擦除，替换为它们的非泛型上界。例如， List<T> 这样的类型注解会被擦除为 List，普通的类型变量在未指定边界的情况下会被擦除为 Object。

### 1.3 泛型擦除产生的影响
1. 无法使用instanceof 关键字
2. 无法通过new表达式创建泛型参数类型对象
3. 无法创建泛型数组

```java
public class Erase<T>{
    public void f(Object arg){
        if(arg instanceof T){} //编译错误,无法使用instanceof 关键字
        T var = new T();//编译错误,无法通过new表达式创建泛型参数类型对象
        T[] array = new T[10];//编译错误,无法创建泛型数组
    }
}
```


### 1.4 (How)如何实现泛型擦除 补偿
- 因为擦除，我们将失去执行泛型代码中某些操作的能力。无法在运行时知道确切类型，但不必沮丧，我们仍然可通过特殊处理对泛型擦除实现补偿 以获得丢失的类型信息。
- 具体补偿方式：通过引入类型标签来补偿擦除，
为所需的类型显式传递一个 Class 对象
##### 1.4.1 使用动态的isInstance代替instanceof
```java
class Building {
}

class House extends Building {
}

public class ClassTypeCapture<T> {
    //通过引入类型标签来补偿擦除
    Class<T> kind;

    public ClassTypeCapture(Class<T> kind) {
        this.kind = kind;
    }

    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }

    public static void main(String[] args) {
        ClassTypeCapture<Building> ctt1 = new ClassTypeCapture<>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));

        ClassTypeCapture<House> ctt2 = new ClassTypeCapture<>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
    }
}

```

##### 1.4.2 使用newInstance()方法创建对象
- 通过newInstance()可创建泛型的对象，但由于泛型类型类是否有无参构造函数是未知的，所以此处可能因找不到对应构造函数发生异常
```java
class ClassAsFactory<T> {
    T x;

    public ClassAsFactory(Class<T> kind) {
        try {
            x = kind.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
```

- 对于没有无参构造函数的类，可通过工厂方法模式来创建对象


##### 1.4.3 创建泛型数组
- 如果需要用到泛型参数类型的数组那么使用ArrayList

```java
public class ListOfGenerics<T> {
    //通过
    private List<T> array = new ArrayList<>();

    public void add(T item) {
        array.add(item);
    }

    public T get(int index) {
        return array.get(index);
    }

    public List<T> getData() {
        return array;
    }
}
```

-------
