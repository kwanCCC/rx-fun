## RxJava Extension

# 20 example use Java CompletableFuture

> 为什么这个被名为CompletableFuture

首先可拆解为 **Completable** 和 **Future** ,因为其实现了 **CompletionStage** 和 **future**，首先我们可以看 **CompletionStage** 的接口的*Doc* _(PS:写好文档无比重要)_，首先它代表了了一个特定的计算阶段，并且可以被同步或是异步的完成，加上 **Future** 提供的功能，即为一个未完成的计算单元 ，想象一下，_Stage I_ -> _Stage II_ -> .... 依次类推，这就组成了一条计算的流水线，一个个的完成，一个触发一个

> 简单的开始

>> 预定义结果创建CompletableFuture

```
public static void simpleCompletableFuture(){
    CompletableFuture someone = CompletableFuture.completedFuture("I will come")
    someone.isDone()
    assertEquals("I will come", someone.getNow(null));
} 

```

getNow(null)会在Future完成的情况下返回,但是这个鬼例子🌰和同步有毛区别，别急

>> 简单的异步

```
public static void runAsAsync(){
    someFuture = CompletableFuture.runAsAsync(()->{
        // go make some coffe,I would finish it
    });
    task_a_break()
    assertTrue(someFuture.isDone)
}
```

如上的例子 **CompletableFuture** 中以 _Async_ 结尾的方法,在没有指定 **Executor** 的情况下会使用 **ForkJoinPool** 的 _commonPool_ ,注意它使用的是守护线程

>> 来一个之前说的 _Stage I_ -> _Stage II_ 的例子

```
public static void syncPipline(){
    syncPipline = CompletableFuture.completedFuture("I am coming with sugar").thenApply(()-{
        //I need to get some coffee
    });
    syncPipline.getNow(null)
}
```
这个例子形象的说明两个动作，先拿糖，之后泡了一杯咖啡, _then_ 意味着需要上一阶段得完成, _apply_ 意味着会对上一阶段基础的结果执行,所以函数会阻塞

>> 接着上一个 A 去拿糖 B 去接热水

```
public static void AsyncPipline(){
    aysnc = CompletableFuture.completableFuture("A: I got some sugar").thenApplyAsync(()->{
        //B : I got hot water
    });
    // I need to wait here ,Because I have nothing
    assertNull(aysnc.getNow(null))
    // Afterwards, I can easily make a cup of coffee
    aysnc.join()
}
```

>> 


# convert between Flowable and Computable 