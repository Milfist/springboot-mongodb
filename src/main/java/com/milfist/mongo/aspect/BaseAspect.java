package com.milfist.mongo.aspect;

import com.milfist.mongo.domain.Logger;
import com.milfist.mongo.enumerator.Level;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.fluentd.logger.FluentLogger;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FluentMongoOperations;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

public class BaseAspect {

  FluentLogger logger;

  FluentMongoOperations mongoOperations;

  @Autowired
  public void setLogger(FluentLogger logger, FluentMongoOperations mongoOperations) {
    this.logger = logger;
    this.mongoOperations = mongoOperations;
  }

  private static ConcurrentHashMap<String, MethodStats> methodStats = new ConcurrentHashMap<>();
  private static long statLogFrequency = 10;
  private static long methodWarningThreshold = 1000;

  public Method getMethod(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    return  signature.getMethod();
  }

  public Object getMethodInvocation(JoinPoint joinPoint) {
    MethodInvocationProceedingJoinPoint point = (MethodInvocationProceedingJoinPoint) joinPoint;
    return  point.getThis();
  }

  public String getMethodName(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    return  signature.getMethod().getName();
  }

  public String getClassName(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    return signature.getClass().getName();
  }

  public Class getClass(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    return signature.getClass();
  }

  public void updateStats(String className, String methodName, long elapsedTime) {
    MethodStats stats = methodStats.get(methodName);

    if (stats == null) {
      stats = new MethodStats(className, methodName);
      methodStats.put(methodName, stats);
    }
    stats.count++;
    stats.totalTime += elapsedTime;
    if (elapsedTime > stats.maxTime) {
      stats.maxTime = elapsedTime;
    }

    if (elapsedTime > methodWarningThreshold) {
      logger.log("WARN", "method warning:", "Class : " + className + " Method: " + methodName + "(), count = " + stats.count + ", lastTime = " + elapsedTime + ", maxTime = " + stats.maxTime);
      mongoOperations.insert(Logger.class)
          .one(
              getLogger(className, methodName, Level.WARN)
                  .setCount(stats.count)
                  .setElapsedTime(elapsedTime)
                  .setMaxTime(stats.maxTime)
                  .build()
          );
    }

    if (stats.count % statLogFrequency == 0) {
      long avgTime = stats.totalTime / stats.count;
      long runningAvg = (stats.totalTime - stats.lastTotalTime) / statLogFrequency;
      logger.log("INFO", "information: ", " Class : " + className + " Method : " + methodName + "(), count = " + stats.count + ", lastTime = " + elapsedTime + ", avgTime = " + avgTime + ", runningAvg = " + runningAvg + ", maxTime = " + stats.maxTime);
      mongoOperations.insert(Logger.class)
          .one(
              getLogger(className, methodName, Level.INFO)
                  .setCount(stats.count)
                  .setElapsedTime(elapsedTime)
                  .setAvgTime(avgTime)
                  .setRunningAvg(runningAvg)
                  .setMaxTime(stats.maxTime)
                  .build()
          );


      // reset the last total time
      stats.lastTotalTime = stats.totalTime;
    }

  }

  private Logger.Builder getLogger(String className, String methodName, Level level) {
    return new Logger.Builder(level, className, methodName);
  }

  //TODO: Separar en un componente??
  @Data
  class MethodStats {
    private String methodName;
    private String className;
    private long count;
    private long totalTime;
    private long lastTotalTime;
    private long maxTime;

    public MethodStats(String className, String methodName) {
      this.className = className;
      this.methodName = methodName;
    }
  }

}
