# Answer

`Answer` 是用来配置 mock 结果的通用接口，它指定了被 mock 对象的执行动作和返回结果。

`Answers` 枚举类中包含了 Mockito 预定义的一些 `Answer`，当使用 `mock()` 来创建 mock 对象时可以将这些枚举类作为参数传入，从而配置
mock 对象没有被 stub 方法的默认执行动作和返回值。当使用 `@Mock` 创建 mock 对象时，也可以通过 `@Mock(answer=xxx)` 来进行配置。

1. `RETURNS_DEFAULTS` mock 对象时的默认 `Answer`，返回 empty value（比如：0, false, empty collection）具体值见 
`org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues`
2. `RETURNS_SMART_NULLS` 对于没有 stub 的调用返回值首先是 empty value（比如：0, false, empty collection），如果最后返回的是 
null 值则会用 `SmartNull` 进行代替， 好处是调用 `SmartNull` 结果不会抛出 NPE，而是更友好的错误消息
3. `RETURNS_MOCKS` 对于没有 stub 的调用返回值首先是 empty value （比如：0, false, empty collection），否则尝试返回 mock 对象，
对于没办法 mock 的类（final、primitive 类）则返回 null
4. `RETURNS_DEEP_STUBS` mock 对象的返回值的返回值（等等）会是一个 mock 对象，同样如果调用链路中有一个类无法被 mock 则最终无法正常
生效
5. `CALLS_REAL_METHODS` 对于未 stub 的方法会直接调用真实的方法，适合 mock 对象的部分方法
6. `RETURNS_SELF` 主要给 Builder 模式的类 mock 使用，如果一个未 stub 方法返回值是该类或者该类的父类，则始终返回 mock 对象自身
```
class HttpBuilder {
    HttpBuilder withUrl(String url){}
    HttpBuilder withHeader(String header){}
}
HttpBuilder builder = mock(HttpBuilder.class, RETURNS_SELF);
// 返回的还是 builder
HttpBuilder builder2 = builder.withUrl("test");
assert builder == builder2
```